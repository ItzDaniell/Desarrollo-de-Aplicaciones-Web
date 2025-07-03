# Sistema de Rutas Basado en Roles

Este documento describe la implementación del sistema de rutas basado en roles usando `ROLE_DUENO` y `ROLE_CLIENTE` en la aplicación React.

## Roles Disponibles

### ROLE_DUENO (Dueño)
- **Permisos**: Acceso completo a todas las funcionalidades
- **Puede**: Ver, crear, editar y eliminar productos
- **Rutas accesibles**:
  - `/productos` - Ver lista de productos
  - `/productos/crear` - Crear nuevo producto
  - `/productos/editar/:id` - Editar producto existente

### ROLE_CLIENTE (Cliente)
- **Permisos**: Acceso limitado a funcionalidades de lectura
- **Puede**: Ver productos
- **Rutas accesibles**:
  - `/productos` - Ver lista de productos

## Implementación Técnica

### 1. Constantes Centralizadas (`src/utils/constants.js`)

```javascript
export const ROLES = {
  DUENO: 'ROLE_DUENO',
  CLIENTE: 'ROLE_CLIENTE'
};

export const ROLE_DISPLAY_NAMES = {
  [ROLES.DUENO]: 'Dueño',
  [ROLES.CLIENTE]: 'Cliente'
};
```

### 2. Contexto de Autenticación (`src/contexts/AuthContext.jsx`)

El contexto proporciona funciones para verificar roles:

```javascript
const hasRole = (role) => {
  return user?.role === role;
};

const isOwner = () => {
  return hasRole('ROLE_DUENO');
};

const isClient = () => {
  return hasRole('ROLE_CLIENTE');
};
```

### 3. Componente de Ruta Protegida (`src/components/ProtectedRoute.jsx`)

```javascript
const ProtectedRoute = ({ children, requiredRole = null }) => {
  const { isAuthenticated, hasRole, loading } = useAuth();

  // Verificar autenticación
  if (!isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }

  // Verificar rol específico
  if (requiredRole && !hasRole(requiredRole)) {
    return <Navigate to="/unauthorized" replace />;
  }

  return children;
};
```

### 4. Configuración de Rutas (`src/App.jsx`)

```javascript
// Ruta para CLIENTES - pueden ver productos
<Route path="/productos" element={
  <ProtectedRoute requiredRole="ROLE_CLIENTE">
    <ProductosPage />
  </ProtectedRoute>
} />

// Rutas para DUENOS - pueden crear y editar productos
<Route path="/productos/crear" element={
  <ProtectedRoute requiredRole="ROLE_DUENO">
    <ProductoFormPage />
  </ProtectedRoute>
} />

<Route path="/productos/editar/:id" element={
  <ProtectedRoute requiredRole="ROLE_DUENO">
    <ProductoFormPage />
  </ProtectedRoute>
} />
```

## Navegación Basada en Roles

### Header Component (`src/components/layouts/Header.jsx`)

El header muestra diferentes enlaces según el rol del usuario:

- **Todos los usuarios autenticados**: Enlace "Productos"
- **Solo ROLE_DUENO**: Enlace "Crear Producto"

```javascript
{/* Enlace visible para todos los usuarios autenticados */}
{isAuthenticated() && (
  <Link to="/productos">Productos</Link>
)}

{/* Enlace solo para dueños */}
{isAuthenticated() && isOwner() && (
  <Link to="/productos/crear">Crear Producto</Link>
)}
```

## Verificación de Roles en Componentes

### ProductosPage (`src/pages/ProductosPage.jsx`)

```javascript
// Botón "Nuevo Producto" solo para dueños
{user && user.role === ROLES.DUENO && (
  <button onClick={() => navigate('/productos/crear')}>
    Nuevo Producto
  </button>
)}

// Columna de acciones solo para dueños
{user && user.role === ROLES.DUENO && <th>Acciones</th>}
```

### ProductoFormPage (`src/pages/ProductoFormPage.jsx`)

```javascript
// Verificación de rol al inicio del componente
if (!user || user.role !== ROLES.DUENO) {
  return (
    <div className="alert alert-danger">
      No tienes permisos para acceder a este formulario.
    </div>
  );
}
```

## Flujo de Autenticación y Autorización

1. **Login**: El usuario se autentica y recibe su rol del backend
2. **Verificación**: El AuthContext verifica el token y carga los datos del usuario
3. **Navegación**: El usuario intenta acceder a una ruta
4. **Protección**: ProtectedRoute verifica autenticación y rol requerido
5. **Acceso**: Si cumple los requisitos, se muestra el componente; si no, redirección

## Manejo de Errores

### Página de Acceso No Autorizado (`src/pages/UnauthorizedPage.jsx`)

Cuando un usuario intenta acceder a una ruta sin los permisos necesarios, es redirigido a `/unauthorized`.

### Interceptores de API (`src/services/authService.js`)

```javascript
// Interceptor para manejar tokens expirados
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);
```

## Mejores Prácticas Implementadas

1. **Constantes centralizadas**: Todos los roles y rutas están definidos en un archivo de constantes
2. **Verificación en múltiples niveles**: Autenticación en rutas y autorización en componentes
3. **UI adaptativa**: La interfaz se adapta según el rol del usuario
4. **Manejo de errores**: Páginas específicas para casos de acceso no autorizado
5. **Seguridad**: Verificación tanto en frontend como backend

## Extensibilidad

El sistema está diseñado para ser fácilmente extensible:

- **Nuevos roles**: Agregar en `constants.js` y `AuthContext.jsx`
- **Nuevas rutas**: Definir en `constants.js` y configurar en `App.jsx`
- **Nuevos permisos**: Crear funciones específicas en `AuthContext.jsx`

## Consideraciones de Seguridad

⚠️ **Importante**: La verificación de roles en el frontend es solo para UX. La seguridad real debe implementarse en el backend.

- El frontend puede ser manipulado por usuarios maliciosos
- Siempre verificar permisos en el backend antes de procesar requests
- Usar tokens JWT con información de roles validada por el servidor
- Implementar rate limiting y otras medidas de seguridad en el backend 
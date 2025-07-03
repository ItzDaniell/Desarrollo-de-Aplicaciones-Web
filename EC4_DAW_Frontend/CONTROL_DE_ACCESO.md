# Control de Acceso por Roles - Sistema de Productos

## 🎯 Roles del Sistema

### **DUENO** (Juan / password)
- ✅ **Ver todos los productos**
- ✅ **Crear nuevos productos**
- ✅ **Editar productos existentes**
- ✅ **Eliminar productos**
- ✅ **Acceso completo al CRUD**

### **CLIENTE** (Ana / password)
- ✅ **Ver todos los productos**
- ❌ **No puede crear productos**
- ❌ **No puede editar productos**
- ❌ **No puede eliminar productos**
- ❌ **Solo vista de lectura**

## 🔐 Implementación del Control de Acceso

### **1. Nivel de Servicio (authService.js)**
```javascript
// Verificar si el usuario es DUENO
isOwner() {
  return this.hasRole('DUENO');
}

// Verificar si el usuario es CLIENTE
isClient() {
  return this.hasRole('CLIENTE');
}
```

### **2. Nivel de Contexto (AuthContext.jsx)**
```javascript
// Verificar si el usuario tiene un rol específico
const hasRole = (role) => {
  return user?.role === role;
};

// Verificar si el usuario es DUENO
const isOwner = () => {
  return hasRole('DUENO');
};
```

### **3. Nivel de Componente (ProductosPage.jsx)**

#### **Botón "Nuevo Producto"**
```javascript
{isOwner() && (
  <Link to="/productos/crear" className="btn btn-primary">
    <i className="bi bi-plus-circle me-2"></i>
    Nuevo Producto
  </Link>
)}
```

#### **Columna "Acciones" en la tabla**
```javascript
{isOwner() && <th>Acciones</th>}
```

#### **Botones de Editar/Eliminar**
```javascript
{isOwner() && (
  <td>
    <div className="btn-group" role="group">
      <Link to={`/productos/editar/${producto.id}`} className="btn btn-sm btn-outline-primary">
        <i className="bi bi-pencil"></i>
      </Link>
      <button onClick={() => handleDelete(producto.id)} className="btn btn-sm btn-outline-danger">
        <i className="bi bi-trash"></i>
      </button>
    </div>
  </td>
)}
```

### **4. Nivel de Navegación (Header.jsx)**
```javascript
{isOwner() && (
  <li className="nav-item">
    <Link className="nav-link" to="/productos/crear">
      Crear Producto
    </Link>
  </li>
)}
```

### **5. Nivel de Rutas (App.jsx)**
```javascript
{/* Rutas para CRUD de productos (solo DUENO) */}
<Route 
  path="/productos/crear" 
  element={
    <ProtectedRoute requiredRole="DUENO">
      <ProductoFormPage />
    </ProtectedRoute>
  } 
/>

<Route 
  path="/productos/editar/:id" 
  element={
    <ProtectedRoute requiredRole="DUENO">
      <ProductoFormPage />
    </ProtectedRoute>
  } 
/>
```

## 🛡️ Protección en Múltiples Niveles

### **Frontend (React)**
1. **Condicional rendering** - Los botones solo aparecen si `isOwner()` es true
2. **Rutas protegidas** - `ProtectedRoute` verifica el rol antes de renderizar
3. **Navegación condicional** - Enlaces solo visibles para DUENO

### **Backend (Spring Security)**
1. **Anotaciones de seguridad** - `@PreAuthorize("hasRole('DUENO')")`
2. **Filtros JWT** - Verificación de token y roles
3. **Validación de endpoints** - Rechaza peticiones sin autorización

## 📱 Interfaz de Usuario por Rol

### **DUENO - Vista Completa**
```
┌─────────────────────────────────────────────────────────┐
│ Sistema de Productos                    [Juan] [DUENO]  │
├─────────────────────────────────────────────────────────┤
│ Productos | Crear Producto                              │
├─────────────────────────────────────────────────────────┤
│ Lista de Productos                    [Nuevo Producto]  │
├─────────────────────────────────────────────────────────┤
│ ID | Nombre | Descripción | Precio | Cantidad | Categoría | Acciones │
│ 1  | Laptop | Descripción | S/999  | 10       | Electrónicos | [✏️] [🗑️] │
│ 2  | Mouse  | Descripción | S/50   | 25       | Electrónicos | [✏️] [🗑️] │
└─────────────────────────────────────────────────────────┘
```

### **CLIENTE - Vista Limitada**
```
┌─────────────────────────────────────────────────────────┐
│ Sistema de Productos                    [Ana] [CLIENTE] │
├─────────────────────────────────────────────────────────┤
│ Productos                                              │
├─────────────────────────────────────────────────────────┤
│ Lista de Productos                                     │
├─────────────────────────────────────────────────────────┤
│ ID | Nombre | Descripción | Precio | Cantidad | Categoría │
│ 1  | Laptop | Descripción | S/999  | 10       | Electrónicos │
│ 2  | Mouse  | Descripción | S/50   | 25       | Electrónicos │
└─────────────────────────────────────────────────────────┘
```

## 🔍 Verificación de Funcionamiento

### **Para Probar como DUENO:**
1. Login con: `Juan` / `password`
2. Deberías ver:
   - ✅ Botón "Nuevo Producto" en la página principal
   - ✅ Enlace "Crear Producto" en la navegación
   - ✅ Columna "Acciones" en la tabla
   - ✅ Botones de editar (✏️) y eliminar (🗑️) en cada fila

### **Para Probar como CLIENTE:**
1. Login con: `Ana` / `password`
2. Deberías ver:
   - ❌ NO hay botón "Nuevo Producto"
   - ❌ NO hay enlace "Crear Producto" en la navegación
   - ❌ NO hay columna "Acciones" en la tabla
   - ❌ NO hay botones de editar/eliminar

### **Para Verificar Protección de Rutas:**
1. Como CLIENTE, intenta acceder directamente a:
   - `http://localhost:5173/productos/crear`
   - `http://localhost:5173/productos/editar/1`
2. Deberías ser redirigido a la página "Acceso Denegado"

## 🚨 Casos de Seguridad

### **Frontend (React)**
- Los botones no aparecen para usuarios no autorizados
- Las rutas están protegidas con `ProtectedRoute`
- El contexto maneja el estado de autenticación

### **Backend (Spring Security)**
- Validación de JWT en cada petición
- Verificación de roles en los endpoints
- Rechazo de peticiones no autorizadas

## ✅ Resumen

El sistema implementa un control de acceso robusto en múltiples niveles:

1. **Visual** - Los botones solo aparecen para usuarios autorizados
2. **Navegación** - Los enlaces están condicionados por rol
3. **Rutas** - Las páginas están protegidas por `ProtectedRoute`
4. **Backend** - Los endpoints validan JWT y roles
5. **UX** - Experiencia de usuario adaptada al rol

¡El control de acceso está completamente implementado y funcionando! 
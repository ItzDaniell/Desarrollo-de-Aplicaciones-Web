# Sistema de Gestión de Productos - Frontend React

Un sistema moderno de gestión de productos con autenticación JWT y control de acceso basado en roles, desarrollado con React y conectado a un backend Spring Security.

## 🚀 Características

- **Autenticación JWT**: Sistema seguro de autenticación con tokens JWT
- **Control de Roles**: Dos roles principales (DUENO y CLIENTE) con permisos diferenciados
- **CRUD de Productos**: Gestión completa de productos (solo para DUENO)
- **Diseño Moderno**: Interfaz moderna con CSS modularizado y animaciones fluidas
- **Responsive**: Diseño adaptable a todos los dispositivos
- **UX Optimizada**: Experiencia de usuario mejorada con feedback visual y transiciones suaves

## 🎨 Diseño y UX

### Estructura de Estilos Modulares

El proyecto utiliza CSS Modules para una mejor organización y mantenimiento del código:

```
src/styles/
├── index.css              # Estilos globales y variables
├── utils/
│   ├── variables.css      # Variables CSS (colores, espaciado, tipografía)
│   └── animations.css     # Animaciones reutilizables
├── layouts/
│   ├── Header.module.css  # Estilos del header
│   └── Footer.module.css  # Estilos del footer
├── pages/
│   ├── Login.module.css   # Estilos de la página de login
│   ├── Products.module.css # Estilos de la página de productos
│   └── ProductForm.module.css # Estilos del formulario
└── components/
    └── Hero.module.css    # Estilos del componente hero
```

### Características de UX

- **Animaciones Fluidas**: Transiciones suaves y animaciones de entrada
- **Feedback Visual**: Estados de hover, focus y loading claros
- **Accesibilidad**: Soporte para lectores de pantalla y navegación por teclado
- **Modo Oscuro**: Soporte automático para preferencias del sistema
- **Responsive Design**: Adaptable a móviles, tablets y desktop

## 🛠️ Tecnologías Utilizadas

- **React 18**: Framework principal
- **React Router**: Navegación entre páginas
- **CSS Modules**: Estilos modulares y encapsulados
- **Bootstrap Icons**: Iconografía consistente
- **Google Fonts**: Tipografía Inter para mejor legibilidad

## 📦 Instalación

1. **Clonar el repositorio**:
```bash
git clone <url-del-repositorio>
cd EC4_DAW_Frontend
```

2. **Instalar dependencias**:
```bash
npm install
```

3. **Configurar el backend**:
   - Asegúrate de que el backend Spring esté ejecutándose en `http://localhost:8086`
   - O modifica la URL base en `src/config/api.js`

4. **Ejecutar el proyecto**:
```bash
npm run dev
```

## 🔐 Usuarios de Prueba

### DUENO (Administrador)
- **Usuario**: Juan
- **Contraseña**: password
- **Permisos**: CRUD completo de productos

### CLIENTE (Usuario)
- **Usuario**: Ana
- **Contraseña**: password
- **Permisos**: Solo visualización de productos

## 🏗️ Estructura del Proyecto

```
src/
├── components/
│   ├── Hero.jsx                    # Componente de bienvenida
│   ├── ProtectedRoute.jsx          # Componente de protección de rutas
│   └── layouts/
│       ├── Header.jsx              # Header con navegación
│       └── Footer.jsx              # Footer informativo
├── contexts/
│   └── AuthContext.jsx             # Contexto de autenticación
├── pages/
│   ├── LoginPage.jsx               # Página de login
│   ├── ProductosPage.jsx           # Lista de productos
│   ├── ProductoFormPage.jsx        # Formulario de productos
│   └── UnauthorizedPage.jsx        # Página de acceso denegado
├── services/
│   ├── authService.js              # Servicios de autenticación
│   └── productoService.js          # Servicios de productos
├── styles/                         # Estilos modulares
│   ├── index.css                   # Estilos globales
│   ├── utils/                      # Utilidades CSS
│   ├── layouts/                    # Estilos de layout
│   ├── pages/                      # Estilos de páginas
│   └── components/                 # Estilos de componentes
├── utils/
│   └── storageCleaner.js           # Utilidades de limpieza de storage
├── config/
│   └── api.js                      # Configuración de API
├── App.jsx                         # Componente principal
└── main.jsx                        # Punto de entrada
```

## 🎯 Funcionalidades

### Para DUENO:
- ✅ Ver lista de productos
- ✅ Crear nuevos productos
- ✅ Editar productos existentes
- ✅ Eliminar productos
- ✅ Acceso completo al sistema

### Para CLIENTE:
- ✅ Ver lista de productos
- ❌ No puede crear productos
- ❌ No puede editar productos
- ❌ No puede eliminar productos

## 🔧 Configuración

### Variables de Entorno
Crea un archivo `.env` en la raíz del proyecto:

```env
VITE_API_BASE_URL=http://localhost:8086/api
```

### Configuración de API
Modifica `src/config/api.js` si necesitas cambiar la URL del backend:

```javascript
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8086/api';
```

## 🎨 Personalización de Estilos

### Variables CSS
Modifica `src/styles/utils/variables.css` para cambiar colores, tipografía y espaciado:

```css
:root {
  --primary-color: #667eea;
  --secondary-color: #764ba2;
  --font-family: 'Inter', sans-serif;
  /* ... más variables */
}
```

### Animaciones
Añade nuevas animaciones en `src/styles/utils/animations.css`:

```css
@keyframes miAnimacion {
  0% { opacity: 0; transform: translateY(20px); }
  100% { opacity: 1; transform: translateY(0); }
}

.miClase {
  animation: miAnimacion 0.6s ease-out;
}
```

## 🚀 Scripts Disponibles

- `npm run dev`: Ejecuta el servidor de desarrollo
- `npm run build`: Construye la aplicación para producción
- `npm run preview`: Previsualiza la build de producción
- `npm run lint`: Ejecuta el linter de ESLint

## 🔍 Debug y Desarrollo

### Herramientas de Debug
El sistema incluye herramientas de debug en la página de login:
- **Limpiar Storage**: Elimina datos corruptos del localStorage
- **Debug Info**: Muestra información del estado de autenticación

### Logs de Desarrollo
Los logs de desarrollo están habilitados para ayudar en el debugging:
- Errores de autenticación
- Errores de API
- Estado de tokens JWT

## 📱 Responsive Design

El sistema es completamente responsive y se adapta a:

- **Móviles**: < 576px
- **Tablets**: 576px - 992px
- **Desktop**: > 992px

### Breakpoints Utilizados
```css
--breakpoint-sm: 576px;
--breakpoint-md: 768px;
--breakpoint-lg: 992px;
--breakpoint-xl: 1200px;
--breakpoint-2xl: 1400px;
```

## ♿ Accesibilidad

- **Navegación por teclado**: Todas las funcionalidades son accesibles por teclado
- **Lectores de pantalla**: Etiquetas ARIA y estructura semántica
- **Contraste**: Colores con suficiente contraste para legibilidad
- **Reducción de movimiento**: Respeta las preferencias de `prefers-reduced-motion`

## 🌙 Modo Oscuro

El sistema soporta automáticamente el modo oscuro basado en las preferencias del sistema:

```css
@media (prefers-color-scheme: dark) {
  :root {
    --bg-primary: #1a202c;
    --text-primary: #f7fafc;
    /* ... más variables */
  }
}
```

## 🔒 Seguridad

- **JWT Tokens**: Autenticación segura con tokens JWT
- **Validación de Roles**: Verificación de permisos en frontend y backend
- **Protección de Rutas**: Rutas protegidas según el rol del usuario
- **Limpieza de Storage**: Utilidades para limpiar datos corruptos

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 📞 Soporte

Si tienes problemas o preguntas:

1. Revisa la documentación
2. Verifica los logs de desarrollo
3. Usa las herramientas de debug incluidas
4. Abre un issue en el repositorio

---

**Desarrollado para el curso de Desarrollo de Aplicaciones Web - 4to Ciclo**

// Roles de usuario
export const ROLES = {
  DUENO: 'ROLE_DUENO',
  CLIENTE: 'ROLE_CLIENTE'
};

// Nombres de visualización para los roles
export const ROLE_DISPLAY_NAMES = {
  [ROLES.DUENO]: 'Dueño',
  [ROLES.CLIENTE]: 'Cliente'
};

// Colores de badges para los roles
export const ROLE_BADGE_COLORS = {
  [ROLES.DUENO]: 'primary',
  [ROLES.CLIENTE]: 'secondary'
};

// Iconos para los roles
export const ROLE_ICONS = {
  [ROLES.DUENO]: 'bi-shield-check',
  [ROLES.CLIENTE]: 'bi-person'
};

// Rutas de la aplicación
export const ROUTES = {
  HOME: '/',
  LOGIN: '/login',
  PRODUCTOS: '/productos',
  PRODUCTO_CREAR: '/productos/crear',
  PRODUCTO_EDITAR: '/productos/editar',
  UNAUTHORIZED: '/unauthorized'
};

// Configuración de la API
export const API_CONFIG = {
  TIMEOUT: 10000,
  RETRY_ATTEMPTS: 3
};

// Mensajes de error comunes
export const ERROR_MESSAGES = {
  UNAUTHORIZED: 'No tienes permisos para acceder a este recurso.',
  NETWORK_ERROR: 'Error de conexión. Verifica tu conexión a internet.',
  SERVER_ERROR: 'Error del servidor. Inténtalo de nuevo más tarde.',
  VALIDATION_ERROR: 'Por favor, verifica los datos ingresados.'
};

// Mensajes de éxito comunes
export const SUCCESS_MESSAGES = {
  PRODUCTO_CREADO: 'Producto creado correctamente',
  PRODUCTO_ACTUALIZADO: 'Producto actualizado correctamente',
  PRODUCTO_ELIMINADO: 'Producto eliminado correctamente',
  LOGIN_EXITOSO: 'Inicio de sesión exitoso'
}; 
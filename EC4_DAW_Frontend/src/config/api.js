// Configuración del backend
export const API_CONFIG = {
  BASE_URL: 'http://localhost:8086/api',
  ENDPOINTS: {
    AUTH: {
      LOGIN: '/auth/login'
    },
    PRODUCTOS: {
      BASE: '/productos',
      BY_ID: (id) => `/productos/${id}`
    }
  }
};

// Configuración de headers por defecto
export const DEFAULT_HEADERS = {
  'Content-Type': 'application/json'
};

// Configuración de timeout
export const API_TIMEOUT = 10000; // 10 segundos 
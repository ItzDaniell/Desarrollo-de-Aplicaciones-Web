import axios from 'axios';
import { API_CONFIG, DEFAULT_HEADERS, API_TIMEOUT } from '../config/api';

// Crear instancia de axios con configuración base
const api = axios.create({
  baseURL: API_CONFIG.BASE_URL,
  headers: DEFAULT_HEADERS,
  timeout: API_TIMEOUT
});

// Función para validar formato de JWT
const isValidJwtFormat = (token) => {
  if (!token || typeof token !== 'string') {
    return false;
  }
  // JWT debe tener exactamente 2 puntos (header.payload.signature)
  const parts = token.split('.');
  return parts.length === 3;
};

// Interceptor para agregar el token JWT a todas las peticiones
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token && isValidJwtFormat(token)) {
      config.headers.Authorization = `Bearer ${token}`;
    } else if (token && !isValidJwtFormat(token)) {
      // Si el token no es válido, limpiarlo
      console.warn('Token JWT inválido encontrado, limpiando...');
      localStorage.removeItem('token');
      localStorage.removeItem('user');
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Interceptor para manejar errores de autenticación
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Token expirado o inválido
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

class AuthService {
  // Login del usuario
  async login(credentials) {
    try {
      const response = await api.post(API_CONFIG.ENDPOINTS.AUTH.LOGIN, credentials);
      const { token, usuario } = response.data;
      
      // Validar que el token recibido sea válido
      if (!token || !isValidJwtFormat(token)) {
        console.error('Token JWT inválido recibido del backend');
        return { 
          success: false, 
          error: 'Token de autenticación inválido' 
        };
      }
      
      // Guardar token y datos del usuario
      localStorage.setItem('token', token);
      localStorage.setItem('user', JSON.stringify(usuario));
      
      return { success: true, user: usuario };
    } catch (error) {
      console.error('Error en login:', error);
      return { 
        success: false, 
        error: error.response?.data?.message || 'Error de autenticación' 
      };
    }
  }

  // Logout del usuario
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/login';
  }

  // Verificar si el usuario está autenticado
  isAuthenticated() {
    const token = localStorage.getItem('token');
    return token && isValidJwtFormat(token);
  }

  // Obtener el usuario actual
  getCurrentUser() {
    try {
      const userStr = localStorage.getItem('user');
      if (!userStr || userStr === 'undefined' || userStr === 'null') {
        return null;
      }
      return JSON.parse(userStr);
    } catch (error) {
      console.error('Error al parsear el usuario desde localStorage:', error);
      // Limpiar datos corruptos
      localStorage.removeItem('user');
      return null;
    }
  }

  // Obtener el rol del usuario actual
  getUserRole() {
    const user = this.getCurrentUser();
    return user?.role || null;
  }

  // Verificar si el usuario tiene un rol específico
  hasRole(role) {
    const userRole = this.getUserRole();
    return userRole === role;
  }

  // Verificar si el usuario es DUENO
  isOwner() {
    return this.hasRole('ROLE_DUENO');
  }

  // Verificar si el usuario es CLIENTE
  isClient() {
    return this.hasRole('ROLE_CLIENTE');
  }

  // Obtener el token JWT
  getToken() {
    const token = localStorage.getItem('token');
    return token && isValidJwtFormat(token) ? token : null;
  }

  // Verificar si el token está expirado (opcional, el backend maneja esto)
  isTokenExpired() {
    const token = this.getToken();
    if (!token) return true;
    
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.exp * 1000 < Date.now();
    } catch (error) {
      return true;
    }
  }

  // Limpiar datos corruptos
  clearCorruptedData() {
    const token = localStorage.getItem('token');
    if (token && !isValidJwtFormat(token)) {
      console.warn('Limpiando datos corruptos del localStorage');
      localStorage.removeItem('token');
      localStorage.removeItem('user');
    }
  }
}

export default new AuthService();

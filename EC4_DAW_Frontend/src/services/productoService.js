import axios from 'axios';
import { API_CONFIG, DEFAULT_HEADERS, API_TIMEOUT } from '../config/api';

// Crear instancia de axios con configuración base
const api = axios.create({
  baseURL: API_CONFIG.BASE_URL,
  headers: DEFAULT_HEADERS,
  timeout: API_TIMEOUT
});

// Interceptor para agregar el token JWT a todas las peticiones
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
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

class ProductoService {
  // Obtener todos los productos
  async getProductos() {
    try {
      const response = await api.get(API_CONFIG.ENDPOINTS.PRODUCTOS.BASE);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error al obtener productos:', error);
      return { 
        success: false, 
        error: error.response?.data?.message || 'Error al obtener productos' 
      };
    }
  }

  // Obtener un producto por ID
  async getProducto(id) {
    try {
      const response = await api.get(API_CONFIG.ENDPOINTS.PRODUCTOS.BY_ID(id));
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error al obtener producto:', error);
      return { 
        success: false, 
        error: error.response?.data?.message || 'Error al obtener producto' 
      };
    }
  }

  // Crear un nuevo producto
  async createProducto(producto) {
    try {
      const response = await api.post(API_CONFIG.ENDPOINTS.PRODUCTOS.BASE, producto);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error al crear producto:', error);
      return { 
        success: false, 
        error: error.response?.data?.message || 'Error al crear producto' 
      };
    }
  }

  // Actualizar un producto
  async updateProducto(id, producto) {
    try {
      const response = await api.put(API_CONFIG.ENDPOINTS.PRODUCTOS.BY_ID(id), producto);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error al actualizar producto:', error);
      return { 
        success: false, 
        error: error.response?.data?.message || 'Error al actualizar producto' 
      };
    }
  }

  // Eliminar un producto
  async deleteProducto(id) {
    try {
      await api.delete(API_CONFIG.ENDPOINTS.PRODUCTOS.BY_ID(id));
      return { success: true };
    } catch (error) {
      console.error('Error al eliminar producto:', error);
      return { 
        success: false, 
        error: error.response?.data?.message || 'Error al eliminar producto' 
      };
    }
  }
}

export default new ProductoService(); 
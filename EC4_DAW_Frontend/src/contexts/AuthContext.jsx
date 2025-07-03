import { createContext, useContext, useState, useEffect } from 'react';
import authService from '../services/authService';

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth debe ser usado dentro de un AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Verificar si hay un usuario autenticado al cargar la app
  useEffect(() => {
    const checkAuth = () => {
      try {
        // Limpiar datos corruptos primero
        authService.clearCorruptedData();
        
        if (authService.isAuthenticated()) {
          const currentUser = authService.getCurrentUser();
          if (currentUser) {
            setUser(currentUser);
          } else {
            // Si no hay usuario válido pero hay token, limpiar todo
            authService.logout();
          }
        }
      } catch (error) {
        console.error('Error al verificar autenticación:', error);
        // En caso de error, limpiar todo
        authService.logout();
      } finally {
        setLoading(false);
      }
    };

    checkAuth();
  }, []);

  // Función de login
  const login = async (credentials) => {
    setLoading(true);
    const result = await authService.login(credentials);
    
    if (result.success) {
      setUser(result.user);
    }
    
    setLoading(false);
    return result;
  };

  // Función de logout
  const logout = () => {
    authService.logout();
    setUser(null);
  };

  // Verificar si el usuario está autenticado
  const isAuthenticated = () => {
    return !!user;
  };

  // Obtener el rol del usuario
  const getUserRole = () => {
    return user?.role || null;
  };

  // Verificar si el usuario tiene un rol específico
  const hasRole = (role) => {
    return user?.role === role;
  };

  // Verificar si el usuario es DUENO
  const isOwner = () => {
    return hasRole('ROLE_DUENO');
  };

  // Verificar si el usuario es CLIENTE
  const isClient = () => {
    return hasRole('ROLE_CLIENTE');
  };

  const value = {
    user,
    loading,
    login,
    logout,
    isAuthenticated,
    getUserRole,
    hasRole,
    isOwner,
    isClient,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}; 
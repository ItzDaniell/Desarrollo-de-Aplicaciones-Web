// Utilidad para limpiar y verificar el localStorage

export const clearAllAuthData = () => {
  console.log('Limpiando todos los datos de autenticación...');
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  console.log('Datos de autenticación limpiados');
};

export const validateStoredData = () => {
  const token = localStorage.getItem('token');
  const user = localStorage.getItem('user');
  
  console.log('Validando datos almacenados...');
  
  // Validar token
  if (token) {
    const isValidToken = token.split('.').length === 3;
    console.log('Token válido:', isValidToken);
    
    if (!isValidToken) {
      console.warn('Token inválido encontrado, limpiando...');
      localStorage.removeItem('token');
    }
  }
  
  // Validar usuario
  if (user) {
    try {
      const parsedUser = JSON.parse(user);
      console.log('Usuario válido:', !!parsedUser);
    } catch (error) {
      console.warn('Usuario inválido encontrado, limpiando...');
      localStorage.removeItem('user');
    }
  }
  
  console.log('Validación completada');
};

export const getStorageInfo = () => {
  const token = localStorage.getItem('token');
  const user = localStorage.getItem('user');
  
  return {
    hasToken: !!token,
    tokenLength: token ? token.length : 0,
    hasUser: !!user,
    userLength: user ? user.length : 0,
    tokenValid: token ? token.split('.').length === 3 : false
  };
}; 
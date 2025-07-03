import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { clearAllAuthData, getStorageInfo } from '../utils/storageCleaner';
import styles from '../styles/pages/Login.module.css';

const LoginPage = () => {
  const [credentials, setCredentials] = useState({
    username: '',
    password: ''
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [debugInfo, setDebugInfo] = useState(null);
  
  const { login, isAuthenticated } = useAuth();
  const navigate = useNavigate();

  // Si ya está autenticado, redirigir a productos
  if (isAuthenticated()) {
    navigate('/productos');
    return null;
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials(prev => ({
      ...prev,
      [name]: value
    }));
    // Limpiar error cuando el usuario empiece a escribir
    if (error) setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const result = await login(credentials);
      
      if (result.success) {
        // Redirigir a la página de productos después del login exitoso
        navigate('/productos');
      } else {
        setError(result.error);
      }
    } catch (err) {
      setError('Error de conexión. Intente nuevamente.');
    } finally {
      setLoading(false);
    }
  };

  const handleClearStorage = () => {
    clearAllAuthData();
    setDebugInfo(null);
    alert('localStorage limpiado. Recarga la página.');
  };

  const handleShowDebugInfo = () => {
    const info = getStorageInfo();
    setDebugInfo(info);
  };

  return (
    <div className={styles.loginContainer}>
      {/* Elementos decorativos de fondo */}
      <div className={styles.backgroundDecorations}>
        <div className={styles.decorationCircle}></div>
        <div className={styles.decorationCircle}></div>
        <div className={styles.decorationCircle}></div>
      </div>

      <div className={`container ${styles.contentContainer}`}>
        <div className="row justify-content-center">
          <div className="col-md-6 col-lg-4">
            <div className={`card border-0 shadow-lg ${styles.loginCard}`}>
              <div className={`card-body p-5 ${styles.cardBody}`}>
                {/* Logo y título */}
                <div className={`text-center mb-4 ${styles.logoSection}`}>
                  <div className="mb-3">
                    <i className={`bi bi-shield-lock display-1 text-primary ${styles.logoIcon}`}></i>
                  </div>
                  <h2 className={`fw-bold mb-2 ${styles.title}`}>
                    Bienvenido
                  </h2>
                  <p className={`text-muted ${styles.subtitle}`}>Accede a tu cuenta para continuar</p>
                </div>
                
                <form onSubmit={handleSubmit}>
                  <div className={`mb-4 ${styles.formGroup}`}>
                    <label htmlFor="username" className={`form-label fw-semibold text-dark ${styles.formLabel}`}>
                      <i className={`bi bi-person me-2 text-primary ${styles.formLabelIcon}`}></i>
                      Usuario
                    </label>
                    <div className={`input-group ${styles.inputGroup}`}>
                      <span className={`input-group-text bg-light border-end-0 ${styles.inputGroupText}`}>
                        <i className={`bi bi-person text-muted ${styles.inputGroupIcon}`}></i>
                      </span>
                      <input
                        id="username"
                        name="username"
                        type="text"
                        className={`form-control border-start-0 ${styles.formControl}`}
                        required
                        placeholder="Ingresa tu usuario"
                        value={credentials.username}
                        onChange={handleChange}
                      />
                    </div>
                  </div>
                  
                  <div className={`mb-4 ${styles.formGroup}`}>
                    <label htmlFor="password" className={`form-label fw-semibold text-dark ${styles.formLabel}`}>
                      <i className={`bi bi-lock me-2 text-primary ${styles.formLabelIcon}`}></i>
                      Contraseña
                    </label>
                    <div className={`input-group ${styles.inputGroup}`}>
                      <span className={`input-group-text bg-light border-end-0 ${styles.inputGroupText}`}>
                        <i className={`bi bi-lock text-muted ${styles.inputGroupIcon}`}></i>
                      </span>
                      <input
                        id="password"
                        name="password"
                        type="password"
                        className={`form-control border-start-0 ${styles.formControl}`}
                        required
                        placeholder="Ingresa tu contraseña"
                        value={credentials.password}
                        onChange={handleChange}
                      />
                    </div>
                  </div>

                  {error && (
                    <div className={`alert alert-danger border-0 shadow-sm ${styles.errorAlert}`} role="alert">
                      <div className={`d-flex align-items-center ${styles.errorContent}`}>
                        <i className={`bi bi-exclamation-triangle-fill me-2 text-danger ${styles.errorIcon}`}></i>
                        <span className={`fw-medium ${styles.errorText}`}>{error}</span>
                      </div>
                    </div>
                  )}

                  <div className="d-grid mb-4">
                    <button
                      type="submit"
                      className={`btn btn-primary btn-lg fw-semibold border-0 shadow-sm ${styles.submitButton}`}
                      disabled={loading}
                    >
                      {loading ? (
                        <div className={`d-flex align-items-center justify-content-center ${styles.submitButtonContent}`}>
                          <div className={`spinner-border spinner-border-sm me-2 ${styles.loadingSpinner}`} role="status" aria-hidden="true"></div>
                          <span>Iniciando sesión...</span>
                        </div>
                      ) : (
                        <div className={`d-flex align-items-center justify-content-center ${styles.submitButtonContent}`}>
                          <i className={`bi bi-box-arrow-in-right me-2 ${styles.submitButtonIcon}`}></i>
                          <span>Iniciar Sesión</span>
                        </div>
                      )}
                    </button>
                  </div>
                </form>

                {/* Información de usuarios de prueba */}
                <div className={`text-center ${styles.userInfoSection}`}>
                  <div className={`p-3 rounded-3 ${styles.userInfoContainer}`}>
                    <p className={`text-muted small mb-2 fw-semibold ${styles.userInfoTitle}`}>
                      <i className={`bi bi-info-circle me-1 ${styles.userInfoIcon}`}></i>
                      Usuarios de prueba:
                    </p>
                    <div className={`row g-2 ${styles.userInfoGrid}`}>
                      <div className="col-6">
                        <div className={`p-2 rounded ${styles.userInfoCard} ${styles.owner}`}>
                          <small className={`fw-bold text-primary ${styles.userInfoRole} ${styles.owner}`}>DUENO</small><br/>
                          <small className={`text-muted ${styles.userInfoCredentials}`}>Juan / password</small>
                        </div>
                      </div>
                      <div className="col-6">
                        <div className={`p-2 rounded ${styles.userInfoCard} ${styles.client}`}>
                          <small className={`fw-bold text-purple ${styles.userInfoRole} ${styles.client}`}>CLIENTE</small><br/>
                          <small className={`text-muted ${styles.userInfoCredentials}`}>Ana / password</small>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                {/* Botones de debug temporales */}
                <div className={`mt-4 pt-3 border-top ${styles.debugSection}`}>
                  <div className={`d-flex gap-2 justify-content-center ${styles.debugButtons}`}>
                    <button
                      type="button"
                      className={`btn btn-outline-warning btn-sm rounded-pill ${styles.debugButton}`}
                      onClick={handleClearStorage}
                    >
                      <i className="bi bi-trash me-1"></i>
                      Limpiar Storage
                    </button>
                    <button
                      type="button"
                      className={`btn btn-outline-info btn-sm rounded-pill ${styles.debugButton}`}
                      onClick={handleShowDebugInfo}
                    >
                      <i className="bi bi-bug me-1"></i>
                      Debug Info
                    </button>
                  </div>
                  
                  {debugInfo && (
                    <div className={`mt-3 p-3 rounded-3 ${styles.debugInfo}`}>
                      <strong className={`text-dark ${styles.debugInfoTitle}`}>Debug Info:</strong><br/>
                      <span className={`text-muted ${styles.debugInfoContent}`}>
                        <div className={styles.debugInfoItem}>
                          Token: {debugInfo.hasToken ? '✅ Sí' : '❌ No'} 
                          {debugInfo.hasToken && ` (${debugInfo.tokenLength} chars, válido: ${debugInfo.tokenValid ? '✅' : '❌'})`}
                        </div>
                        <div className={styles.debugInfoItem}>
                          User: {debugInfo.hasUser ? '✅ Sí' : '❌ No'}
                          {debugInfo.hasUser && ` (${debugInfo.userLength} chars)`}
                        </div>
                      </span>
                    </div>
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;

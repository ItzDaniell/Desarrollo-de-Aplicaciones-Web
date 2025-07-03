import { Link } from 'react-router-dom';

const UnauthorizedPage = () => {
  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center" 
         style={{ 
           background: 'linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%)',
           position: 'relative',
           overflow: 'hidden'
         }}>
      
      {/* Elementos decorativos de fondo */}
      <div className="position-absolute w-100 h-100" style={{ top: 0, left: 0, zIndex: 0 }}>
        <div className="position-absolute" style={{ top: '15%', left: '10%', width: '150px', height: '150px', 
             background: 'rgba(255,255,255,0.1)', borderRadius: '50%', animation: 'float 6s ease-in-out infinite' }}></div>
        <div className="position-absolute" style={{ top: '50%', right: '20%', width: '100px', height: '100px', 
             background: 'rgba(255,255,255,0.08)', borderRadius: '50%', animation: 'float 8s ease-in-out infinite' }}></div>
        <div className="position-absolute" style={{ bottom: '30%', left: '30%', width: '80px', height: '80px', 
             background: 'rgba(255,255,255,0.06)', borderRadius: '50%', animation: 'float 4s ease-in-out infinite' }}></div>
      </div>

      <div className="container position-relative" style={{ zIndex: 1 }}>
        <div className="row justify-content-center">
          <div className="col-md-8 col-lg-6">
            <div className="card border-0 shadow-lg" style={{ 
              backdropFilter: 'blur(10px)', 
              backgroundColor: 'rgba(255, 255, 255, 0.95)',
              borderRadius: '25px'
            }}>
              <div className="card-body text-center p-5">
                {/* Icono de error */}
                <div className="mb-4">
                  <div className="position-relative d-inline-block">
                    <div className="position-absolute w-100 h-100 rounded-circle" 
                         style={{ 
                           background: 'linear-gradient(45deg, #ff6b6b, #ee5a52)',
                           animation: 'pulse 2s infinite'
                         }}></div>
                    <i className="bi bi-shield-exclamation display-1 text-white position-relative" 
                       style={{ zIndex: 1 }}></i>
                  </div>
                </div>

                {/* Título y mensaje */}
                <h1 className="display-6 fw-bold mb-3" style={{ 
                  background: 'linear-gradient(45deg, #ff6b6b, #ee5a52)', 
                  WebkitBackgroundClip: 'text', 
                  WebkitTextFillColor: 'transparent'
                }}>
                  Acceso Denegado
                </h1>
                
                <p className="text-muted fs-5 mb-4">
                  No tienes permisos para acceder a esta página. 
                  Tu rol actual no te permite realizar esta acción.
                </p>

                {/* Información adicional */}
                <div className="row g-3 mb-5">
                  <div className="col-md-6">
                    <div className="p-3 rounded-3" style={{ backgroundColor: '#fff3cd' }}>
                      <i className="bi bi-exclamation-triangle text-warning fs-4 mb-2"></i>
                      <h6 className="fw-bold text-warning">Acceso Restringido</h6>
                      <p className="small text-muted mb-0">
                        Esta funcionalidad requiere permisos especiales
                      </p>
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="p-3 rounded-3" style={{ backgroundColor: '#d1ecf1' }}>
                      <i className="bi bi-info-circle text-info fs-4 mb-2"></i>
                      <h6 className="fw-bold text-info">Contacta al Administrador</h6>
                      <p className="small text-muted mb-0">
                        Solicita acceso si necesitas esta funcionalidad
                      </p>
                    </div>
                  </div>
                </div>

                {/* Roles disponibles */}
                <div className="mb-5">
                  <h6 className="fw-bold text-dark mb-3">
                    <i className="bi bi-people me-2"></i>
                    Roles Disponibles:
                  </h6>
                  <div className="row g-3">
                    <div className="col-6">
                      <div className="p-3 rounded-3 border" style={{ backgroundColor: '#e8f5e8' }}>
                        <div className="d-flex align-items-center mb-2">
                          <i className="bi bi-shield-check text-success me-2"></i>
                          <span className="fw-bold text-success">DUENO</span>
                        </div>
                        <p className="small text-muted mb-0">
                          Acceso completo a todas las funcionalidades
                        </p>
                      </div>
                    </div>
                    <div className="col-6">
                      <div className="p-3 rounded-3 border" style={{ backgroundColor: '#e3f2fd' }}>
                        <div className="d-flex align-items-center mb-2">
                          <i className="bi bi-person text-info me-2"></i>
                          <span className="fw-bold text-info">CLIENTE</span>
                        </div>
                        <p className="small text-muted mb-0">
                          Solo visualización de productos
                        </p>
                      </div>
                    </div>
                  </div>
                </div>

                {/* Botones de acción */}
                <div className="d-flex gap-3 justify-content-center flex-wrap">
                  <Link 
                    to="/productos" 
                    className="btn btn-primary btn-lg px-4 border-0 shadow-sm"
                    style={{ 
                      borderRadius: '15px',
                      background: 'linear-gradient(45deg, #667eea, #764ba2)',
                      transition: 'all 0.3s ease'
                    }}
                    onMouseOver={(e) => e.target.style.transform = 'translateY(-2px)'}
                    onMouseOut={(e) => e.target.style.transform = 'translateY(0)'}
                  >
                    <i className="bi bi-box me-2"></i>
                    Ver Productos
                  </Link>
                  
                  <Link 
                    to="/login" 
                    className="btn btn-outline-secondary btn-lg px-4 border-0 shadow-sm"
                    style={{ 
                      borderRadius: '15px',
                      backgroundColor: '#f8f9fa',
                      transition: 'all 0.3s ease'
                    }}
                    onMouseOver={(e) => {
                      e.target.style.transform = 'translateY(-2px)';
                      e.target.style.backgroundColor = '#6c757d';
                      e.target.style.color = 'white';
                    }}
                    onMouseOut={(e) => {
                      e.target.style.transform = 'translateY(0)';
                      e.target.style.backgroundColor = '#f8f9fa';
                      e.target.style.color = '#6c757d';
                    }}
                  >
                    <i className="bi bi-arrow-left me-2"></i>
                    Volver al Login
                  </Link>
                </div>

                {/* Información de ayuda */}
                <div className="mt-4 pt-4 border-top">
                  <div className="p-3 rounded-3" style={{ backgroundColor: '#f8f9fa' }}>
                    <p className="small text-muted mb-2">
                      <i className="bi bi-lightbulb me-2 text-warning"></i>
                      <strong>Consejo:</strong> Si crees que deberías tener acceso, contacta al administrador del sistema.
                    </p>
                    <p className="small text-muted mb-0">
                      <i className="bi bi-clock me-2 text-info"></i>
                      Código de error: 403 - Forbidden
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Estilos CSS */}
      <style jsx>{`
        @keyframes float {
          0%, 100% { transform: translateY(0px); }
          50% { transform: translateY(-20px); }
        }
        
        @keyframes pulse {
          0% { transform: scale(1); opacity: 0.8; }
          50% { transform: scale(1.1); opacity: 0.6; }
          100% { transform: scale(1); opacity: 0.8; }
        }
        
        .card {
          transition: all 0.3s ease;
        }
        
        .card:hover {
          transform: translateY(-5px);
          box-shadow: 0 25px 50px rgba(0,0,0,0.15) !important;
        }
        
        .btn:hover {
          box-shadow: 0 8px 25px rgba(0,0,0,0.2);
        }
      `}</style>
    </div>
  );
};

export default UnauthorizedPage; 
import { useAuth } from '../../contexts/AuthContext';

const Footer = () => {
  const { isAuthenticated } = useAuth();
  
  const currentYear = new Date().getFullYear();

  return (
    <footer className="border-0" 
            style={{ 
              background: 'linear-gradient(135deg, #2c3e50 0%, #34495e 100%)',
              color: 'white',
              marginTop: 'auto'
            }}>
      <div className="container py-5">
        <div className="row g-4">
          {/* Información principal */}
          <div className="col-lg-4 col-md-6">
            <div className="mb-4">
              <div className="d-flex align-items-center mb-3">
                <i className="bi bi-shield-lock fs-2 me-2" 
                   style={{ 
                     background: 'linear-gradient(45deg, #667eea, #764ba2)', 
                     WebkitBackgroundClip: 'text', 
                     WebkitTextFillColor: 'transparent' 
                   }}></i>
                <span className="fs-4 fw-bold">ProductManager</span>
              </div>
              <p className="text-light opacity-75 mb-3">
                Sistema de gestión de productos con autenticación JWT y control de acceso basado en roles.
                Desarrollado con React y conectado a un backend Spring Security.
              </p>
              <div className="d-flex gap-3">
                <div className="text-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-shield-check text-success fs-5"></i>
                  <div className="small mt-1">Seguro</div>
                </div>
                <div className="text-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-lightning text-warning fs-5"></i>
                  <div className="small mt-1">Rápido</div>
                </div>
                <div className="text-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-phone text-info fs-5"></i>
                  <div className="small mt-1">Responsive</div>
                </div>
              </div>
            </div>
          </div>

          {/* Enlaces rápidos */}
          <div className="col-lg-2 col-md-6">
            <h6 className="fw-bold mb-3 text-white">
              <i className="bi bi-link-45deg me-2"></i>
              Enlaces
            </h6>
            <ul className="list-unstyled">
              {isAuthenticated() ? (
                <>
                  <li className="mb-2">
                    <a href="/productos" className="text-light text-decoration-none opacity-75" 
                       style={{ transition: 'all 0.3s ease' }}
                       onMouseOver={(e) => {
                         e.target.style.opacity = '1';
                         e.target.style.transform = 'translateX(5px)';
                       }}
                       onMouseOut={(e) => {
                         e.target.style.opacity = '0.75';
                         e.target.style.transform = 'translateX(0)';
                       }}>
                      <i className="bi bi-box me-2"></i>
                      Productos
                    </a>
                  </li>
                  <li className="mb-2">
                    <a href="/productos/crear" className="text-light text-decoration-none opacity-75"
                       style={{ transition: 'all 0.3s ease' }}
                       onMouseOver={(e) => {
                         e.target.style.opacity = '1';
                         e.target.style.transform = 'translateX(5px)';
                       }}
                       onMouseOut={(e) => {
                         e.target.style.opacity = '0.75';
                         e.target.style.transform = 'translateX(0)';
                       }}>
                      <i className="bi bi-plus-circle me-2"></i>
                      Crear Producto
                    </a>
                  </li>
                </>
              ) : (
                <li className="mb-2">
                  <a href="/login" className="text-light text-decoration-none opacity-75"
                     style={{ transition: 'all 0.3s ease' }}
                     onMouseOver={(e) => {
                       e.target.style.opacity = '1';
                       e.target.style.transform = 'translateX(5px)';
                     }}
                     onMouseOut={(e) => {
                       e.target.style.opacity = '0.75';
                       e.target.style.transform = 'translateX(0)';
                     }}>
                    <i className="bi bi-box-arrow-in-right me-2"></i>
                    Iniciar Sesión
                  </a>
                </li>
              )}
            </ul>
          </div>

          {/* Tecnologías */}
          <div className="col-lg-3 col-md-6">
            <h6 className="fw-bold mb-3 text-white">
              <i className="bi bi-gear me-2"></i>
              Tecnologías
            </h6>
            <div className="row g-2">
              <div className="col-6">
                <div className="d-flex align-items-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-braces text-primary me-2"></i>
                  <span className="small">React</span>
                </div>
              </div>
              <div className="col-6">
                <div className="d-flex align-items-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-bootstrap text-primary me-2"></i>
                  <span className="small">Bootstrap</span>
                </div>
              </div>
              <div className="col-6">
                <div className="d-flex align-items-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-shield-lock text-success me-2"></i>
                  <span className="small">JWT</span>
                </div>
              </div>
              <div className="col-6">
                <div className="d-flex align-items-center p-2 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
                  <i className="bi bi-server text-warning me-2"></i>
                  <span className="small">Spring</span>
                </div>
              </div>
            </div>
          </div>

          {/* Información de contacto */}
          <div className="col-lg-3 col-md-6">
            <h6 className="fw-bold mb-3 text-white">
              <i className="bi bi-info-circle me-2"></i>
              Información
            </h6>
            <div className="mb-3">
              <div className="d-flex align-items-center mb-2">
                <i className="bi bi-person-badge text-info me-2"></i>
                <span className="small">Desarrollo de Aplicaciones Web</span>
              </div>
              <div className="d-flex align-items-center mb-2">
                <i className="bi bi-calendar text-info me-2"></i>
                <span className="small">4to Ciclo - 2024</span>
              </div>
              <div className="d-flex align-items-center">
                <i className="bi bi-code-slash text-info me-2"></i>
                <span className="small">EC4 - Frontend</span>
              </div>
            </div>
            
            {/* Estado del sistema */}
            <div className="p-3 rounded" style={{ backgroundColor: 'rgba(255,255,255,0.1)' }}>
              <div className="d-flex align-items-center justify-content-between mb-2">
                <span className="small fw-medium">Estado del Sistema:</span>
                <span className="badge bg-success rounded-pill">
                  <i className="bi bi-check-circle me-1"></i>
                  Activo
                </span>
              </div>
              <div className="d-flex align-items-center justify-content-between">
                <span className="small fw-medium">Usuarios:</span>
                <span className="badge bg-info rounded-pill">
                  <i className="bi bi-people me-1"></i>
                  {isAuthenticated() ? 'Conectado' : 'Desconectado'}
                </span>
              </div>
            </div>
          </div>
        </div>

        {/* Línea divisoria */}
        <hr className="my-4" style={{ borderColor: 'rgba(255,255,255,0.2)' }} />

        {/* Copyright */}
        <div className="row align-items-center">
          <div className="col-md-6">
            <p className="mb-0 text-light opacity-75">
              <i className="bi bi-c-circle me-1"></i>
              {currentYear} ProductManager. Todos los derechos reservados.
            </p>
          </div>
          <div className="col-md-6 text-md-end">
            <div className="d-flex gap-3 justify-content-md-end">
              <a href="#" className="text-light text-decoration-none opacity-75"
                 style={{ transition: 'all 0.3s ease' }}
                 onMouseOver={(e) => e.target.style.opacity = '1'}
                 onMouseOut={(e) => e.target.style.opacity = '0.75'}>
                <i className="bi bi-github fs-5"></i>
              </a>
              <a href="#" className="text-light text-decoration-none opacity-75"
                 style={{ transition: 'all 0.3s ease' }}
                 onMouseOver={(e) => e.target.style.opacity = '1'}
                 onMouseOut={(e) => e.target.style.opacity = '0.75'}>
                <i className="bi bi-linkedin fs-5"></i>
              </a>
              <a href="#" className="text-light text-decoration-none opacity-75"
                 style={{ transition: 'all 0.3s ease' }}
                 onMouseOver={(e) => e.target.style.opacity = '1'}
                 onMouseOut={(e) => e.target.style.opacity = '0.75'}>
                <i className="bi bi-envelope fs-5"></i>
              </a>
            </div>
          </div>
        </div>
      </div>

      {/* Estilos CSS */}
      <style jsx>{`
        footer {
          transition: all 0.3s ease;
        }
        
        .opacity-75 {
          transition: all 0.3s ease;
        }
        
        .opacity-75:hover {
          opacity: 1 !important;
        }
        
        .badge {
          transition: all 0.3s ease;
        }
        
        .badge:hover {
          transform: scale(1.05);
        }
      `}</style>
    </footer>
  );
};

export default Footer;

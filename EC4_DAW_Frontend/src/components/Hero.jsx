import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

const Hero = () => {
  const { isAuthenticated } = useAuth();

  return (
    <section className="bg-white text-dark py-5">
      <div className="container text-center">
        <h1 className="display-4 fw-bold mb-3 text-dark">
          Sistema de Gestión de Productos
        </h1>
        <p className="lead mb-4 text-muted">
          Gestiona tu inventario de manera eficiente y segura.<br/>
          Plataforma moderna con control de acceso por roles.
        </p>
        <div className="d-flex justify-content-center gap-3 mb-4 flex-wrap">
          {isAuthenticated() ? (
            <Link to="/productos" className="btn btn-primary btn-lg px-4 fw-semibold">
              <i className="bi bi-box me-2"></i>
              Ver Productos
            </Link>
          ) : (
            <>
              <Link to="/login" className="btn btn-primary btn-lg px-4 fw-semibold">
                <i className="bi bi-box-arrow-in-right me-2"></i>
                Iniciar Sesión
              </Link>
              <a href="#features" className="btn btn-outline-primary btn-lg px-4 fw-semibold">
                <i className="bi bi-info-circle me-2"></i>
                Saber Más
              </a>
            </>
          )}
        </div>
        <div className="row mt-5" id="features">
          <div className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm border-0">
              <div className="card-body text-center p-4">
                <i className="bi bi-shield-check text-primary fs-1 mb-3"></i>
                <h5 className="card-title fw-bold text-dark">Autenticación Segura</h5>
                <p className="card-text text-muted">JWT y control de acceso por roles para máxima seguridad.</p>
              </div>
            </div>
          </div>
          <div className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm border-0">
              <div className="card-body text-center p-4">
                <i className="bi bi-gear text-primary fs-1 mb-3"></i>
                <h5 className="card-title fw-bold text-dark">Gestión Completa</h5>
                <p className="card-text text-muted">CRUD de productos con interfaz intuitiva y validaciones.</p>
              </div>
            </div>
          </div>
          <div className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm border-0">
              <div className="card-body text-center p-4">
                <i className="bi bi-phone text-primary fs-1 mb-3"></i>
                <h5 className="card-title fw-bold text-dark">Responsive Design</h5>
                <p className="card-text text-muted">Funciona perfectamente en móviles, tablets y desktop.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Hero;

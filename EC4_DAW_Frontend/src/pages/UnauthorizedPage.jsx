import { Link } from 'react-router-dom';

const UnauthorizedPage = () => (
  <div className="container py-5 bg-light min-vh-100">
    <div className="row justify-content-center">
      <div className="col-md-8 col-lg-6">
        <div className="alert alert-warning text-center p-5 border-0 shadow">
          <i className="bi bi-exclamation-triangle display-4 text-warning mb-3"></i>
          <h2 className="fw-bold mb-3">Acceso Denegado</h2>
          <p className="mb-4">No tienes permisos para acceder a esta página.</p>
          <Link to="/" className="btn btn-primary">
            Volver al inicio
          </Link>
        </div>
      </div>
    </div>
  </div>
);

export default UnauthorizedPage; 
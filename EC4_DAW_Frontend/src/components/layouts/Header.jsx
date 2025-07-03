import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../contexts/AuthContext';
import { ROLES, ROLE_DISPLAY_NAMES, ROLE_BADGE_COLORS, ROLE_ICONS, ROUTES } from '../../utils/constants';

const Header = () => {
  const { user, logout, isAuthenticated, isOwner, isClient } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const getRoleBadgeColor = (role) => {
    return ROLE_BADGE_COLORS[role] || 'secondary';
  };

  const getRoleIcon = (role) => {
    return ROLE_ICONS[role] || 'bi-person';
  };

  const getRoleDisplayName = (role) => {
    return ROLE_DISPLAY_NAMES[role] || 'Usuario';
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm border-bottom">
      <div className="container">
        {/* Logo y marca */}
        <Link className="navbar-brand d-flex align-items-center fw-bold text-dark" to="/">
          <i className="bi bi-box-seam text-primary fs-3 me-2"></i>
          <span className="fs-4">ProductManager</span>
        </Link>

        {/* Botón hamburguesa */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Menú de navegación */}
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            {/* Enlace de productos - visible para todos los usuarios autenticados */}
            {isAuthenticated() && (
              <li className="nav-item">
                <Link 
                  className="nav-link text-dark fw-medium"
                  to="/productos"
                >
                  <i className="bi bi-box me-2"></i>
                  Productos
                </Link>
              </li>
            )}
            
            {/* Enlace de crear producto - solo para dueños */}
            {isAuthenticated() && isOwner() && (
              <li className="nav-item">
                <Link 
                  className="nav-link text-dark fw-medium"
                  to={ROUTES.PRODUCTO_CREAR}
                >
                  <i className="bi bi-plus-circle me-2"></i>
                  Crear Producto
                </Link>
              </li>
            )}
          </ul>

          {/* Información del usuario y logout */}
          {isAuthenticated() && user && (
            <div className="navbar-nav ms-auto">
              <div className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle d-flex align-items-center text-dark fw-medium"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <div className="d-flex align-items-center">
                    <div className="me-3">
                      <i className={`bi ${getRoleIcon(user.role)} fs-5 text-primary`}></i>
                    </div>
                    <div className="text-start">
                      <div className="fw-semibold">{user.username}</div>
                      <div className="small text-muted">
                        <span className={`badge bg-${getRoleBadgeColor(user.role)} rounded-pill`}>
                          {getRoleDisplayName(user.role)}
                        </span>
                      </div>
                    </div>
                  </div>
                </a>
                
                <ul className="dropdown-menu dropdown-menu-end shadow border-0">
                  <li>
                    <div className="dropdown-header fw-bold text-dark px-3 py-2">
                      <i className="bi bi-person-circle me-2 text-primary"></i>
                      Perfil de Usuario
                    </div>
                  </li>
                  <li><hr className="dropdown-divider" /></li>
                  <li>
                    <div className="px-3 py-2">
                      <div className="small text-muted mb-1">Usuario:</div>
                      <div className="fw-semibold text-dark">{user.username}</div>
                    </div>
                  </li>
                  <li>
                    <div className="px-3 py-2">
                      <div className="small text-muted mb-1">Rol:</div>
                      <div>
                        <span className={`badge bg-${getRoleBadgeColor(user.role)} rounded-pill`}>
                          <i className={`bi ${getRoleIcon(user.role)} me-1`}></i>
                          {getRoleDisplayName(user.role)}
                        </span>
                      </div>
                    </div>
                  </li>
                  <li><hr className="dropdown-divider" /></li>
                  <li>
                    <button
                      className="dropdown-item d-flex align-items-center fw-medium text-danger"
                      onClick={handleLogout}
                    >
                      <i className="bi bi-box-arrow-right me-2"></i>
                      Cerrar Sesión
                    </button>
                  </li>
                </ul>
              </div>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Header;

import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../contexts/AuthContext';
import styles from '../../styles/layouts/Header.module.css';

const Header = () => {
  const { user, logout, isAuthenticated } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const getRoleBadgeColor = (role) => {
    return role === 'DUENO' ? 'success' : 'info';
  };

  const getRoleIcon = (role) => {
    return role === 'DUENO' ? 'bi-shield-check' : 'bi-person';
  };

  return (
    <nav className={`navbar navbar-expand-lg border-0 shadow-sm ${styles.header}`}>
      <div className={`container ${styles.headerContainer}`}>
        {/* Logo y marca */}
        <Link className={`navbar-brand d-flex align-items-center fw-bold text-white ${styles.brand}`} to="/">
          <i className={`bi bi-shield-lock fs-3 me-2 ${styles.brandIcon}`}></i>
          <span className={`fs-4 ${styles.brandText}`}>ProductManager</span>
        </Link>

        {/* Botón hamburguesa */}
        <button
          className={`navbar-toggler border-0 ${styles.toggler}`}
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className={`navbar-toggler-icon ${styles.togglerIcon}`}></span>
        </button>

        {/* Menú de navegación */}
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className={`navbar-nav me-auto ${styles.navList}`}>
            {isAuthenticated() && (
              <li className="nav-item">
                <Link 
                  className={`nav-link text-white fw-medium ${styles.navLink}`}
                  to="/productos"
                >
                  <i className={`bi bi-box me-2 ${styles.navIcon}`}></i>
                  Productos
                </Link>
              </li>
            )}
          </ul>

          {/* Información del usuario y logout */}
          {isAuthenticated() && user && (
            <div className={`navbar-nav ms-auto ${styles.nav}`}>
              <div className={`nav-item dropdown ${styles.userDropdown}`}>
                <a
                  className={`nav-link dropdown-toggle d-flex align-items-center text-white fw-medium ${styles.userButton}`}
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <div className={`d-flex align-items-center ${styles.userInfo}`}>
                    <div className="me-3">
                      <i className={`bi ${getRoleIcon(user.role)} fs-5 ${styles.userIcon}`}></i>
                    </div>
                    <div className={`text-start ${styles.userDetails}`}>
                      <div className={`fw-semibold ${styles.userName}`}>{user.username}</div>
                      <div className="small opacity-75">
                        <span className={`badge bg-${getRoleBadgeColor(user.role)} rounded-pill ${styles.roleBadge} ${styles[`roleBadge${user.role}`]}`}>
                          {user.role}
                        </span>
                      </div>
                    </div>
                  </div>
                </a>
                
                <ul className={`dropdown-menu dropdown-menu-end border-0 shadow-lg ${styles.dropdownMenu}`}>
                  <li>
                    <div className={`dropdown-header fw-bold text-dark px-3 py-2 ${styles.dropdownHeader}`}>
                      <i className="bi bi-person-circle me-2"></i>
                      Perfil de Usuario
                    </div>
                  </li>
                  <li><hr className={`dropdown-divider ${styles.dropdownDivider}`} /></li>
                  <li>
                    <div className={`px-3 py-2 ${styles.userInfoItem}`}>
                      <div className={`small text-muted mb-1 ${styles.userInfoLabel}`}>Usuario:</div>
                      <div className={`fw-semibold text-dark ${styles.userInfoValue}`}>{user.username}</div>
                    </div>
                  </li>
                  <li>
                    <div className={`px-3 py-2 ${styles.userInfoItem}`}>
                      <div className={`small text-muted mb-1 ${styles.userInfoLabel}`}>Rol:</div>
                      <div>
                        <span className={`badge bg-${getRoleBadgeColor(user.role)} rounded-pill ${styles.roleBadge} ${styles[`roleBadge${user.role}`]}`}>
                          <i className={`bi ${getRoleIcon(user.role)} me-1`}></i>
                          {user.role}
                        </span>
                      </div>
                    </div>
                  </li>
                  <li><hr className={`dropdown-divider ${styles.dropdownDivider}`} /></li>
                  <li>
                    <button
                      className={`dropdown-item d-flex align-items-center fw-medium ${styles.dropdownItem}`}
                      onClick={handleLogout}
                    >
                      <i className={`bi bi-box-arrow-right me-2 text-danger ${styles.dropdownItemIcon}`}></i>
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

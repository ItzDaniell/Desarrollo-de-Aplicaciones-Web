import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import styles from '../styles/components/Hero.module.css';

const Hero = () => {
  const { isAuthenticated, user } = useAuth();

  return (
    <section className={styles.hero}>
      <div className={`container ${styles.heroContainer}`}>
        <div className={styles.heroContent}>
          <h1 className={styles.heroTitle}>
            Sistema de Gestión de Productos
          </h1>
          <p className={styles.heroSubtitle}>
            Gestiona tu inventario de manera eficiente y segura
          </p>
          <p className={styles.heroDescription}>
            Una plataforma moderna que te permite administrar productos con control de acceso basado en roles. 
            Perfecto para empresas que necesitan un sistema robusto y fácil de usar.
          </p>
          
          <div className={styles.heroButtons}>
            {isAuthenticated() ? (
              <Link 
                to="/productos" 
                className={`${styles.heroButton} ${styles.heroButtonPrimary}`}
              >
                <i className={`bi bi-box me-2 ${styles.heroButtonIcon}`}></i>
                Ver Productos
              </Link>
            ) : (
              <>
                <Link 
                  to="/login" 
                  className={`${styles.heroButton} ${styles.heroButtonPrimary}`}
                >
                  <i className={`bi bi-box-arrow-in-right me-2 ${styles.heroButtonIcon}`}></i>
                  Iniciar Sesión
                </Link>
                <a 
                  href="#features" 
                  className={`${styles.heroButton} ${styles.heroButtonSecondary}`}
                >
                  <i className={`bi bi-info-circle me-2 ${styles.heroButtonIcon}`}></i>
                  Saber Más
                </a>
              </>
            )}
          </div>
        </div>

        <div className={styles.heroFeatures} id="features">
          <div className={styles.heroFeature}>
            <i className={`bi bi-shield-check ${styles.heroFeatureIcon}`}></i>
            <h3 className={styles.heroFeatureTitle}>Autenticación Segura</h3>
            <p className={styles.heroFeatureDescription}>
              Sistema de autenticación JWT con control de acceso basado en roles para máxima seguridad.
            </p>
          </div>
          
          <div className={styles.heroFeature}>
            <i className={`bi bi-gear ${styles.heroFeatureIcon}`}></i>
            <h3 className={styles.heroFeatureTitle}>Gestión Completa</h3>
            <p className={styles.heroFeatureDescription}>
              CRUD completo de productos con interfaz intuitiva y validaciones robustas.
            </p>
          </div>
          
          <div className={styles.heroFeature}>
            <i className={`bi bi-phone ${styles.heroFeatureIcon}`}></i>
            <h3 className={styles.heroFeatureTitle}>Responsive Design</h3>
            <p className={styles.heroFeatureDescription}>
              Interfaz adaptable que funciona perfectamente en dispositivos móviles, tablets y desktop.
            </p>
          </div>
        </div>

        <div className={styles.heroStats}>
          <div className={styles.heroStat}>
            <span className={styles.heroStatNumber}>2</span>
            <span className={styles.heroStatLabel}>Roles de Usuario</span>
          </div>
          
          <div className={styles.heroStat}>
            <span className={styles.heroStatNumber}>100%</span>
            <span className={styles.heroStatLabel}>Seguro</span>
          </div>
          
          <div className={styles.heroStat}>
            <span className={styles.heroStatNumber}>24/7</span>
            <span className={styles.heroStatLabel}>Disponible</span>
          </div>
          
          <div className={styles.heroStat}>
            <span className={styles.heroStatNumber}>∞</span>
            <span className={styles.heroStatLabel}>Productos</span>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Hero;

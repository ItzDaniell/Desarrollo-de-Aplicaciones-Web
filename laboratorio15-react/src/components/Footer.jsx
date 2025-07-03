import { Link } from "react-router-dom";

const Footer = () => {
  return (
    <div className="container">
      <footer className="py-3 my-4">
        <ul className="nav justify-content-center border-bottom pb-3 mb-3">
          <li className="nav-item">
            <Link
              to="/categorias"
              className="nav-link px-2 text-body-secondary"
            >
              Categorías
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/productos" className="nav-link px-2 text-body-secondary">
              Productos
            </Link>
          </li>
        </ul>
        <p className="text-center text-body-secondary">© Tecsup 2025</p>
      </footer>
    </div>
  );
};

export default Footer;

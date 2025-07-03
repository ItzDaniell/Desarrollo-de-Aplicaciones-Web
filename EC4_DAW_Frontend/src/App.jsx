import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import Header from './components/layouts/Header';
import Footer from './components/layouts/Footer';
import Hero from './components/Hero';
import LoginPage from './pages/LoginPage';
import ProductosPage from './pages/ProductosPage';
import ProductoFormPage from './pages/ProductoFormPage';
import UnauthorizedPage from './pages/UnauthorizedPage';
import ProtectedRoute from './components/ProtectedRoute';
import { ROLES, ROUTES } from './utils/constants';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="d-flex flex-column min-vh-100">
          <Header />
          
          <main className="flex-grow-1">
            <Routes>
              {/* Ruta principal - muestra Hero */}
              <Route path="/" element={<Hero />} />
              
              {/* Ruta de login */}
              <Route path="/login" element={<LoginPage />} />
              
              {/* Rutas protegidas - tanto CLIENTES como DUENOS pueden ver productos */}
              <Route path={ROUTES.PRODUCTOS} element={
                <ProtectedRoute>
                  <ProductosPage />
                </ProtectedRoute>
              } />
              
              {/* Rutas protegidas para DUENOS - pueden crear productos */}
              <Route path={ROUTES.PRODUCTO_CREAR} element={
                <ProtectedRoute requiredRole={ROLES.DUENO}>
                  <ProductoFormPage />
                </ProtectedRoute>
              } />
              
              {/* Rutas protegidas para DUENOS - pueden editar productos */}
              <Route path={`${ROUTES.PRODUCTO_EDITAR}/:id`} element={
                <ProtectedRoute requiredRole={ROLES.DUENO}>
                  <ProductoFormPage />
                </ProtectedRoute>
              } />
              
              {/* Ruta de acceso no autorizado */}
              <Route path={ROUTES.UNAUTHORIZED} element={<UnauthorizedPage />} />
              
              {/* Redirección por defecto */}
              <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
          </main>
          
          <Footer />
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;

import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import productoService from '../services/productoService';

const ProductosPage = () => {
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { isOwner } = useAuth();

  useEffect(() => {
    cargarProductos();
  }, []);

  const cargarProductos = async () => {
    setLoading(true);
    setError('');
    
    const result = await productoService.getProductos();
    
    if (result.success) {
      setProductos(result.data);
    } else {
      setError(result.error);
    }
    
    setLoading(false);
  };

  const handleDelete = async (id) => {
    if (window.confirm('¿Estás seguro de que quieres eliminar este producto?')) {
      const result = await productoService.deleteProducto(id);
      
      if (result.success) {
        // Recargar la lista de productos
        cargarProductos();
      } else {
        alert('Error al eliminar producto: ' + result.error);
      }
    }
  };

  const formatearPrecio = (precio) => {
    return new Intl.NumberFormat('es-PE', {
      style: 'currency',
      currency: 'PEN'
    }).format(precio);
  };

  const getCategoriaColor = (categoria) => {
    const colores = {
      'Electrónicos': 'primary',
      'Ropa': 'success',
      'Hogar': 'warning',
      'Deportes': 'info',
      'Libros': 'secondary',
      'Otros': 'dark'
    };
    return colores[categoria] || 'secondary';
  };

  if (loading) {
    return (
      <div className="min-vh-100 d-flex align-items-center justify-content-center" 
           style={{ background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)' }}>
        <div className="text-center">
          <div className="spinner-border text-primary mb-3" style={{ width: '3rem', height: '3rem' }} role="status">
            <span className="visually-hidden">Cargando...</span>
          </div>
          <h5 className="text-muted">Cargando productos...</h5>
        </div>
      </div>
    );
  }

  return (
    <div className="min-vh-100" style={{ background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)' }}>
      <div className="container py-5">
        {/* Header de la página */}
        <div className="row mb-5">
          <div className="col-12">
            <div className="d-flex justify-content-between align-items-center">
              <div>
                <h1 className="display-6 fw-bold mb-2" style={{ 
                  background: 'linear-gradient(45deg, #667eea, #764ba2)', 
                  WebkitBackgroundClip: 'text', 
                  WebkitTextFillColor: 'transparent'
                }}>
                  <i className="bi bi-box me-3"></i>
                  Catálogo de Productos
                </h1>
                <p className="text-muted fs-5">
                  {productos.length} producto{productos.length !== 1 ? 's' : ''} disponible{productos.length !== 1 ? 's' : ''}
                </p>
              </div>
              {isOwner() && (
                <Link to="/productos/crear" className="btn btn-primary btn-lg shadow-sm border-0" 
                      style={{ 
                        borderRadius: '15px',
                        background: 'linear-gradient(45deg, #667eea, #764ba2)',
                        transition: 'all 0.3s ease'
                      }}
                      onMouseOver={(e) => e.target.style.transform = 'translateY(-2px)'}
                      onMouseOut={(e) => e.target.style.transform = 'translateY(0)'}>
                  <i className="bi bi-plus-circle me-2"></i>
                  Nuevo Producto
                </Link>
              )}
            </div>
          </div>
        </div>

        {/* Mensaje de error */}
        {error && (
          <div className="row mb-4">
            <div className="col-12">
              <div className="alert alert-danger border-0 shadow-sm" role="alert" 
                   style={{ borderRadius: '15px', backgroundColor: '#fef2f2' }}>
                <div className="d-flex align-items-center">
                  <i className="bi bi-exclamation-triangle-fill me-3 text-danger fs-4"></i>
                  <div>
                    <h6 className="alert-heading fw-bold mb-1">Error al cargar productos</h6>
                    <p className="mb-0">{error}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* Contenido principal */}
        {productos.length === 0 ? (
          <div className="row">
            <div className="col-12">
              <div className="card border-0 shadow-lg" style={{ borderRadius: '20px' }}>
                <div className="card-body text-center py-5">
                  <div className="mb-4">
                    <i className="bi bi-box display-1 text-muted" style={{ opacity: 0.5 }}></i>
                  </div>
                  <h3 className="text-muted mb-3">No hay productos disponibles</h3>
                  {isOwner() ? (
                    <div>
                      <p className="text-muted mb-4">Crea el primer producto para comenzar a gestionar tu inventario</p>
                      <Link to="/productos/crear" className="btn btn-primary btn-lg shadow-sm border-0"
                            style={{ 
                              borderRadius: '15px',
                              background: 'linear-gradient(45deg, #667eea, #764ba2)'
                            }}>
                        <i className="bi bi-plus-circle me-2"></i>
                        Crear Primer Producto
                      </Link>
                    </div>
                  ) : (
                    <p className="text-muted">Los productos aparecerán aquí cuando estén disponibles</p>
                  )}
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div className="row g-4">
            {productos.map((producto) => (
              <div key={producto.id} className="col-lg-4 col-md-6">
                <div className="card h-100 border-0 shadow-sm" 
                     style={{ 
                       borderRadius: '20px',
                       transition: 'all 0.3s ease',
                       overflow: 'hidden'
                     }}
                     onMouseOver={(e) => e.target.style.transform = 'translateY(-5px)'}
                     onMouseOut={(e) => e.target.style.transform = 'translateY(0)'}>
                  
                  {/* Header de la card */}
                  <div className="card-header border-0 bg-transparent pt-4 pb-0">
                    <div className="d-flex justify-content-between align-items-start">
                      <div>
                        <span className="badge bg-secondary rounded-pill px-3 py-2">
                          ID: {producto.id}
                        </span>
                      </div>
                      {isOwner() && (
                        <div className="btn-group" role="group">
                          <Link
                            to={`/productos/editar/${producto.id}`}
                            className="btn btn-sm btn-outline-primary rounded-pill"
                            title="Editar"
                            style={{ borderWidth: '2px' }}
                          >
                            <i className="bi bi-pencil"></i>
                          </Link>
                          <button
                            onClick={() => handleDelete(producto.id)}
                            className="btn btn-sm btn-outline-danger rounded-pill"
                            title="Eliminar"
                            style={{ borderWidth: '2px' }}
                          >
                            <i className="bi bi-trash"></i>
                          </button>
                        </div>
                      )}
                    </div>
                  </div>

                  {/* Contenido de la card */}
                  <div className="card-body pt-3">
                    <h5 className="card-title fw-bold mb-3 text-dark">
                      {producto.nombre}
                    </h5>
                    
                    <p className="card-text text-muted mb-4" style={{ fontSize: '0.9rem' }}>
                      {producto.descripcion.length > 80 
                        ? producto.descripcion.substring(0, 80) + '...'
                        : producto.descripcion
                      }
                    </p>

                    <div className="row g-3">
                      {/* Precio */}
                      <div className="col-6">
                        <div className="text-center p-3 rounded-3" style={{ backgroundColor: '#e8f5e8' }}>
                          <div className="fw-bold text-success fs-5">
                            {formatearPrecio(producto.precio)}
                          </div>
                          <small className="text-muted">Precio</small>
                        </div>
                      </div>

                      {/* Cantidad */}
                      <div className="col-6">
                        <div className="text-center p-3 rounded-3" 
                             style={{ 
                               backgroundColor: producto.cantidad > 0 ? '#e3f2fd' : '#ffebee'
                             }}>
                          <div className={`fw-bold fs-5 ${producto.cantidad > 0 ? 'text-info' : 'text-danger'}`}>
                            {producto.cantidad}
                          </div>
                          <small className="text-muted">Unidades</small>
                        </div>
                      </div>
                    </div>

                    {/* Categoría */}
                    <div className="mt-4">
                      <span className={`badge bg-${getCategoriaColor(producto.categoria)} rounded-pill px-3 py-2`}>
                        <i className="bi bi-tag me-1"></i>
                        {producto.categoria}
                      </span>
                    </div>
                  </div>

                  {/* Footer de la card */}
                  <div className="card-footer border-0 bg-transparent pt-0">
                    <div className="d-grid">
                      <button className="btn btn-outline-primary rounded-pill" disabled>
                        <i className="bi bi-eye me-2"></i>
                        Ver Detalles
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>

      {/* Estilos CSS */}
      <style jsx>{`
        .card {
          transition: all 0.3s ease;
        }
        
        .card:hover {
          box-shadow: 0 15px 35px rgba(0,0,0,0.1) !important;
        }
        
        .btn-outline-primary:hover {
          background: linear-gradient(45deg, #667eea, #764ba2);
          border-color: #667eea;
        }
        
        .btn-outline-danger:hover {
          background: linear-gradient(45deg, #ff6b6b, #ee5a52);
          border-color: #ff6b6b;
        }
      `}</style>
    </div>
  );
};

export default ProductosPage; 
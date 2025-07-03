import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import productoService from '../services/productoService';

const ProductoFormPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { isOwner } = useAuth();
  
  const [formData, setFormData] = useState({
    nombre: '',
    descripcion: '',
    precio: '',
    cantidad: '',
    categoria: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [isEditing, setIsEditing] = useState(false);

  const categorias = [
    'Electrónicos',
    'Ropa',
    'Hogar',
    'Deportes',
    'Libros',
    'Otros'
  ];

  useEffect(() => {
    if (!isOwner()) {
      navigate('/unauthorized');
      return;
    }

    if (id) {
      setIsEditing(true);
      cargarProducto();
    }
  }, [id, isOwner, navigate]);

  const cargarProducto = async () => {
    setLoading(true);
    const result = await productoService.getProducto(id);
    
    if (result.success) {
      setFormData(result.data);
    } else {
      setError('Error al cargar el producto: ' + result.error);
    }
    setLoading(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    // Limpiar error cuando el usuario empiece a escribir
    if (error) setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    // Validación básica
    if (!formData.nombre.trim() || !formData.descripcion.trim() || 
        !formData.precio || !formData.cantidad || !formData.categoria) {
      setError('Todos los campos son obligatorios');
      setLoading(false);
      return;
    }

    if (parseFloat(formData.precio) <= 0) {
      setError('El precio debe ser mayor a 0');
      setLoading(false);
      return;
    }

    if (parseInt(formData.cantidad) < 0) {
      setError('La cantidad no puede ser negativa');
      setLoading(false);
      return;
    }

    try {
      const result = isEditing 
        ? await productoService.updateProducto(id, formData)
        : await productoService.createProducto(formData);

      if (result.success) {
        navigate('/productos');
      } else {
        setError(result.error);
      }
    } catch (err) {
      setError('Error de conexión. Intente nuevamente.');
    } finally {
      setLoading(false);
    }
  };

  if (loading && isEditing) {
    return (
      <div className="min-vh-100 d-flex align-items-center justify-content-center" 
           style={{ background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)' }}>
        <div className="text-center">
          <div className="spinner-border text-primary mb-3" style={{ width: '3rem', height: '3rem' }} role="status">
            <span className="visually-hidden">Cargando...</span>
          </div>
          <h5 className="text-muted">Cargando producto...</h5>
        </div>
      </div>
    );
  }

  return (
    <div className="min-vh-100" style={{ background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)' }}>
      <div className="container py-5">
        <div className="row justify-content-center">
          <div className="col-lg-8 col-xl-6">
            {/* Header */}
            <div className="text-center mb-5">
              <div className="mb-3">
                <i className={`bi ${isEditing ? 'bi-pencil-square' : 'bi-plus-circle'} display-1 text-primary`}
                   style={{ 
                     background: 'linear-gradient(45deg, #667eea, #764ba2)', 
                     WebkitBackgroundClip: 'text', 
                     WebkitTextFillColor: 'transparent' 
                   }}></i>
              </div>
              <h1 className="display-6 fw-bold mb-2" style={{ 
                background: 'linear-gradient(45deg, #667eea, #764ba2)', 
                WebkitBackgroundClip: 'text', 
                WebkitTextFillColor: 'transparent'
              }}>
                {isEditing ? 'Editar Producto' : 'Nuevo Producto'}
              </h1>
              <p className="text-muted fs-5">
                {isEditing ? 'Modifica la información del producto' : 'Completa la información del nuevo producto'}
              </p>
            </div>

            {/* Formulario */}
            <div className="card border-0 shadow-lg" style={{ borderRadius: '20px' }}>
              <div className="card-body p-5">
                <form onSubmit={handleSubmit}>
                  {/* Mensaje de error */}
                  {error && (
                    <div className="alert alert-danger border-0 shadow-sm mb-4" role="alert" 
                         style={{ borderRadius: '12px', backgroundColor: '#fef2f2' }}>
                      <div className="d-flex align-items-center">
                        <i className="bi bi-exclamation-triangle-fill me-2 text-danger"></i>
                        <span className="fw-medium">{error}</span>
                      </div>
                    </div>
                  )}

                  {/* Nombre */}
                  <div className="mb-4">
                    <label htmlFor="nombre" className="form-label fw-semibold text-dark">
                      <i className="bi bi-box me-2 text-primary"></i>
                      Nombre del Producto *
                    </label>
                    <div className="input-group">
                      <span className="input-group-text bg-light border-end-0">
                        <i className="bi bi-box text-muted"></i>
                      </span>
                      <input
                        id="nombre"
                        name="nombre"
                        type="text"
                        className="form-control border-start-0"
                        required
                        placeholder="Ej: Laptop HP Pavilion"
                        value={formData.nombre}
                        onChange={handleChange}
                        style={{ borderColor: '#e9ecef' }}
                      />
                    </div>
                  </div>

                  {/* Descripción */}
                  <div className="mb-4">
                    <label htmlFor="descripcion" className="form-label fw-semibold text-dark">
                      <i className="bi bi-card-text me-2 text-primary"></i>
                      Descripción *
                    </label>
                    <div className="input-group">
                      <span className="input-group-text bg-light border-end-0">
                        <i className="bi bi-card-text text-muted"></i>
                      </span>
                      <textarea
                        id="descripcion"
                        name="descripcion"
                        className="form-control border-start-0"
                        rows="4"
                        required
                        placeholder="Describe las características del producto..."
                        value={formData.descripcion}
                        onChange={handleChange}
                        style={{ borderColor: '#e9ecef', resize: 'none' }}
                      />
                    </div>
                  </div>

                  {/* Precio y Cantidad */}
                  <div className="row g-4 mb-4">
                    <div className="col-md-6">
                      <label htmlFor="precio" className="form-label fw-semibold text-dark">
                        <i className="bi bi-currency-dollar me-2 text-primary"></i>
                        Precio (PEN) *
                      </label>
                      <div className="input-group">
                        <span className="input-group-text bg-light border-end-0">
                          <i className="bi bi-currency-dollar text-muted"></i>
                        </span>
                        <input
                          id="precio"
                          name="precio"
                          type="number"
                          step="0.01"
                          min="0"
                          className="form-control border-start-0"
                          required
                          placeholder="0.00"
                          value={formData.precio}
                          onChange={handleChange}
                          style={{ borderColor: '#e9ecef' }}
                        />
                      </div>
                    </div>

                    <div className="col-md-6">
                      <label htmlFor="cantidad" className="form-label fw-semibold text-dark">
                        <i className="bi bi-boxes me-2 text-primary"></i>
                        Cantidad *
                      </label>
                      <div className="input-group">
                        <span className="input-group-text bg-light border-end-0">
                          <i className="bi bi-boxes text-muted"></i>
                        </span>
                        <input
                          id="cantidad"
                          name="cantidad"
                          type="number"
                          min="0"
                          className="form-control border-start-0"
                          required
                          placeholder="0"
                          value={formData.cantidad}
                          onChange={handleChange}
                          style={{ borderColor: '#e9ecef' }}
                        />
                      </div>
                    </div>
                  </div>

                  {/* Categoría */}
                  <div className="mb-5">
                    <label htmlFor="categoria" className="form-label fw-semibold text-dark">
                      <i className="bi bi-tag me-2 text-primary"></i>
                      Categoría *
                    </label>
                    <div className="input-group">
                      <span className="input-group-text bg-light border-end-0">
                        <i className="bi bi-tag text-muted"></i>
                      </span>
                      <select
                        id="categoria"
                        name="categoria"
                        className="form-select border-start-0"
                        required
                        value={formData.categoria}
                        onChange={handleChange}
                        style={{ borderColor: '#e9ecef' }}
                      >
                        <option value="">Selecciona una categoría</option>
                        {categorias.map(cat => (
                          <option key={cat} value={cat}>{cat}</option>
                        ))}
                      </select>
                    </div>
                  </div>

                  {/* Botones */}
                  <div className="d-flex gap-3 justify-content-center">
                    <button
                      type="button"
                      className="btn btn-outline-secondary btn-lg px-4 border-0 shadow-sm"
                      onClick={() => navigate('/productos')}
                      style={{ 
                        borderRadius: '12px',
                        backgroundColor: '#f8f9fa',
                        transition: 'all 0.3s ease'
                      }}
                      onMouseOver={(e) => e.target.style.transform = 'translateY(-2px)'}
                      onMouseOut={(e) => e.target.style.transform = 'translateY(0)'}
                    >
                      <i className="bi bi-arrow-left me-2"></i>
                      Cancelar
                    </button>
                    
                    <button
                      type="submit"
                      className="btn btn-primary btn-lg px-5 fw-semibold border-0 shadow-sm"
                      disabled={loading}
                      style={{ 
                        borderRadius: '12px',
                        background: 'linear-gradient(45deg, #667eea, #764ba2)',
                        transition: 'all 0.3s ease'
                      }}
                      onMouseOver={(e) => e.target.style.transform = 'translateY(-2px)'}
                      onMouseOut={(e) => e.target.style.transform = 'translateY(0)'}
                    >
                      {loading ? (
                        <div className="d-flex align-items-center">
                          <div className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></div>
                          <span>{isEditing ? 'Guardando...' : 'Creando...'}</span>
                        </div>
                      ) : (
                        <div className="d-flex align-items-center">
                          <i className={`bi ${isEditing ? 'bi-check-circle' : 'bi-plus-circle'} me-2`}></i>
                          <span>{isEditing ? 'Guardar Cambios' : 'Crear Producto'}</span>
                        </div>
                      )}
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Estilos CSS */}
      <style jsx>{`
        .card {
          transition: all 0.3s ease;
        }
        
        .card:hover {
          box-shadow: 0 20px 40px rgba(0,0,0,0.1) !important;
        }
        
        .form-control:focus, .form-select:focus {
          box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
          border-color: #667eea;
        }
        
        .btn-primary:hover {
          box-shadow: 0 8px 25px rgba(102, 126, 234, 0.3);
        }
        
        .btn-outline-secondary:hover {
          background-color: #6c757d;
          color: white;
        }
      `}</style>
    </div>
  );
};

export default ProductoFormPage; 
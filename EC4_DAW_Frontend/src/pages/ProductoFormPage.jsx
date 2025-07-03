import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import productoService from '../services/productoService';
import { useAuth } from '../contexts/AuthContext';
import { ROLES, ROUTES, ERROR_MESSAGES, SUCCESS_MESSAGES } from '../utils/constants';

const initialForm = {
  nombre: '',
  descripcion: '',
  precio: '',
  cantidad: '',
  categoria: ''
};

const ProductoFormPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useAuth();
  const [form, setForm] = useState(initialForm);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  useEffect(() => {
    if (id) {
      setLoading(true);
      productoService.getProducto(id)
        .then(result => {
          if (result.success) {
            setForm({
              nombre: result.data.nombre,
              descripcion: result.data.descripcion,
              precio: result.data.precio,
              cantidad: result.data.cantidad,
              categoria: result.data.categoria
            });
          } else {
            setError('No se pudo cargar el producto');
          }
          setLoading(false);
        })
        .catch(() => {
          setError('No se pudo cargar el producto');
          setLoading(false);
        });
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
    setError('');
    setSuccess('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setSuccess('');
    try {
      if (id) {
        await productoService.updateProducto(id, form);
        setSuccess(SUCCESS_MESSAGES.PRODUCTO_ACTUALIZADO);
      } else {
        await productoService.createProducto(form);
        setSuccess(SUCCESS_MESSAGES.PRODUCTO_CREADO);
        setForm(initialForm);
      }
      setTimeout(() => navigate(ROUTES.PRODUCTOS), 1200);
    } catch {
      setError(ERROR_MESSAGES.SERVER_ERROR);
    } finally {
      setLoading(false);
    }
  };

  if (!user || user.role !== ROLES.DUENO) {
    return (
      <div className="container py-5">
        <div className="alert alert-danger">{ERROR_MESSAGES.UNAUTHORIZED}</div>
      </div>
    );
  }

  return (
    <div className="container py-4">
      <div className="row justify-content-center">
        <div className="col-md-8 col-lg-6">
          <div className="card border-0 shadow">
            <div className="card-body p-4">
              <h2 className="mb-4 fw-bold text-dark">
                {id ? 'Editar Producto' : 'Nuevo Producto'}
              </h2>
              {error && (
                <div className="alert alert-danger border-0 shadow-sm" role="alert">
                  <i className="bi bi-exclamation-triangle me-2"></i>
                  {error}
                </div>
              )}
              {success && <div className="alert alert-success border-0 shadow-sm">{success}</div>}
              <form onSubmit={handleSubmit} autoComplete="off">
                <div className="mb-3">
                  <label htmlFor="nombre" className="form-label fw-semibold text-dark">
                    Nombre
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="nombre"
                    name="nombre"
                    value={form.nombre}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="descripcion" className="form-label fw-semibold text-dark">
                    Descripción
                  </label>
                  <textarea
                    className="form-control"
                    id="descripcion"
                    name="descripcion"
                    value={form.descripcion}
                    onChange={handleChange}
                    required
                    rows="2"
                  ></textarea>
                </div>
                <div className="mb-3">
                  <label htmlFor="precio" className="form-label fw-semibold text-dark">
                    Precio
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="precio"
                    name="precio"
                    value={form.precio}
                    onChange={handleChange}
                    required
                    min="0"
                    step="0.01"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="cantidad" className="form-label fw-semibold text-dark">
                    Cantidad
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="cantidad"
                    name="cantidad"
                    value={form.cantidad}
                    onChange={handleChange}
                    required
                    min="0"
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="categoria" className="form-label fw-semibold text-dark">
                    Categoría
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="categoria"
                    name="categoria"
                    value={form.categoria}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="d-flex gap-2 justify-content-end mt-4">
                  <button
                    type="submit"
                    className="btn btn-primary fw-semibold"
                    disabled={loading}
                  >
                    {loading ? (
                      <span>
                        <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                        Guardando...
                      </span>
                    ) : (
                      <span>
                        <i className="bi bi-save me-2"></i>
                        Guardar
                      </span>
                    )}
                  </button>
                  <button
                    type="button"
                    className="btn btn-outline-secondary"
                    onClick={() => navigate(ROUTES.PRODUCTOS)}
                  >
                    <i className="bi bi-arrow-left me-2"></i>
                    Cancelar
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductoFormPage; 
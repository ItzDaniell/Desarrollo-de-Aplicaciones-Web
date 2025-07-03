import { useEffect, useState } from 'react';
import { useAuth } from '../contexts/AuthContext';
import productoService from '../services/productoService';
import { Link, useNavigate } from 'react-router-dom';
import { ROLES, ROUTES, SUCCESS_MESSAGES, ERROR_MESSAGES } from '../utils/constants';
import DeleteConfirmModal from '../components/DeleteConfirmModal';

const ProductosPage = () => {
  const { user } = useAuth();
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [deletingIds, setDeletingIds] = useState(new Set());
  const [error, setError] = useState('');
  const [deleteModal, setDeleteModal] = useState({ isOpen: false, product: null });
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    productoService.getProductos()
      .then(result => {
        if (result.success) {
          setProductos(result.data);
        } else {
          setError(result.error || 'Error al cargar productos');
        }
        setLoading(false);
      })
      .catch(() => {
        setError('Error al cargar productos');
        setLoading(false);
      });
  }, []);

  const handleDeleteClick = (producto) => {
    setDeleteModal({ isOpen: true, product: producto });
  };

  const handleDeleteConfirm = async () => {
    const { product } = deleteModal;
    
    try {
      // Marcar este producto como en proceso de eliminación
      setDeletingIds(prev => new Set(prev).add(product.id));
      
      const result = await productoService.deleteProducto(product.id);
      
      if (result.success) {
        // Actualizar la lista de productos
        setProductos(productos.filter(p => p.id !== product.id));
        // Cerrar modal
        setDeleteModal({ isOpen: false, product: null });
        // Mostrar mensaje de éxito
        alert(SUCCESS_MESSAGES.PRODUCTO_ELIMINADO);
      } else {
        setError(result.error || ERROR_MESSAGES.SERVER_ERROR);
      }
    } catch (error) {
      setError(ERROR_MESSAGES.SERVER_ERROR);
    } finally {
      // Remover el ID del conjunto de productos en proceso de eliminación
      setDeletingIds(prev => {
        const newSet = new Set(prev);
        newSet.delete(product.id);
        return newSet;
      });
    }
  };

  const handleDeleteCancel = () => {
    setDeleteModal({ isOpen: false, product: null });
  };

  return (
    <div className="container py-4">
      <h2 className="mb-4 fw-bold text-dark">Lista de Productos</h2>
      {error && (
        <div className="alert alert-danger border-0 shadow-sm" role="alert">
          <i className="bi bi-exclamation-triangle me-2"></i>
          {error}
        </div>
      )}
      {user && user.role === ROLES.DUENO && (
        <div className="mb-3">
          <button
            className="btn btn-primary fw-semibold"
            onClick={() => navigate(ROUTES.PRODUCTO_CREAR)}
          >
            <i className="bi bi-plus-circle me-2"></i>
            Nuevo Producto
          </button>
        </div>
      )}
      <div className="table-responsive">
        <table className="table table-hover border rounded-3 overflow-hidden shadow-sm">
          <thead className="table-light">
            <tr>
              <th>Nombre</th>
              <th>Precio</th>
              <th>Categoría</th>
              <th>Stock</th>
              {user && user.role === ROLES.DUENO && <th>Acciones</th>}
            </tr>
          </thead>
          <tbody>
            {productos.map((producto) => (
              <tr key={producto.id}>
                <td className="fw-semibold text-dark">{producto.nombre}</td>
                <td>S/ {producto.precio}</td>
                <td>
                  <span className="badge bg-primary rounded-pill">
                    {producto.categoria}
                  </span>
                </td>
                <td>
                  <span className="badge bg-secondary rounded-pill">
                    {producto.cantidad}
                  </span>
                </td>
                {user && user.role === ROLES.DUENO && (
                  <td>
                    <button
                      className="btn btn-outline-primary btn-sm me-2"
                      onClick={() => navigate(`${ROUTES.PRODUCTO_EDITAR}/${producto.id}`)}
                    >
                      <i className="bi bi-pencil"></i>
                    </button>
                    <button
                      className="btn btn-outline-danger btn-sm"
                      onClick={() => handleDeleteClick(producto)}
                      disabled={deletingIds.has(producto.id)}
                    >
                      {deletingIds.has(producto.id) ? (
                        <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                      ) : (
                        <i className="bi bi-trash"></i>
                      )}
                    </button>
                  </td>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal de confirmación de eliminación */}
      <DeleteConfirmModal
        isOpen={deleteModal.isOpen}
        onClose={handleDeleteCancel}
        onConfirm={handleDeleteConfirm}
        productName={deleteModal.product?.nombre || ''}
        isLoading={deleteModal.product && deletingIds.has(deleteModal.product.id)}
      />
    </div>
  );
};

export default ProductosPage; 
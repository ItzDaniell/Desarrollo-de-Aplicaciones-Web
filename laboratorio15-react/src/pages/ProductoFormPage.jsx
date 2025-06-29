import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useProductStore } from "../store/useProductosStore";
import { useCategoriasStore } from "../store/useCategoriasStore";
import Header from "../components/Header";
import Footer from "../components/Footer";

const ProductoFormPage = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const isEditing = Boolean(id);

  const {
    fetchProductById,
    selectedProduct,
    createProduct,
    updateProduct,
  } = useProductStore();

  const { categories, getCategories } = useCategoriasStore();

  const [formData, setFormData] = useState({
    nombre: "",
    descripcion: "",
    stock: 0,
    precio: 0,
    categoria: "",
  });

  useEffect(() => {
    getCategories();
    if (isEditing) {
      fetchProductById(id);
    }
  }, [id]);

  useEffect(() => {
    if (selectedProduct && isEditing) {
      setFormData({
        nombre: selectedProduct.nombre || "",
        descripcion: selectedProduct.descripcion || "",
        stock: selectedProduct.stock || 0,
        precio: selectedProduct.precio || 0,
        categoria: selectedProduct.categoria?.id || "",
      });
    }
  }, [selectedProduct]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      ...formData,
      categoria: { id: parseInt(formData.categoria) }, // Relación ManyToOne
    };

    if (isEditing) {
      await updateProduct(id, data);
    } else {
      await createProduct(data);
    }

    navigate("/productos");
  };

  return (
    <>
      <Header />
      <main className="container mt-4">
        <h2>{isEditing ? "Editar Producto" : "Nuevo Producto"}</h2>

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label>Nombre</label>
            <input
              type="text"
              className="form-control"
              name="nombre"
              value={formData.nombre}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Descripción</label>
            <input
              type="text"
              className="form-control"
              name="descripcion"
              value={formData.descripcion}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Stock</label>
            <input
              type="number"
              className="form-control"
              name="stock"
              value={formData.stock}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Precio</label>
            <input
              type="number"
              className="form-control"
              step="0.01"
              name="precio"
              value={formData.precio}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label>Categoría</label>
            <select
              className="form-select"
              name="categoria"
              value={formData.categoria}
              onChange={handleChange}
              required
            >
              <option value="">-- Seleccione --</option>
              {categories.map((cat) => (
                <option key={cat.id} value={cat.id}>
                  {cat.nombre}
                </option>
              ))}
            </select>
          </div>

          <button className="btn btn-success" type="submit">
            {isEditing ? "Actualizar" : "Guardar"}
          </button>
        </form>
      </main>
      <Footer />
    </>
  );
};

export default ProductoFormPage;

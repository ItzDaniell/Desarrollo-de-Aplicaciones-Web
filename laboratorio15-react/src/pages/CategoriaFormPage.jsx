import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useCategoriasStore } from "../store/useCategoriasStore";
import Header from "../components/Header";
import Footer from "../components/Footer";

const CategoriaFormPage = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const {
    selectedCategoria,
    fetchCategoriaById,
    createCategoria,
    updateCategoria,
  } = useCategoriasStore();

  const [nombre, setNombre] = useState("");

  useEffect(() => {
    if (id) {
      fetchCategoriaById(id);
    }
  }, [id]);

  useEffect(() => {
    if (id && selectedCategoria) {
      setNombre(selectedCategoria.nombre);
    }
  }, [selectedCategoria]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const categoriaData = { nombre };

    if (id) {
      await updateCategoria(id, categoriaData);
    } else {
      await createCategoria(categoriaData);
    }

    navigate("/categorias");
  };

  return (
    <>
      <Header />
      <main className="container py-4">
        <h2>{id ? "Editar Categoría" : "Nueva Categoría"}</h2>

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="nombre" className="form-label">
              Nombre de la categoría
            </label>
            <input
              type="text"
              id="nombre"
              className="form-control"
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary">
            {id ? "Actualizar" : "Crear"}
          </button>
        </form>
      </main>
      <Footer />
    </>
  );
};

export default CategoriaFormPage;

import { useEffect } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { useCategoriasStore } from "../store/useCategoriasStore";
import { Link } from "react-router-dom";

const CateogoriaPage = () => {
  const { categories, getCategories, deleteCategoria } = useCategoriasStore();

  useEffect(() => {
    getCategories();
  }, []);

  const handleDelete = async (id) => {
    const confirm = window.confirm("¿Estás seguro de eliminar esta categoría?");
    if (confirm) {
      await deleteCategoria(id);
    }
  };

  return (
    <>
      <Header />
      <main>
        <section className="container">
          <div className="d-flex flex-row justify-content-between mt-2 mb-2">
            <h2>Lista de Categorías</h2>
            <Link to="/categoria/new">
              <button className="btn btn-primary">Agregar Categoría</button>
            </Link>
          </div>

          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Descripción</th>
                <th scope="col" colSpan={2}>
                  Opciones
                </th>
              </tr>
            </thead>
            <tbody>
              {categories.length === 0 ? (
                <tr>
                  <td colSpan={4} style={{ textAlign: "center" }}>
                    No hay categorías registradas.
                  </td>
                </tr>
              ) : (
                categories.map((cat) => (
                  <tr key={cat.id}>
                    <td>{cat.id}</td>
                    <td>{cat.nombre}</td>
                    <td>
                      <Link to={`/categoria/edit/${cat.id}`}>
                        <button className="btn btn-warning">Editar</button>
                      </Link>
                    </td>
                    <td>
                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(cat.id)}
                      >
                        Eliminar
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </section>
      </main>
      <Footer />
    </>
  );
};

export default CateogoriaPage;

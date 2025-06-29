import { useEffect } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { useProductStore } from "../store/useProductosStore";
import { Link } from "react-router-dom";

const ProductoPage = () => {
  const { products, getProducts, deleteProduct } = useProductStore();

  useEffect(() => {
    getProducts();
  }, []);

  const handleDelete = async (id) => {
    const confirm = window.confirm("¿Estás seguro de eliminar este producto?");
    if (confirm) {
      await deleteProduct(id);
    }
  };

  return (
    <>
      <Header />
      <main>
        <section className="container">
          <div className="d-flex flex-row justify-content-between mt-2 mb-2">
            <h2>Lista de Productos</h2>
            <Link to="/producto/new">
              <button className="btn btn-primary">Agregar Producto</button>
            </Link>
          </div>

          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Descripción</th>
                <th scope="col">Stock</th>
                <th scope="col">Precio</th>
                <th scope="col">Categoría</th>
                <th scope="col" colSpan={2}>
                  Opciones
                </th>
              </tr>
            </thead>
            <tbody>
              {products.length === 0 ? (
                <tr>
                  <td colSpan={8} style={{ textAlign: "center" }}>
                    No hay Productos registrados.
                  </td>
                </tr>
              ) : (
                products.map((prod) => (
                  <tr key={prod.id}>
                    <td>{prod.id}</td>
                    <td>{prod.nombre}</td>
                    <td>{prod.descripcion}</td>
                    <td>{prod.stock}</td>
                    <td>{prod.precio}</td>
                    <td>{prod.categoria.nombre}</td>
                    <td>
                      <Link to={`/producto/edit/${prod.id}`}>
                        <button className="btn btn-warning">Editar</button>
                      </Link>
                    </td>
                    <td>
                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(prod.id)}
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

export default ProductoPage;

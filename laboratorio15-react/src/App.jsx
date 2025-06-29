import { BrowserRouter, Route, Routes } from "react-router-dom"
import CateogoriaPage from "./pages/CategoriaPage"
import CategoriaFormPage from "./pages/CategoriaFormPage"
import ProductoPage from "./pages/ProductoPage"
import ProductoFormPage from "./pages/ProductoFormPage"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/categorias" element={<CateogoriaPage />} />
        <Route path="/categoria/new" element={<CategoriaFormPage />} />
        <Route path="/categoria/edit/:id" element={<CategoriaFormPage />} />
        <Route path="/productos" element={<ProductoPage />} />
        <Route path="/producto/new" element={<ProductoFormPage />} />
        <Route path="/producto/edit/:id" element={<ProductoFormPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App

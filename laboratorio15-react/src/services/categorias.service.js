import axios from "axios"

export const getCategorias = async () => {
    try {
        const response = await axios.get("http://localhost:8081/api/v1/categorias")
        return response.data
    } catch (error) {
        console.error("Error fetching categorias:", error)
    }
}

export const getCategoriaById = async (id) => {
    try {
        const response = await axios.get(`http://localhost:8081/api/v1/categoria/${id}`)
        return response.data
    } catch (error) {
        console.error("Error fetching categoria by ID:", error)
    }
}

export const createCategoria = async (categoria) => {
    try {
        const response = await axios.post("http://localhost:8081/api/v1/categorias", categoria)
        return response.data
    } catch (error) {
        console.error("Error creating categoria:", error)
    }
}

export const updateCategoria = async (id, categoria) => {
    try {
        const response = await axios.put(`http://localhost:8081/api/v1/categoria/${id}`, categoria)
        return response.data
    } catch (error) {
        console.error("Error updating categoria:", error)
    }
}

export const deleteCategoria = async (id) => {
    try {
        const response = await axios.delete(`http://localhost:8081/api/v1/categoria/${id}`)
        return response.data
    } catch (error) {
        console.error("Error deleting categoria:", error)
    }
}

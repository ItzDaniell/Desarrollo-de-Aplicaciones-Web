import axios from "axios"

export const getProducts = async () => {
    try {
        const response = await axios.get("http://localhost:8081/api/v1/productos")
        return response.data
    } catch (error) {
        console.error("Error fetching productos:", error)
    }
}

export const getProductById = async (id) => {
    try {
        const response = await axios.get(`http://localhost:8081/api/v1/producto/${id}`)
        return response.data
    } catch (error) {
        console.error("Error fetching producto by ID:", error)
    }
}

export const createProduct = async (producto) => {
    try {
        const response = await axios.post("http://localhost:8081/api/v1/productos", producto)
        return response.data
    } catch (error) {
        console.error("Error creating producto:", error)
    }
}

export const updateProduct = async (id, producto) => {
    try {
        const response = await axios.put(`http://localhost:8081/api/v1/producto/${id}`, producto)
        return response.data
    } catch (error) {
        console.error("Error updating producto:", error)
    }
}

export const deleteProduct = async (id) => {
    try {
        const response = await axios.delete(`http://localhost:8081/api/v1/producto/${id}`)
        return response.data
    } catch (error) {
        console.error("Error deleting producto:", error)
    }
}
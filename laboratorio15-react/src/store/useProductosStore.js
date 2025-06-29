import { create } from 'zustand';
import {
  getProducts,
  getProductById,
  createProduct,
  updateProduct,
  deleteProduct,
} from '../services/productos.service';

export const useProductStore = create((set) => ({
  products: [],
  selectedProduct: null,

  getProducts: async () => {
    const products = await getProducts();
    set({ products });
  },

  fetchProductById: async (id) => {
    const product = await getProductById(id);
    set({ selectedProduct: product });
  },

  createProduct: async (product) => {
    const newProduct = await createProduct(product);
    set((state) => ({ products: [...state.products, newProduct] }));
  },

  updateProduct: async (id, product) => {
    const updated = await updateProduct(id, product);
    set((state) => ({
      products: state.products.map((p) => (p.id === id ? updated : p)),
    }));
  },

  deleteProduct: async (id) => {
    await deleteProduct(id);
    set((state) => ({
      products: state.products.filter((p) => p.id !== id),
    }));
  },
}));
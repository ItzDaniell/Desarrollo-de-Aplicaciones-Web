import { create } from 'zustand';
import {
  getCategorias,
  getCategoriaById,
  createCategoria,
  updateCategoria,
  deleteCategoria,
} from '../services/categorias.service';

export const useCategoriasStore = create((set) => ({
  categories: [],
  selectedCategoria: null,

  getCategories: async () => {
    const categories = await getCategorias();
    set({ categories });
  },

  fetchCategoriaById: async (id) => {
    const categoria = await getCategoriaById(id);
    set({ selectedCategoria: categoria });
  },

  createCategoria: async (categoria) => {
    await createCategoria(categoria);
    set((state) => ({ categories: [...state.categories, categoria] }));
  },

  updateCategoria: async (id, categoria) => {
    const updated = await updateCategoria(id, categoria);
    set((state) => ({
      categories: state.categories.map((cat) =>
        cat.id === id ? updated : cat
      ),
    }));
  },

  deleteCategoria: async (id) => {
    await deleteCategoria(id);
    set((state) => ({
      categories: state.categories.filter((cat) => cat.id !== id),
    }));
  },
}));

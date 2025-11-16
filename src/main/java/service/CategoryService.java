package service;

import dao.CategoryDAO;
import domain.Category;
import java.util.List;


public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public Category findOrCreateCategory(String categoryName) {
        if(categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name can't be empty.");
        }
        String lowerName = categoryName.trim().toLowerCase();
        Category category = categoryDAO.findByName(lowerName);

        if(category == null){
            System.out.println("(Service: '" + lowerName + "' category not found. Creating a new one...)");

            category = new Category(lowerName);
            categoryDAO.save(category);
        }

        return category;
    }

    public Category findCategoryByID(Long id) {
        Category category = categoryDAO.searchByID(id);
        if (category == null) {
            throw new RuntimeException("Error: id category " + id + " not found.");
        }
        return category;
    }

    public List<Category> listAllCategories() {
        return categoryDAO.listAll();
    }
}

package org.nttdata.ecomspring.controllers;

import jakarta.websocket.server.PathParam;
import org.nttdata.ecomspring.entities.Category;
import org.nttdata.ecomspring.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String getCategories(Map<String, Object> model) {
        List<Category> categories = categoryService.getAllCategories();
        model.put("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String getCategoryById(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Category category = categoryService.getCategoryById(id);
        model.put("category", category);
        return "category";
    }

    @PostMapping("/categories")
    public String addCategory(Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @PutMapping("/categories")
    public String updateCategory(Category category) {
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}

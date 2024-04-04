package org.nttdata.ecomspring.controllers;

import jakarta.websocket.server.PathParam;
import org.nttdata.ecomspring.entities.Category;
import org.nttdata.ecomspring.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getCategories(Map<String, Object> model) {
        List<Category> categories = categoryService.getAllCategories();
        model.put("categories", categories);
        return "categories";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Category category = categoryService.getCategoryById(id);
        model.put("category", category);
        return "category";
    }

    @GetMapping("edit/{id}")
    public String getCategoryById(@PathVariable(value = "id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("editCategory", category);
        return "categories";
    }

    @PostMapping("/save")
    public String addCategory(Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

//    @GetMapping("/add")
//    public String addCategoryPage(Model model) {
//
//        categoryService.addCategory();
//        return "redirect:/categories";
//    }

    @PostMapping("/update")
    public String updateCategory(Category category) {
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}

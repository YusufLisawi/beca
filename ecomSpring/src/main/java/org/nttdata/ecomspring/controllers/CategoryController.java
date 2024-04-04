package org.nttdata.ecomspring.controllers;

import jakarta.websocket.server.PathParam;
import org.nttdata.ecomspring.entities.Category;
import org.nttdata.ecomspring.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getCategories(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);
        model.addAttribute("categories", categoryPage);
        return "categories";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable(value = "id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping("")
    public String addCategory(Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("update")
    public String updateCategory(Category category, Model model) {
        categoryService.updateCategory(category);
        model.addAttribute("category", null);
        return "redirect:/categories";
    }

    @PostMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
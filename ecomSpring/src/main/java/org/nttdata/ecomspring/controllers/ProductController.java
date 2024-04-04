package org.nttdata.ecomspring.controllers;

import org.nttdata.ecomspring.entities.Category;
import org.nttdata.ecomspring.entities.Product;
import org.nttdata.ecomspring.services.CategoryService;
import org.nttdata.ecomspring.services.ProductService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductController {

    public static String UPLOAD_PATH = "uploads" + File.separator;
    private static final String UPLOAD_DIRECTORY = "classpath:static/uploads/";

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ResourceLoader resourceLoader;

    public ProductController(ProductService productService, CategoryService categoryService, ResourceLoader resourceLoader) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        addCategoriesToModel(model);
        addProductsToModel(model);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        addCategoriesToModel(model);
        addProductsToModel(model);
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(Product product, @RequestParam("image") MultipartFile file, Model model) throws IOException {
        String fileNames = uploadImage(model, file);
        product.setImageUrl(fileNames);
        productService.addProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Resource resource = resourceLoader.getResource(UPLOAD_DIRECTORY);
        Path uploadPath = Paths.get(resource.getURI());
        Path fileNameAndPath = uploadPath.resolve(file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("message", "Uploaded images: " + fileNames.toString());
        return fileNames.toString();
    }

    private void addCategoriesToModel(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

    private void addProductsToModel(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("UPLOAD_DIRECTORY", UPLOAD_PATH);
    }
}

package org.nttdata.ecomspring.controllers;

import org.nttdata.ecomspring.entities.Product;
import org.nttdata.ecomspring.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(Map<String, Object> model) {
        List<Product> products = productService.getAllProducts();
        model.put("products", products);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Product product = productService.getProductById(id);
        model.put("product", product);
        return "product";
    }

    @PostMapping("/products")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @PutMapping("/products")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}

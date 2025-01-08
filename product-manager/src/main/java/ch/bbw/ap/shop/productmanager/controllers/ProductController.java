package ch.bbw.ap.shop.productmanager.controllers;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.services.ProductService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String q, @RequestParam(required = false) Long categoryId) {
        return productService.getAll(q, categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getById(id);

        if(product == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.getById(id));
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

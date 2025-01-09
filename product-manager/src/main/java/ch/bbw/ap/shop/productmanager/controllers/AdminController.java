package ch.bbw.ap.shop.productmanager.controllers;

import ch.bbw.ap.shop.productmanager.mapper.ProductMapper;
import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.models.ProductRequest;
import ch.bbw.ap.shop.productmanager.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductRequest product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {

        Product product = productService.editProduct(id, productRequest);

        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}

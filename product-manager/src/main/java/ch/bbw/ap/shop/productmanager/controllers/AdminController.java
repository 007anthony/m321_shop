package ch.bbw.ap.shop.productmanager.controllers;

import ch.bbw.ap.shop.productmanager.mapper.ProductMapper;
import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.models.ProductRequest;
import ch.bbw.ap.shop.productmanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

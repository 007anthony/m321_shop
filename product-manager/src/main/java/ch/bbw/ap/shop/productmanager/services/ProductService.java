package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.models.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> getAll(String search, Long categoryId);
    Product getById(Long id);

    Product createProduct(ProductRequest product);
    Product editProduct(Long id, ProductRequest product);
}

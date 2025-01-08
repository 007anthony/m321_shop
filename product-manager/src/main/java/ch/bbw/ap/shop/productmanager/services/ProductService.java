package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll(String search, Long categoryId);
    Product getById(Long id);
    void deleteById(Long id);
}

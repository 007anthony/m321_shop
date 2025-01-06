package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAll(String search, Category category);
}

package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.repositories.ProductRepository;
import ch.bbw.ap.shop.productmanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAll(String search, Category category) {
        return productRepository.findAll();
    }
}

package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.repositories.ProductRepository;
import ch.bbw.ap.shop.productmanager.services.ProductService;
import ch.bbw.ap.shop.productmanager.repositories.specifications.ProductSpecification;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAll(String search, Long categoryId) {
        Specification<Product> filters = Specification.where(StringUtils.isBlank(search)? null: ProductSpecification.search(search))
                .and(categoryId == null? null: ProductSpecification.hasCategory(categoryId));
        return productRepository.findAll(filters);
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) {
            return null;
        }

        return product.get();
    }
}

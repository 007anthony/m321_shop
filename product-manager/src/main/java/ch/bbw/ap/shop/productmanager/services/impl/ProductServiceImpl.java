package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.mapper.ProductMapper;
import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.models.ProductRequest;
import ch.bbw.ap.shop.productmanager.repositories.ProductRepository;
import ch.bbw.ap.shop.productmanager.services.CategoryService;
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

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;


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

    @Override
    public Product createProduct(ProductRequest product) {

        Product p = productMapper.map(product);

        p.setCategory(categoryService.getById(product.getCategoryId()));
        productRepository.save(p);
        return p;
    }

    @Override
    public Product editProduct(Long id, ProductRequest product) {

        Product p = this.getById(id);

        if(p == null) {
            return null;
        }

        p = productMapper.merge(product, p);

        p.setId(id);

        if(product.getCategoryId() != null) {
            p.setCategory(categoryService.getById(product.getCategoryId()));
        }

        productRepository.save(p);

        return p;
    }


}

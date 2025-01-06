package ch.bbw.ap.shop.productmanager.specifications;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> search(String search) {
        return (root, query, builder) -> builder.like(root.get("product"), "%" + search + "%");
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, builder) -> {
            Join<Category, Product> categoryProducts = root.join("categories");
            return builder.equal(categoryProducts.get("id"), categoryId);
        };
    }
}

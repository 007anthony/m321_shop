package ch.bbw.ap.shop.productmanager.repositories.specifications;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> search(String search) {
        return (root, query, builder) -> builder.like(root.get("product"), "%" + search + "%");
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, builder) -> {
            Path<Category> categoryPath = root.get("category");
            return builder.equal(categoryPath.get("id"), categoryId);
        };
    }
}

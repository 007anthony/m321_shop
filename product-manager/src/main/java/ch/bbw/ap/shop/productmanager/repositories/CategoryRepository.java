package ch.bbw.ap.shop.productmanager.repositories;

import ch.bbw.ap.shop.productmanager.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package ch.bbw.ap.shop.shoppingcart.repository;

import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

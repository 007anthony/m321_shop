package ch.bbw.ap.shop.shoppingcart.repository;

import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> getCartItemByProductId(Long productId);
}

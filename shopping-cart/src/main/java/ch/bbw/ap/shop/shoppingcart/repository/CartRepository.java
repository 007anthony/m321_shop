package ch.bbw.ap.shop.shoppingcart.repository;

import ch.bbw.ap.shop.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findCartByUserIdAndActiveTrue(Long userId);
}

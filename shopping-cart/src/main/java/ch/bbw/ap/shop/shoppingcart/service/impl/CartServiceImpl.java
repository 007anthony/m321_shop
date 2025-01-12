package ch.bbw.ap.shop.shoppingcart.service.impl;

import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.repository.CartRepository;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCurrentCart() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Cart> cart = cartRepository.findCartByUserIdAndActiveTrue(userId);

        if(cart.isEmpty()) {
            return null;
        }

        return cart.get();
    }

    @Override
    public Cart createCart() {
        Cart currentCart = getCurrentCart();

        if(currentCart != null) {
            return null;
        }

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Cart cart = new Cart();

        cart.setActive(true);
        cart.setUserId(userId);

        cartRepository.save(cart);

        return cart;

    }
}

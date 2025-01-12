package ch.bbw.ap.shop.shoppingcart.service.impl;

import ch.bbw.ap.shop.shoppingcart.client.UserClient;
import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.UserResponse;
import ch.bbw.ap.shop.shoppingcart.mapper.CartMapper;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.repository.CartRepository;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import ch.bbw.ap.shop.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartResponse getCurrentCart() {
        UserResponse user = userService.getCurrentUser();

        Optional<Cart> cart = cartRepository.findCartByUserIdAndActiveTrue(user.getId());

        if(cart.isEmpty()) {
            return null;
        }



        return cartMapper.map(cart.get(), user);
    }

    @Override
    public CartResponse createCart() {
        CartResponse currentCart = getCurrentCart();

        if(currentCart != null) {
            return null;
        }

        UserResponse user = userService.getCurrentUser();

        Cart cart = new Cart();

        cart.setActive(true);
        cart.setUserId(user.getId());

        cartRepository.save(cart);

        return cartMapper.map(cart, user);

    }
}

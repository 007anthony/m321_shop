package ch.bbw.ap.shop.shoppingcart.service.impl;

import ch.bbw.ap.shop.shoppingcart.client.UserClient;
import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.UserResponse;
import ch.bbw.ap.shop.shoppingcart.mapper.CartMapper;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import ch.bbw.ap.shop.shoppingcart.repository.CartItemRepository;
import ch.bbw.ap.shop.shoppingcart.repository.CartRepository;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import ch.bbw.ap.shop.shoppingcart.service.ProductService;
import ch.bbw.ap.shop.shoppingcart.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Cart getCurrentCart() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Cart> cartOptional = cartRepository.findCartByUserIdAndActiveTrue(userId);


        if(cartOptional.isEmpty()) {
            return null;
        }

        Cart cart = cartOptional.get();

        return cart;
    }

    public CartItem addProduct(Long id) {

        Cart cart = getCurrentCart();

        if(cart == null) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProductId(id);

        cartItemRepository.save(cartItem);


        return cartItem;
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

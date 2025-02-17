package ch.bbw.ap.shop.shoppingcart.service.impl;

import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.mapper.ProductMapper;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import ch.bbw.ap.shop.shoppingcart.repository.CartItemRepository;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import ch.bbw.ap.shop.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ProductResponse addProduct(Long id) {
        Cart cart = cartService.getCurrentCart();

        if(cart == null) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProductId(id);

        ProductResponse productResponse = productMapper.map(cartItem);

        if(productResponse == null) {
            return null;
        }

        cartItemRepository.save(cartItem);

        return productResponse;
    }

    @Override
    public boolean removeProduct(Long id) {
        Optional<CartItem> cartItem = cartItemRepository.getCartItemByProductId(id);

        if(cartItem.isEmpty()) {
            return false;
        }

        cartItemRepository.delete(cartItem.get());

        return true;
    }

    @Override
    public void deleteCardItems(Collection<CartItem> cartItems) {

        cartItemRepository.deleteAllInBatch(cartItems);
    }
}

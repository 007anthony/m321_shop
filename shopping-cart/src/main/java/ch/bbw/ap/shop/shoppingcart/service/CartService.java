package ch.bbw.ap.shop.shoppingcart.service;

import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;

public interface CartService {

    Cart getCurrentCart();

    CartItem addProduct(Long id);

    Cart createCart();
}

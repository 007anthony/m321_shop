package ch.bbw.ap.shop.shoppingcart.service;

import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.model.Cart;

public interface CartService {

    CartResponse getCurrentCart();

    CartResponse createCart();
}

package ch.bbw.ap.shop.shoppingcart.service;

import ch.bbw.ap.shop.shoppingcart.model.Cart;

public interface CartService {

    Cart getCurrentCart();

    Cart createCart();
}

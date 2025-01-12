package ch.bbw.ap.shop.shoppingcart.service;

import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;

import java.util.Collection;

public interface ProductService {

    ProductResponse addProduct(Long id);

    boolean removeProduct(Long id);

    void deleteCardItems(Collection<CartItem> cartItems);
}

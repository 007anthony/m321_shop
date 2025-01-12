package ch.bbw.ap.shop.shoppingcart.service;

import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(Long id);
}

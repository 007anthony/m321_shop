package ch.bbw.ap.shop.shoppingcart.service.impl;

import ch.bbw.ap.shop.shoppingcart.client.ProductClient;
import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import ch.bbw.ap.shop.shoppingcart.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductResponse getProduct(Long id) {
        return productClient.getProduct(id);
    }
}

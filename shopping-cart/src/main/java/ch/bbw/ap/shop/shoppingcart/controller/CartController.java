package ch.bbw.ap.shop.shoppingcart.controller;

import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.mapper.CartMapper;
import ch.bbw.ap.shop.shoppingcart.mapper.ProductMapper;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import ch.bbw.ap.shop.shoppingcart.model.ProductRequest;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import ch.bbw.ap.shop.shoppingcart.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<CartResponse> createCart() {
        Cart cart = cartService.createCart();

        if(cart == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).build();
        }

        CartResponse response = cartMapper.map(cart);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/items")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        CartItem cartItem = cartService.addProduct(productRequest.getProductId());

        if(cartItem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.mapProduct(cartItem));
    }
}

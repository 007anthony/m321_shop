package ch.bbw.ap.shop.shoppingcart.controller;

import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();

        if(cart == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).build();
        }

        return ResponseEntity.ok(cart);
    }
}

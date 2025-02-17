package ch.bbw.ap.shop.shoppingcart.controller;

import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.mapper.CartMapper;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.model.ProductRequest;
import ch.bbw.ap.shop.shoppingcart.repository.CartRepository;
import ch.bbw.ap.shop.shoppingcart.service.CartService;
import ch.bbw.ap.shop.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart() {
        Cart cart = cartService.createCart();

        if(cart == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).build();
        }

        CartResponse response = cartMapper.map(cart);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CartResponse> getProducts() {
        CartResponse cart = cartMapper.map(cartService.getCurrentCart());
        if(cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping
    public CartResponse clearCart() {
        Cart cart = cartService.getCurrentCart();

        productService.deleteCardItems(cart.getCartItems());
        cart.setCartItems(new HashSet<>());
        return cartMapper.map(cartService.getCurrentCart());
    }

    @PostMapping("/items")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        try {
            ProductResponse product = productService.addProduct(productRequest.getProductId());

            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(product);
        }
        catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).build();
        }
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity removeProduct(@PathVariable Long productId) {
        if(productService.removeProduct(productId)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

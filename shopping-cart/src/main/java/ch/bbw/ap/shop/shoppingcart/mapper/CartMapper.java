package ch.bbw.ap.shop.shoppingcart.mapper;

import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.UserResponse;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import ch.bbw.ap.shop.shoppingcart.service.ProductService;
import ch.bbw.ap.shop.shoppingcart.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public abstract class CartMapper {

    @Autowired
    private UserService userService;

    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "products", source = "cartItems", defaultExpression = "java(new ArrayList())")
    @Mapping(target = "user", expression = "java(getCurrentUser())")
    public abstract CartResponse map(Cart cart);

}

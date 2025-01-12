package ch.bbw.ap.shop.shoppingcart.mapper;

import ch.bbw.ap.shop.shoppingcart.client.response.CartResponse;
import ch.bbw.ap.shop.shoppingcart.client.response.UserResponse;
import ch.bbw.ap.shop.shoppingcart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "id", source = "cart.id")
    CartResponse map(Cart cart, UserResponse user);
}

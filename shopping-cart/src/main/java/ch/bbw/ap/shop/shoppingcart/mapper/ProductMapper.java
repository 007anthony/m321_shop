package ch.bbw.ap.shop.shoppingcart.mapper;

import ch.bbw.ap.shop.shoppingcart.client.response.ProductResponse;
import ch.bbw.ap.shop.shoppingcart.model.CartItem;
import ch.bbw.ap.shop.shoppingcart.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    private ProductService productService;

    public abstract List<ProductResponse> mapProducts(List<CartItem> cartItems);


    public ProductResponse mapProduct(CartItem cartItem) {
        return productService.getProduct(cartItem.getProductId());
    }
}

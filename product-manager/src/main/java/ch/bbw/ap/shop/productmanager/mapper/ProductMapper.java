package ch.bbw.ap.shop.productmanager.mapper;

import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.models.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product map(ProductRequest productRequest);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "product", source = "productRequest.product")
    @Mapping(target = "price", source = "productRequest.price")
    Product merge(ProductRequest productRequest, Product product);
}

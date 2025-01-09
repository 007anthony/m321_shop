package ch.bbw.ap.shop.productmanager.mapper;

import ch.bbw.ap.shop.productmanager.models.Product;
import ch.bbw.ap.shop.productmanager.models.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product map(ProductRequest productRequest);
}

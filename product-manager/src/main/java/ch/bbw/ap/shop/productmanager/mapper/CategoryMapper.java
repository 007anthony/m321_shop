package ch.bbw.ap.shop.productmanager.mapper;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public Category map(CategoryRequest categoryRequest);
}

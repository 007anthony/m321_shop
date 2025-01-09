package ch.bbw.ap.shop.productmanager.mapper;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category map(CategoryRequest categoryRequest);

    @Mapping(target = "id", source = "category.id")
    @Mapping(target = "category", source = "categoryRequest.category")
    Category merge(CategoryRequest categoryRequest, Category category);
}

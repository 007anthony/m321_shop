package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    Category createCategory(CategoryRequest categoryRequest);
}

package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);
}

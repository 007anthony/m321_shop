package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.mapper.CategoryMapper;
import ch.bbw.ap.shop.productmanager.models.Category;
import ch.bbw.ap.shop.productmanager.models.CategoryRequest;
import ch.bbw.ap.shop.productmanager.repositories.CategoryRepository;
import ch.bbw.ap.shop.productmanager.services.CategoryService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty()) {
            return null;
        }

        return category.get();
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.map(categoryRequest);

        categoryRepository.save(category);

        return category;
    }

    @Override
    public Category editCategory(Long id, CategoryRequest categoryRequest) {
        Category category = this.getById(id);

        if(category == null) {
            return null;
        }

        category = categoryMapper.merge(categoryRequest, category);

        categoryRepository.save(category);

        return category;
    }
}

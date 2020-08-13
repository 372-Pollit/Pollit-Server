package com.pollit.server.service;

import com.pollit.server.model.Category;
import com.pollit.server.repository.CategoryRepository;
import com.pollit.server.serviceinterface.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public void remove(int categoryId) {
        categoryRepository.remove(categoryId);
    }

    @Override
    public void add(String categoryName) {
        categoryRepository.add(categoryName);
    }

    @Override
    public void update(int categoryId, String categoryName) {
        categoryRepository.update(categoryId, categoryName);
    }

}

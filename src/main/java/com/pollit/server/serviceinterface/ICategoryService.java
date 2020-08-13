package com.pollit.server.serviceinterface;

import com.pollit.server.model.Category;

import java.util.List;

public interface ICategoryService {

    public List<Category> findAll();

    public void remove(int categoryId);

    public void add(String categoryName);

    public void update(int categoryId, String categoryName);

}

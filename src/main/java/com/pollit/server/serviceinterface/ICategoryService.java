package com.pollit.server.serviceinterface;

import com.pollit.server.model.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> findAll();
}

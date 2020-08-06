package com.pollit.server.serviceinterface;

import com.pollit.server.model.Category;

import java.util.List;

public interface ISubsService {
    List<Category> findSubbedCategories(int userId);

    void unSub(int userId, int categoryId);

    List<Category> findUnSubbedCategories(int userId);

    void sub(Integer userId, Integer categoryId);
}

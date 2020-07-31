package com.pollit.server.controller;

import com.pollit.server.model.Category;
import com.pollit.server.serviceinterface.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryContoller {

    private final ICategoryService categoryService;

    public CategoryContoller(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/findAll")
    public List<Category> findAll() {
        List<Category> list = categoryService.findAll();
        list.sort(Comparator.comparing(Category::getName));
        return list;
    }
}

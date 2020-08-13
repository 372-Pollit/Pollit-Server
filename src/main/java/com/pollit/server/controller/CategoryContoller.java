package com.pollit.server.controller;

import com.pollit.server.model.Category;
import com.pollit.server.serviceinterface.ICategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
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

    @PostMapping("/remove")
    public void remove(@RequestBody HashMap req) {
        categoryService.remove(Integer.valueOf(req.get("categoryId").toString()));
    }

    @PostMapping("/add")
    public void add(@RequestBody HashMap req) {
        categoryService.add(req.get("categoryName").toString());
    }

    @PostMapping("/update")
    public void update(@RequestBody HashMap req) {
        categoryService.update(Integer.valueOf(req.get("categoryId").toString()),
                req.get("categoryName").toString());
    }

}

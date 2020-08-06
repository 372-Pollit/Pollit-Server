package com.pollit.server.controller;

import com.pollit.server.model.Category;
import com.pollit.server.serviceinterface.ISubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/subs")
@CrossOrigin("*")
public class SubsController {

    private final ISubsService subsService;

    public SubsController(ISubsService subsService) {
        this.subsService = subsService;
    }

    @GetMapping("/subbed")
    public List<Category> findSubbedCategories(@Param("userId") int userId) {
        return subsService.findSubbedCategories(userId);
    }

    @PostMapping("/unSub")
    public void unSub(@RequestBody HashMap req) {
        subsService.unSub(Integer.valueOf(req.get("userId").toString()),
                Integer.valueOf(req.get("categoryId").toString()));

    }

    @GetMapping("/unSubbed")
    public List<Category> findUnSubbedCategories(@Param("userId") int userId) {
        return subsService.findUnSubbedCategories(userId);
    }

    @PostMapping("/sub")
    public void sub(@RequestBody HashMap req) {
        subsService.sub(Integer.valueOf(req.get("userId").toString()),
                Integer.valueOf(req.get("categoryId").toString()));
    }
}

package com.pollit.server.controller;

import com.pollit.server.model.Belongs;
import com.pollit.server.serviceinterface.IBelongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/belongs")
public class BelongController {

    private final IBelongsService belongsService;

    public BelongController(IBelongsService belongsService) {
        this.belongsService = belongsService;
    }

    @GetMapping("/findAll")
    public List<Belongs> findAll() {
        return belongsService.findAll();
    }
}

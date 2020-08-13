package com.pollit.server.controller;

import com.pollit.server.model.Moderator;
import com.pollit.server.service.ModeratorService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/moderator")
@CrossOrigin("*")
public class ModeratorController {

    private final ModeratorService moderatorService;

    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @GetMapping("/findAll")
    public List<Moderator> findAll() {
        return moderatorService.findAll();
    }

    @GetMapping("/isModerator")
    public Moderator isModerator(@Param("id") int id) {
        return moderatorService.isModerator(id);
    }

    @PostMapping("/remove")
    public void remove(@RequestBody HashMap req) {
        moderatorService.remove(req);
    }

    @PostMapping("/add")
    public void add(@RequestBody HashMap req) {
        moderatorService.add(req);
    }

}

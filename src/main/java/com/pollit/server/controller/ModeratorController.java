package com.pollit.server.controller;

import com.pollit.server.model.Moderator;
import com.pollit.server.service.ModeratorService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

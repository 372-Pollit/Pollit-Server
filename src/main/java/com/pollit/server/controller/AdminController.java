package com.pollit.server.controller;

import com.pollit.server.model.Admin;
import com.pollit.server.serviceinterface.IAdminService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/findAll")
    public List<Admin> findAll() {
        return adminService.findAll();
    }

    @GetMapping("/isAdmin")
    public Admin isAdmin(@Param("id") int id) {
        return adminService.isAdmin(id);
    }

}

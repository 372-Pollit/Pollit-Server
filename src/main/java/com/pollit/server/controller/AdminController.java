package com.pollit.server.controller;

import com.pollit.server.model.Admin;
import com.pollit.server.serviceinterface.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/findAll")
    public List<Admin> findAll() {
        return adminService.findAll();
    }

}

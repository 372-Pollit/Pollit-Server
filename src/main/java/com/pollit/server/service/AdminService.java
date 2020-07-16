package com.pollit.server.service;

import com.pollit.server.model.Admin;
import com.pollit.server.repository.AdminRepository;
import com.pollit.server.serviceinterface.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Admin> findAll() {
        return (List<Admin>) repository.findAll();
    }
}

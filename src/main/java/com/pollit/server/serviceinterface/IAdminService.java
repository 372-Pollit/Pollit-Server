package com.pollit.server.serviceinterface;

import com.pollit.server.model.Admin;

import java.util.List;

public interface IAdminService {

    public List<Admin> findAll();

    public Admin isAdmin(int id);

}

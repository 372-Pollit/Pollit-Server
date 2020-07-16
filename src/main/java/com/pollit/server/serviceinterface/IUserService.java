package com.pollit.server.serviceinterface;


import com.pollit.server.model.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();

}

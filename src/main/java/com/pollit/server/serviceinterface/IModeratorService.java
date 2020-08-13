package com.pollit.server.serviceinterface;


import com.pollit.server.model.Moderator;

import java.util.List;

public interface IModeratorService {

    public List<Moderator> findAll();

    public Moderator isModerator(int id);

}

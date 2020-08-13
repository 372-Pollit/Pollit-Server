package com.pollit.server.service;

import com.pollit.server.model.Moderator;
import com.pollit.server.repository.ModeratorRepository;
import com.pollit.server.serviceinterface.IModeratorService;
import com.pollit.server.util.Crud;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorService extends Crud implements IModeratorService {

    private final ModeratorRepository repository;

    public ModeratorService(ModeratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Moderator> findAll() {
        return (List<Moderator>) repository.findAll();
    }

    @Override
    public Moderator isModerator(int id) {
        return repository.isModerator(id);
    }

}

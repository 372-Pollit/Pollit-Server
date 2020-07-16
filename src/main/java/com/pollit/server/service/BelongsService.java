package com.pollit.server.service;

import com.pollit.server.model.Belongs;
import com.pollit.server.repository.BelongsRepository;
import com.pollit.server.serviceinterface.IBelongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BelongsService implements IBelongsService {

    private final BelongsRepository repository;

    public BelongsService(BelongsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Belongs> findAll() {
        return (List<Belongs>) repository.findAll();
    }
}

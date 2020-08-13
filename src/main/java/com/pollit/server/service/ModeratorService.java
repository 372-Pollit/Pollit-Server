package com.pollit.server.service;

import com.pollit.server.model.Admin;
import com.pollit.server.model.Moderator;
import com.pollit.server.model.User;
import com.pollit.server.repository.ModeratorRepository;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.IModeratorService;
import com.pollit.server.util.Crud;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService extends Crud implements IModeratorService {

    private final ModeratorRepository repository;
    private final UserRepository userRepository;

    public ModeratorService(ModeratorRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Moderator> findAll() {
        return (List<Moderator>) repository.findAll();
    }

    @Override
    public Moderator isModerator(int id) {
        return repository.isModerator(id);
    }

    @Override
    public void remove(HashMap req) {
        repository.remove(Integer.valueOf(req.get("id").toString()));
    }

    @Override
    public void add(HashMap req) {
        repository.add(Integer.valueOf(req.get("id").toString()));
    }

    @Override
    @Modifying
    @Transactional
    public void blockUser(HashMap req) {
        repository.blockUser(Integer.valueOf(req.get("moderatorId").toString()), Integer.valueOf(req.get("userId").toString()));
        userRepository.findById(Integer.valueOf(req.get("userId").toString())).get().setBlocked(true);
    }

    @Override
    @Modifying
    @Transactional
    public void unblockUser(HashMap req) {
        repository.unblockUser(Integer.valueOf(req.get("moderatorId").toString()), Integer.valueOf(req.get("userId").toString()));
        userRepository.findById(Integer.valueOf(req.get("userId").toString())).get().setBlocked(false);
    }

}

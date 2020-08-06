package com.pollit.server.service;

import com.pollit.server.model.Category;
import com.pollit.server.model.Subs;
import com.pollit.server.repository.CategoryRepository;
import com.pollit.server.repository.SubsRepository;
import com.pollit.server.repository.UserRepository;
import com.pollit.server.serviceinterface.ISubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SubsService implements ISubsService {

    private final SubsRepository subsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public SubsService(SubsRepository subsRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.subsRepository = subsRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findSubbedCategories(int userId) {
        return subsRepository.findSubbedCategories(userId);
    }

    @Override
    public void unSub(int userId, int categoryId) {
        subsRepository.unSub(userId, categoryId);
    }

    @Override
    public List<Category> findUnSubbedCategories(int userId) {
        return subsRepository.findUnSubbedCategories(userId);
    }

    @Override
    @Transactional
    public void sub(Integer userId, Integer categoryId) {
        Subs subs = new Subs();
        subs.setUser(userRepository.findById(userId).get());
        subs.setCategory(categoryRepository.findById(categoryId).get());
        subs.setDate(new Timestamp(System.currentTimeMillis()));
        subsRepository.save(subs);
    }
}

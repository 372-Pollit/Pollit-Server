package com.pollit.server.repository;

import com.pollit.server.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
}

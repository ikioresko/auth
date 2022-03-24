package com.shop.auth.repository;

import com.shop.auth.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findUsersByUsername(String username);
}

package com.shop.auth.controller;

import com.shop.auth.model.Users;
import com.shop.auth.service.UsersServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UsersServiceImpl usersServiceImpl;

    public UserController(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @PostMapping("/sign-up")
    public boolean save(@RequestBody Users user) {
        return usersServiceImpl.addUser(user);
    }
}

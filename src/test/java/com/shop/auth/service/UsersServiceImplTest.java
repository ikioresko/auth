package com.shop.auth.service;

import com.shop.auth.model.Role;
import com.shop.auth.model.Users;
import com.shop.auth.repository.UsersRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersServiceImplTest {
    @Autowired
    private UsersServiceImpl usersService;

    @MockBean
    private UsersRepo repo;

    @Test
    void addUserTest() {
        Users user = new Users();
        user.setUsername("admin");
        user.setPassword("123");
        boolean isUserCreated = usersService.addUser(user);
        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        Mockito.verify(repo, Mockito.times(1)).save(user);
    }

    @Test
    public void addUserFailTest() {
        Users user = new Users();
        user.setUsername("admin");
        Mockito.doReturn(user)
                .when(repo)
                .findUsersByUsername("admin");
        boolean isUserCreated = usersService.addUser(user);
        Assert.assertFalse(isUserCreated);
        Mockito.verify(repo, Mockito.times(0)).save(ArgumentMatchers.any(Users.class));
    }
}
package com.shop.auth.service;

import com.shop.auth.model.Role;
import com.shop.auth.model.Users;
import com.shop.auth.repository.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@Transactional
public class UsersServiceImpl implements UserDetailsService {
    private final UsersRepo repo;
    private final BCryptPasswordEncoder encoder;

    public UsersServiceImpl(UsersRepo repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true)
    public Users findUserByName(String username) {
        return repo.findUsersByUsername(username);
    }

    public boolean addUser(Users user) {
        boolean rsl = true;
        Users userFromDb = repo.findUsersByUsername(user.getUsername());
        if (userFromDb != null) {
            rsl = false;
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            user.getRoles().add(Role.USER);
            repo.save(user);
            log.info("User create new account with username: {}", user.getUsername());
        }
        return rsl;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findUsersByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}

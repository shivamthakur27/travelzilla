package com.travelzilla.service;

import com.travelzilla.model.User;
import com.travelzilla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}

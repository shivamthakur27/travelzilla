package com.travelzilla.service;

import com.travelzilla.model.User;

public interface UserService
{
    void save(User user) throws Exception;

    User findByEmail(String email);
}

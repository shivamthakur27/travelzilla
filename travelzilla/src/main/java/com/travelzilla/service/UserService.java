package com.travelzilla.service;

import com.travelzilla.dto.NewUserDto;
import com.travelzilla.dto.UserDetailDto;
import com.travelzilla.model.User;

public interface UserService
{
    public NewUserDto createUser(UserDetailDto user) throws Exception;


}

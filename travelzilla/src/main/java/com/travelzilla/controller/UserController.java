package com.travelzilla.controller;

import com.travelzilla.dto.NewUserDto;
import com.travelzilla.dto.RtnDto;
import com.travelzilla.dto.UserDetailDto;
import com.travelzilla.model.User;
import com.travelzilla.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/signup", consumes = {"multipart/form-data"})
    public ResponseEntity<RtnDto> createUser(UserDetailDto userDetailDto) {

        NewUserDto newUserDetail = userService.createUser(userDetailDto);

        RtnDto rtnDto = new RtnDto();
        rtnDto.setStatusCode(201);
        rtnDto.setMessage("Employee created successfully");
        rtnDto.setData(newUserDetail);
        return ResponseEntity.ok(rtnDto);
    }

}

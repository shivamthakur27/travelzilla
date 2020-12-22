package com.travelzilla.dto;

import lombok.Data;

@Data
public class UserDetailDto
{

    private long id;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
    private long phoneNumber;
    private long roleId;
}

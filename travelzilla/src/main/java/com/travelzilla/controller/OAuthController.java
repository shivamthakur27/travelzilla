package com.travelzilla.controller;

import com.travelzilla.common.OAuthTokenUtil;
import com.travelzilla.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController
{

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    OAuthTokenUtil oAuthTokenUtil;

    @Autowired
    protected TokenStore tokenStore;

    @Autowired
    ConsumerTokenServices tokenServices;

    private String errorKey = "error";
    private String statusKey = "status";
    private String messageKey = "message";
    private String errorDesKey = "error_description";

}

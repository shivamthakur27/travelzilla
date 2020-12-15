package com.travelzilla.controller;

import com.travelzilla.model.User;
import com.travelzilla.service.SecurityService;
import com.travelzilla.service.UserServiceImpl;
import com.travelzilla.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController
{
    @Autowired
    UserValidator userValidator;

    @Autowired
    private SecurityService securityService;


    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "application/json")
    public ResponseEntity<String> registration(@RequestBody User user) throws Exception {


        if (userService.findByEmail(user.getEmail()) != null)
        {
         return new ResponseEntity<String>("User Already Present" , HttpStatus.CONFLICT);
        }


        userService.save(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());

        return new ResponseEntity<String>("User Created!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login", produces = "application/json")
    public String login(@RequestParam String email , @RequestParam String password) {

        return "login";
    }
}

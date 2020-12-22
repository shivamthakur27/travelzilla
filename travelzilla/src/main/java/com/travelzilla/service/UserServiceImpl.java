package com.travelzilla.service;

import com.travelzilla.dto.CustomUserDetailsDto;
import com.travelzilla.dto.NewUserDto;
import com.travelzilla.dto.UserDetailDto;
import com.travelzilla.model.Role;
import com.travelzilla.model.User;
import com.travelzilla.repository.RoleRepository;
import com.travelzilla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService , UserDetailsService
{

    @Autowired
    UserServiceImpl userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public NewUserDto createUser(UserDetailDto userDetailDto)  {

        User user = new User();
        user.setName(userDetailDto.getName());
        user.setEmail(userDetailDto.getEmail());
        user.setPassword(encoder.encode(userDetailDto.getPassword()));
        user.setPhoneNumber(userDetailDto.getPhoneNumber());
        Role role = new Role();
        role.setId(userDetailDto.getRoleId());
        user.setRole(role);

        userRepository.save(user);

        NewUserDto newUserDetail = new NewUserDto();
        newUserDetail.setNewUserId(user.getId());

        return newUserDetail;
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Invalid email : " + email));
        return user.map(CustomUserDetailsDto::new).get();
    }

}

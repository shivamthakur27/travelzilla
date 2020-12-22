package com.travelzilla.dto;

import com.travelzilla.model.Role;
import com.travelzilla.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsDto implements UserDetails
{
    private long id;
    private String password;
    private String email;
    private String name;
    private long roleId;
    private Role role;
    private User user;

    private static final long serialVersionUID = 1L;

    public CustomUserDetailsDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.roleId = role.getId();
        this.user = user;
        this.name = user.getName();
    }

    public CustomUserDetailsDto() {	}

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public String getUsername() {
        return name;
    }


    public long getRoleId() {
        return roleId;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String roleName = "ROLE_" + role.getName().toUpperCase();
        authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
    }
}

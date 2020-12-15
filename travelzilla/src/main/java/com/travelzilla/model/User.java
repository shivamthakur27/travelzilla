package com.travelzilla.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name ="users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3 , max = 40)
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 8, max = 30)
    private String password;

    @Transient
    private String passwordConfirm;

    @NotBlank
    private long phoneNumber;

    @ManyToMany
    private Set<Role> roles;


}
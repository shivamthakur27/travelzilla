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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long id;

    @Column(name="Name",nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3 , max = 40)
    private String name;

    @Column(name="Email",nullable = false)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name="Password",nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 30)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name="PhoneNumber",nullable = false)
    @NotBlank(message = "Phone Number cannot be blank")
    @Size(min = 10 , max = 10)
    private long phoneNumber;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

}
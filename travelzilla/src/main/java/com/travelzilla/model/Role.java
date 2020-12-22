package com.travelzilla.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = -6608484483521517137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.MERGE,
            mappedBy = "role")
    private List<User> user;
}

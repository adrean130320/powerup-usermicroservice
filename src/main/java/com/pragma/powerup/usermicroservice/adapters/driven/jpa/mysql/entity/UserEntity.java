package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String address;
    private String idDniType;
    @Column(unique = true, nullable = false, length = 20)
    private String dniNumber;
    private String idPersonType;
    private String password;
    private String tokenPassword;
    @Temporal(TemporalType.DATE) // aquí se indica que solo se debe considerar la parte de fecha
    private Date birthdate;
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_role")
    private RoleEntity role;
}

package com.epsi.blockchainsmokers.workshopi4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String username;

    private String password;

    public Utilisateur(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
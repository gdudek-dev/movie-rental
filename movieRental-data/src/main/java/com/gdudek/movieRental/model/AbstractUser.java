package com.gdudek.movieRental.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class AbstractUser extends AbstractTimestamp {

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Override
    protected void onCreate() {
        super.onCreate();

    }

}

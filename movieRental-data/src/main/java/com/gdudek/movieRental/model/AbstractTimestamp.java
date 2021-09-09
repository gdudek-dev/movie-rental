package com.gdudek.movieRental.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;


@MappedSuperclass
@Getter
public abstract class AbstractTimestamp {


    @Column(name = "updated")
    private LocalDateTime updated;

    @PrePersist
    protected void onCreate() {
        updated  = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }
}

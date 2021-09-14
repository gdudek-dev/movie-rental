package com.gdudek.movieRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTimestamp {


    @Column(name = "updated")
    private LocalDateTime updated;

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PrePersist
    protected void onCreate() {
        updated  = LocalDateTime.parse(formatter.format(LocalDateTime.now()),formatter);
    }

    @PreUpdate
    protected void onUpdate() {
        updated  = LocalDateTime.parse(formatter.format(LocalDateTime.now()),formatter);
    }
}

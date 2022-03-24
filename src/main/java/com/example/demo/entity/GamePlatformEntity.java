package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GamePlatformEntity {
    @Id
    @GeneratedValue
    private int id;
}

package com.example.dotodo.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_user") //user가 table 지정 안됨.
public class User
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(unique = true, length = 20) //중복 불가
    private String username;

    private String password;

}

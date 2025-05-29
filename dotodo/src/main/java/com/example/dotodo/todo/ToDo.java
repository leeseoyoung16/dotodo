package com.example.dotodo.todo;

import com.example.dotodo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ToDo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 지정
    private Long id;

    @Column
    private String content;

    @Column
    private boolean complete;

    private LocalDate date;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) //외래키 지정
    private User user;

}

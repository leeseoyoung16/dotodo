package com.example.dotodo.todo;

import com.example.dotodo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long>
{
    ToDo findByUserAndId (User user, Long id);//할일 가져오기
    List<ToDo> findByUserAndDate(User user, LocalDate date); //할일 목록 들고오기
}

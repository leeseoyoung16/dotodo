package com.example.dotodo.todo;

import com.example.dotodo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService
{
    private final ToDoRepository toDoRepository;

    //할일 목록 가져오기
    public List<ToDo> getToDo(User user, LocalDate date) {
        return toDoRepository.findByUserAndDate(user,date);
    }

    //할 일 가져오기
    public ToDo getToDoById(User user, Long id) {
        return toDoRepository.findByUserAndId(user,id);
    }

    //할일 생성
    public void createToDo(String content, User user, LocalDate date) {
        ToDo toDo = new ToDo();
        toDo.setContent(content);
        toDo.setComplete(false);
        toDo.setDate(date);
        toDo.setUser(user);
        toDo.setCreatedDate(LocalDateTime.now());
        this.toDoRepository.save(toDo);
    }

    //할일 삭제
    public void delete(ToDo toDo) {
        this.toDoRepository.delete(toDo);
    }

    //할일 수정
    public void modifyToDo(ToDo toDo, String content, LocalDate date, boolean complete)
    {
        toDo.setContent(content);
        toDo.setDate(date);
        toDo.setComplete(complete);
        toDo.setModifiedDate(LocalDateTime.now());
        this.toDoRepository.save(toDo);
    }
}

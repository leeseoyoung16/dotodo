package com.example.dotodo.todo;

import com.example.dotodo.user.User;
import com.example.dotodo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.security.Principal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;
    private final UserService userService;

    // 달력 화면
    @GetMapping("/calendar")
    public String calendar(@RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month, Model model) {
        LocalDate today = LocalDate.now();
        int displayYear = (year != null) ? year : today.getYear();
        int displayMonth = (month != null) ? month : today.getMonthValue();

        model.addAttribute("year", displayYear);
        model.addAttribute("month", displayMonth); //뷰 템플릿에서 씀.

        int lastDay = YearMonth.of(displayYear, displayMonth).lengthOfMonth();
        model.addAttribute("lastDay", lastDay); //마지막 날 템플릿에 전송

        return "calendar";
    }

    // 할 일 목록 조회
    @GetMapping("/list")
    public String getToDo(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                          Model model, Principal principal) {
        User user = userService.getUser(principal.getName()); //user 가져오기
        List<ToDo> toDoList = toDoService.getToDo(user, date); //같은 날짜인거 다 들고오기

        model.addAttribute("toDoList", toDoList);
        model.addAttribute("date", date);

        return "todo_list";
    }

    // 할 일 생성
    @PostMapping("/create")
    public String create(@RequestParam("content") String content,
                         @RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date, Principal principal) {
        User user = userService.getUser(principal.getName());
        toDoService.createToDo(content, user, date); //할일 생성

        return "redirect:/todo/list?date=" + date;
    }

    // 할 일 수정
    @PostMapping("/modify")
    public String modify(@RequestParam("id") Long id,
                         @RequestParam("content") String content,
                         @RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                         @RequestParam(value = "complete", required = false, defaultValue = "false") boolean complete,
                         Principal principal) {
        User user = userService.getUser(principal.getName());
        ToDo toDo = toDoService.getToDoById(user, id);
        toDoService.modifyToDo(toDo, content, date, complete);

        return "redirect:/todo/list?date=" + date;
    }

    // 할 일 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id,
                         @RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date, Principal principal) {
        User user = userService.getUser(principal.getName());
        ToDo toDo = toDoService.getToDoById(user, id);
        toDoService.delete(toDo);

        return "redirect:/todo/list?date=" + date;
    }

    //할 일 완료 토글
    @GetMapping("/toggle")
    public String toggleComplete(@RequestParam Long id,
                                 @RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                 @RequestParam(value = "complete", required = false) Boolean complete, Principal principal) {
        User user = userService.getUser(principal.getName());
        ToDo toDo = toDoService.getToDoById(user, id);
        boolean newComplete = (complete != null) ? complete : toDo.isComplete();
        this.toDoService.modifyToDo(toDo, toDo.getContent(), date, !newComplete);
        return "redirect:/todo/list?date=" + date;
    }

}

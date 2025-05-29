package com.example.dotodo.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //에러가 생기면 signup_form 다시 부르기
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) { //2개의 비밀번호가 다르면
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }
        try {
            userService.create(userCreateForm.getUsername(),
                    passwordEncoder.encode(userCreateForm.getPassword1()));
        } catch (DataIntegrityViolationException e) { // 무결성 제약조건 위반 (id 중복)
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 사용 중인 사용자 ID입니다.");
            return "signup_form";
        } catch (Exception e) { //일반 오류
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/user/login"; //에러 없이 성공한다면 로그인 페이지로
    }
}

package com.example.dotodo.user;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm
{
    @Size(min=3, max=20)
    @NotEmpty(message = "ID는 필수 입력 항목입니다.")
    private String username;

    @Size(min=8, max=20)
    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String password2;

}

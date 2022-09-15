package com.lhs.board_project.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
public class UserSignInRequest {


    @NotEmpty(message = "Please enter your Email")
    @Email
    private String email;
    @NotEmpty(message = "Please enter your Password")
    private String password;

    @Builder
    public UserSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

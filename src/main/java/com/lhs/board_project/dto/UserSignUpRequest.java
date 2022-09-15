package com.lhs.board_project.dto;

import com.lhs.board_project.domain.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {
    @NotEmpty(message = "please email!!!")
    @Email
    private String email;
    @NotEmpty(message = "please nickName!!")
    private String nickName;
    @NotEmpty(message = "please password!!!")
    private String password;
    @NotEmpty(message = "please name!!!!")
    private String name;

    @Builder
    public UserSignUpRequest(String email, String nickName, String password, String name) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
    }

    public User toUserEntity() {
        return User
                .builder()
                .email(this.getEmail())
                .nickName(this.getNickName())
                .password(this.getPassword())
                .name(this.getName())
                .build();
    }
 }

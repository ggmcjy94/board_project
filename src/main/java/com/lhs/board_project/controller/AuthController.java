package com.lhs.board_project.controller;

import com.lhs.board_project.service.UserSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
//https://hou27.tistory.com/entry/Spring-Security-JWT
public class AuthController {

    private final UserSevice userService;


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Validated UserSignRequest signRequest) {

    }

}

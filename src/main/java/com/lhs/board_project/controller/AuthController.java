package com.lhs.board_project.controller;

import com.lhs.board_project.config.jwt.dto.TokenDto;
import com.lhs.board_project.dto.UserSignInRequest;
import com.lhs.board_project.dto.UserSignUpRequest;
import com.lhs.board_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
//https://hou27.tistory.com/entry/Spring-Security-JWT
public class AuthController {

    private final UserService userService;
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Validated @RequestBody UserSignUpRequest request) {
        return new ResponseEntity<>(userService.signUp(request), HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@Validated @RequestBody UserSignInRequest request) {
        return userService.signIn(request);
    }
}

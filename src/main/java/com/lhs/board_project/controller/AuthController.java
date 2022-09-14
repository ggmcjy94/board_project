package com.lhs.board_project.controller;

import com.lhs.board_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
//https://hou27.tistory.com/entry/Spring-Security-JWT
public class AuthController {

    private final UserService userService;


}

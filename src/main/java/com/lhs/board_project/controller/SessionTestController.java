package com.lhs.board_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SessionTestController {

    @GetMapping
    public String main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = "혿길동";
        session.setAttribute("user", name);
        return "main";
    }



}

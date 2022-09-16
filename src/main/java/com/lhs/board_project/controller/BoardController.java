package com.lhs.board_project.controller;


import com.lhs.board_project.domain.User;
import com.lhs.board_project.dto.BoardCreateRequest;
import com.lhs.board_project.ex.UserNotFoundException;
import com.lhs.board_project.repository.BoardRepository;
import com.lhs.board_project.service.BoardService;
import com.lhs.board_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<?> createBoardController(@AuthenticationPrincipal UserDetails userDetails,
                                                   @Validated @RequestBody BoardCreateRequest boardCreateRequest) {
        if (userDetails != null) {
            User user = userService.findByEmail(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
            boardService.create(boardCreateRequest, user);
            return new ResponseEntity<>(boardCreateRequest, HttpStatus.OK);
        } else {
            throw new RuntimeException();
        }
    }

}

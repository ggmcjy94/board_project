package com.lhs.board_project.controller;


import com.lhs.board_project.domain.User;
import com.lhs.board_project.dto.board.BoardCreateRequest;
import com.lhs.board_project.dto.board.BoardResponse;
import com.lhs.board_project.dto.board.BoardUpdateRequest;
import com.lhs.board_project.ex.UserNotFoundException;
import com.lhs.board_project.service.BoardService;
import com.lhs.board_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> createBoardController(@AuthenticationPrincipal UserDetails userDetails,
                                                   @Validated @RequestBody BoardCreateRequest boardCreateRequest) {
        User user = getAuthenticationUser(userDetails);
        boardService.create(boardCreateRequest, user);
        return new ResponseEntity<>(boardCreateRequest, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<BoardResponse>> findAllBoardController() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoardIdController(@PathVariable("id") Long id) {
        return new ResponseEntity<>(boardService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<List<BoardResponse>> myBoardsController(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(boardService.getBoardsUserEmail(getAuthenticationUser(userDetails)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBoardController(@PathVariable("id") Long id,
                                                   @AuthenticationPrincipal UserDetails userDetails,
                                                   @Validated @RequestBody BoardUpdateRequest boardUpdateRequest) {
        User user = getAuthenticationUser(userDetails);
        boardService.update(id,boardUpdateRequest, user);
        return new ResponseEntity<>(boardUpdateRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoardController(@PathVariable("id") Long id,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        User user = getAuthenticationUser(userDetails);
        boardService.delete(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private User getAuthenticationUser(UserDetails userDetails) {
        if (userDetails != null) {
            return userService.findByEmail(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        } else {
            throw new RuntimeException();
        }
    }
}

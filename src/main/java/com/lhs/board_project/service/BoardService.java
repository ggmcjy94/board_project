package com.lhs.board_project.service;


import com.lhs.board_project.domain.Board;
import com.lhs.board_project.domain.User;
import com.lhs.board_project.dto.BoardCreateRequest;
import com.lhs.board_project.dto.BoardResponse;
import com.lhs.board_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void create(BoardCreateRequest boardCreateRequest, User user) {

        Board board = Board
                .builder()
                .title(boardCreateRequest.getTitle())
                .content(boardCreateRequest.getContent())
                .user(user).build();
        boardRepository.save(board);
    }

    public List<BoardResponse> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards
                .stream()
                .map(this::getBoardResponse)
                .toList();
    }

    private BoardResponse getBoardResponse(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUser().getName(),
                board.getUser().getEmail(),
                board.getCreatedAt());
    }
}

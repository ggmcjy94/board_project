package com.lhs.board_project.service;


import com.lhs.board_project.document.BoardDoc;
import com.lhs.board_project.domain.Board;
import com.lhs.board_project.domain.User;
import com.lhs.board_project.dto.board.BoardCreateRequest;
import com.lhs.board_project.dto.board.BoardResponse;
import com.lhs.board_project.dto.board.BoardUpdateRequest;
import com.lhs.board_project.repository.BoardRepository;
import com.lhs.board_project.repository.elastic.BoardSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardSearchRepository boardSearchRepository;

    public void create(BoardCreateRequest boardCreateRequest, User user) {

        Board board = Board
                .builder()
                .title(boardCreateRequest.getTitle())
                .content(boardCreateRequest.getContent())
                .user(user).build();
        boardRepository.save(board);
    }

    public List<BoardResponse> findAll() {
        List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return boards
                .stream()
                .map(this::getBoardResponse)
                .toList();
    }

    public List<BoardResponse> findBySearchContent(String content) {
        List<BoardDoc> boards = boardSearchRepository.findByContentContaining(content);
        for (BoardDoc board : boards) {
            System.out.println(board.getCreated_at());
        }
        return boards.stream().map(this::getBoardDocResponse).collect(Collectors.toList());
    }

    public void update(Long id, BoardUpdateRequest boardUpdateRequest, User user) {
        Board board = getBoard(id);
        if (checkingUser(user,board)) {
            board.setTitle(boardUpdateRequest.getTitle());
            board.setContent(boardUpdateRequest.getContent());
        }
    }
    public void delete(Long id, User user) {
        Board board = getBoard(id);
        if (checkingUser(user, board)) {
            boardRepository.deleteById(id);
        }
    }

    private Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않음!!!"));
    }

    private boolean checkingUser(User user, Board board) {
        if (user.getEmail().equals(board.getUser().getEmail())) {
            return true;
        } else {
            throw new UsernameNotFoundException("해당 유저가 존재하지 않음 !!!!");
        }
    }

    public List<BoardResponse> getBoardsUserEmail(User user) {
        List<Board> boards = boardRepository.findAllByUserEmailOrderByIdDesc(user.getEmail());
        return boards
                .stream()
                .map(this::getBoardResponse)
                .toList();
    }
    public BoardResponse findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시판 id가 조회되지 않습니다."));
        return getBoardResponse(board);
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

    private BoardResponse getBoardDocResponse(BoardDoc board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getEmail(),
                board.getName(),
                board.getCreated_at());
    }



}

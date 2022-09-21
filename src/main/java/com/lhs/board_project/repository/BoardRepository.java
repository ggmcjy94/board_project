package com.lhs.board_project.repository;

import com.lhs.board_project.domain.Board;
import com.lhs.board_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByUserEmail(String email);

//    List<Board> findAllByUserEmailOrderById(String userEmail);

    List<Board> findAllByUserEmailOrderByIdDesc(String email);
    List<Board> findByContentContaining(String content);
}

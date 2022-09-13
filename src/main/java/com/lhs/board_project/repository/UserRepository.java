package com.lhs.board_project.repository;

import com.lhs.board_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

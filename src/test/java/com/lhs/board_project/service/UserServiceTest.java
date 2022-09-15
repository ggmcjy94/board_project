package com.lhs.board_project.service;

import com.lhs.board_project.config.jwt.JwtTokenProvider;
import com.lhs.board_project.config.jwt.dto.TokenDto;
import com.lhs.board_project.domain.User;
import com.lhs.board_project.dto.UserSignInRequest;
import com.lhs.board_project.dto.UserSignUpRequest;
import com.lhs.board_project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {
    private static final String EMAIL = "test@test";
    private static final String PASSWORD = "1234";
    private static final String NAME = "이현석";
    private static final String NICKNAME = "test";
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void test() {
        assertThat(userService).isNotNull();
    }

    @Test
    @DisplayName("signUp method test")
    void signUp() {
        //given
        //when
        UserSignUpRequest savedUser = userService.signUp(createUserSignUp());

        User FindUser = userRepository.findByEmail(savedUser.getEmail()).orElseThrow(null);

        //then
        assertThat(savedUser.getEmail()).isEqualTo(FindUser.getEmail());
        assertThat(savedUser.getNickName()).isEqualTo(FindUser.getNickName());
        assertThat(savedUser.getName()).isEqualTo(FindUser.getName());
        assertThat(savedUser.getPassword()).isNotEqualTo(FindUser.getPassword()); // 암호화
    }
    @Test
    @DisplayName("Sign IN method TEST")
    void signIn() {
        //given
        UserSignUpRequest savedUser = userService.signUp(createUserSignUp());
        //when
        ResponseEntity<TokenDto> tokenDtoResponseEntity = userService.signIn(createUserSignIn());
        boolean tokenCheck = jwtTokenProvider.validateToken(tokenDtoResponseEntity.getBody().getAccess_token());
        //then
        assertThat(tokenCheck).isTrue();
    }

    private UserSignInRequest createUserSignIn() {
        return UserSignInRequest.builder()
                .email(EMAIL)
                .password(PASSWORD).build();
    }

    private UserSignUpRequest createUserSignUp() {
        return UserSignUpRequest
                .builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickName(NICKNAME)
                .name(NAME)
                .build();
    }

}
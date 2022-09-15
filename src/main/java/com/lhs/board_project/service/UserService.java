package com.lhs.board_project.service;

import com.lhs.board_project.config.jwt.JwtTokenProvider;
import com.lhs.board_project.config.jwt.dto.TokenDto;
import com.lhs.board_project.config.jwt.ex.CustomException;
import com.lhs.board_project.domain.User;
import com.lhs.board_project.dto.UserSignInRequest;
import com.lhs.board_project.dto.UserSignUpRequest;
import com.lhs.board_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    public UserSignUpRequest signUp(UserSignUpRequest request) {
        User entity = request.toUserEntity();
        entity.hashPassword(bCryptPasswordEncoder);
        repository.save(entity);
        return request;
    }

    public ResponseEntity<TokenDto> signIn(UserSignInRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            TokenDto tokenDto = new TokenDto(jwtTokenProvider.generateToken(authenticate));

            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid credentials supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

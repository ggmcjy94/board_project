package com.lhs.board_project.config;

import com.lhs.board_project.domain.User;
import com.lhs.board_project.ex.UserNotFoundException;
import com.lhs.board_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}

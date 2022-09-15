package com.lhs.board_project.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User  extends AudutingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, unique = true, length = 50)
    private String nickName;
    private String password;
    private String name;
    @Builder
    public User(String email, String nickName, String password, String name) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
    }

    public User hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }


}


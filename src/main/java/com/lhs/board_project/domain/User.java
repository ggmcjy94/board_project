package com.lhs.board_project.domain;


import javax.persistence.*;

@Entity
@Table(name = "users")
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
}


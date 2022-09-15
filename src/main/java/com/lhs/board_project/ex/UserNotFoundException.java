package com.lhs.board_project.ex;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Can't find User");
    }
}

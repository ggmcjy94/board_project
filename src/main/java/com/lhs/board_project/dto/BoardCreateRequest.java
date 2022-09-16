package com.lhs.board_project.dto;


import com.lhs.board_project.domain.Board;
import com.lhs.board_project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequest {

    @NotEmpty(message = "Please enter your title")
    private String title;

    @NotEmpty(message = "Please enter your content")
    private String content;

}

package com.lhs.board_project.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateRequest {

    @NotEmpty(message = "Please enter your title")
    private String title;

    @NotEmpty(message = "Please enter your content")
    private String content;

}

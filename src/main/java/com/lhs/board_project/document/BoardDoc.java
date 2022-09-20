package com.lhs.board_project.document;

import com.lhs.board_project.domain.Board;
import com.lhs.board_project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Document(indexName = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Mapping(mappingPath = "elastic/board-mapping.json")
@Setting(settingPath = "elastic/board-setting.json")
public class BoardDoc {
    @Id
    private Long id;
    private String title;
    private String content;
    private User user;
    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    private LocalDateTime createdAt;
    public static BoardDoc from(Board b) {
        return BoardDoc.builder()
                .id(b.getId())
                .title(b.getTitle())
                .content(b.getContent())
                .user(b.getUser())
                .createdAt(b.getCreatedAt())
                .build();
    }
}

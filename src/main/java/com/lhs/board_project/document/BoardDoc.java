package com.lhs.board_project.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhs.board_project.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date_hour_minute_second_millis;
import static org.springframework.data.elasticsearch.annotations.DateFormat.epoch_millis;

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
    private String email;
    private String name;
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime created_at;

    public static BoardDoc from(Board b) {
        return BoardDoc.builder()
                .id(b.getId())
                .title(b.getTitle())
                .content(b.getContent())
                .email(b.getUser().getEmail())
                .name(b.getUser().getName())
                .created_at(b.getCreatedAt())
                .build();
    }
}

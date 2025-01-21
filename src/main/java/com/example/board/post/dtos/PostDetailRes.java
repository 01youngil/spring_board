package com.example.board.post.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDetailRes {
    private Long id;
    private String title;
    private String contents;
    private String authorEmail;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}

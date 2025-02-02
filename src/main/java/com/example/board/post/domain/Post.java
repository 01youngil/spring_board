package com.example.board.post.domain;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.dtos.PostDetailRes;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostUpdateReq;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@Entity
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    private String appointment;
    private LocalDateTime appointmentTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public PostListRes listFromEntity(){
        return PostListRes.builder().id(this.id).title(this.title)
                .authorEmail(this.author.getEmail()).build();
    }
    public PostDetailRes detailFromEntity(){
        return  PostDetailRes.builder().id(this.id).title(this.title).contents(this.contents)
                .createdTime(this.getCreatedTime())
                .updatedTime(this.getUpdateTime())
                .build();
    }

    public void updatePost(PostUpdateReq postUpdateReq){
        this.title = postUpdateReq.getTitle();
        this.contents = postUpdateReq.getContents();
    }

    public void updateAppointment(String appointment){
        this.appointment = appointment;
    }
}

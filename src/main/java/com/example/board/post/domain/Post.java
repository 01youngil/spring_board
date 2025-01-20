package com.example.board.post.domain;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Role;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.dtos.PostListRes;
import lombok.*;

import javax.persistence.*;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public PostListRes listFromEntity(){
        return PostListRes.builder().id(this.id).title(this.title)
                .authorEmail(this.author.getEmail()).build();
    }
}

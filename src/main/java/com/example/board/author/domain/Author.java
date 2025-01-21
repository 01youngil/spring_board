package com.example.board.author.domain;

import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.common.domain.BaseTimeEntity;
import com.example.board.post.domain.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
@AllArgsConstructor
@Builder
public class Author extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
//    enum은 기본적으로 숫자값으로 db에 들어감으로, 별도로 STRING지정 필요
    @Enumerated(EnumType.STRING)
    private Role role;
//    OneToMany의 기본값이 fetch lazy라 별도의 설정은 없음.
//    mappedBy에 ManyToOne쪽의 변수명을 문자열로 지정
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    빌더패턴에서 변수 초기화(디폴트값)시 Builder.Default 어노테이션 사용
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    public AuthorListRes listFromEntity(){
        return AuthorListRes.builder().id(this.id).name(this.name).email(this.email).build();
    }

    public AuthorDetailRes detailFromEntity(){
        return  AuthorDetailRes.builder().id(this.id).name(this.name).email(this.email)
                .password(this.password).role(this.role)
                .postCount(this.posts.size()).createdTime(this.getCreatedTime()).build();
    }

    public void updateNP(AuthorUpdateReq authorUpdateReq){
        this.name = authorUpdateReq.getName();
        this.password = authorUpdateReq.getPassword();
    }

}

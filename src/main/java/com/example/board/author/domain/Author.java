package com.example.board.author.domain;

import com.example.board.author.dtos.AuthorListRes;
import com.example.board.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

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

    public AuthorListRes listFromEntity(){
        return AuthorListRes.builder().id(this.id).name(this.name).email(this.email).build();
    }


}

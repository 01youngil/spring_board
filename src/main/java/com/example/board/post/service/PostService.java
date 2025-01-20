package com.example.board.post.service;

import com.example.board.author.domain.Author;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostSaveReq;
import com.example.board.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public List<PostListRes> findAll(){
        return postRepository.findAll().stream().map(m -> m.listFromEntity()).collect(Collectors.toList());
    }

    public void save(PostSaveReq postSaveReq) throws IllegalArgumentException{
        Author author = authorRepository.findByEmail(postSaveReq.getEmail()).orElseThrow(()-> new EntityNotFoundException("없는사용자입니다."));
        postRepository.save(postSaveReq.toEntity(author));
    }
}

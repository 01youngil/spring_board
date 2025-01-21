package com.example.board.post.service;

import com.example.board.author.domain.Author;
import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.author.repository.AuthorRepository;
import com.example.board.post.domain.Post;
import com.example.board.post.dtos.PostDetailRes;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostSaveReq;
import com.example.board.post.dtos.PostUpdateReq;
import com.example.board.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PostListRes> findAllPaging(Pageable pageable){
        Page<Post> pagePosts = postRepository.findAll(pageable);
        return pagePosts.map(p->p.listFromEntity());
    }

    public void save(PostSaveReq postSaveReq) throws IllegalArgumentException{
        Author author = authorRepository.findByEmail(postSaveReq.getEmail()).orElseThrow(()-> new EntityNotFoundException("없는사용자입니다."));
        postRepository.save(postSaveReq.toEntity(author));
    }

    public PostDetailRes findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("없는사용자입니다."))
                .detailFromEntity();
    }

    public void updatePost(Long id, PostUpdateReq postUpdateReq){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("없는 글입니다."));
        post.updatePost(postUpdateReq);
        postRepository.save(post); //해도되고 안해도됨

    }

    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("없는 글입니다."));
        postRepository.delete(post);
    }
}

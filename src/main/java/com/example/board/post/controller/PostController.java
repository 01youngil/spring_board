package com.example.board.post.controller;

import com.example.board.post.dtos.PostDetailRes;
import com.example.board.post.dtos.PostListRes;
import com.example.board.post.dtos.PostSaveReq;
import com.example.board.post.dtos.PostUpdateReq;
import com.example.board.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq postSaveReq){
        postService.save(postSaveReq);
        return "OK";
    }

    @GetMapping("/list")
    public List<PostListRes> postList(){
        return postService.findAll();
    }

    @GetMapping("/list/paging")
//    페이징처리를 위한 데이터형식 : localhost:8080/post/list/paging?page=0
    public Page<PostListRes> postListPaging
            (@PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){
        return postService.findAllPaging(pageable);
    }

    @GetMapping("/detail/{id}")
    public PostDetailRes postDetailRes(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, PostUpdateReq postUpdateReq){
        postService.updatePost(id, postUpdateReq);
        return "OK";
    }

    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.delete(id);

        return "OK";
    }
}

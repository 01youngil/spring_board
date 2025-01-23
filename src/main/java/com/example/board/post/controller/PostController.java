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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String postCreateScreen(){
        return "/post/post_create";
    }
    @PostMapping("/create")
    public String postCreate(@Valid PostSaveReq postSaveReq){
        postService.save(postSaveReq);
        return "redirect:/post/list/paging";
    }

    @GetMapping("/list")
    public String postList(Model model){
        model.addAttribute("postList",postService.findAll());
        return "/post/post_list";
    }

    @GetMapping("/list/paging")
//    페이징처리를 위한 데이터형식 : localhost:8080/post/list/paging?page=0
    public String postListPaging
            (Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("postList", postService.findAllPaging(pageable));
        return "post/post_list";
    }

    @GetMapping("/list/fetchjoin")
    @ResponseBody
    public String postListFetchJoin(){
        postService.listFetchJoin();
        return "ok";
    }

    @GetMapping("/detail/{id}")
    public String postDetailRes(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.findById(id));
        return "/post/post_detail";
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

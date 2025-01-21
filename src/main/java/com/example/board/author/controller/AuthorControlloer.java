package com.example.board.author.controller;

import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorControlloer {

    private final AuthorService authorService;
    public AuthorControlloer(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq authorSaveReq){
        authorService.save(authorSaveReq);
        return "OK";
    }

    @GetMapping("/list")
    public String authorList(){
        List<AuthorListRes> authorListResList = authorService.findAll();
        return "author/author_list";
    }

    @GetMapping("/detail/{id}")
    public AuthorDetailRes authorDetail(@PathVariable Long id){
        return authorService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.delete(id);
        return "OK";
    }

    @PostMapping("update/{id}")
    public String authorUpdate(@PathVariable Long id, AuthorUpdateReq authorUpdateReq){
        authorService.updateNP(id, authorUpdateReq);
        return "OK";
    }
}

package com.example.board.author.controller;

import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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
    public List<AuthorListRes> authorList(){
        return authorService.findAll();
    }
}

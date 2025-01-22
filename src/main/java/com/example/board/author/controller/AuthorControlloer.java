package com.example.board.author.controller;

import com.example.board.author.domain.Author;
import com.example.board.author.dtos.AuthorDetailRes;
import com.example.board.author.dtos.AuthorListRes;
import com.example.board.author.dtos.AuthorSaveReq;
import com.example.board.author.dtos.AuthorUpdateReq;
import com.example.board.author.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/create")
    public String authorCreateScreen(){
        return "/author/author_create";
    }
    @PostMapping("/create")
    public String authorCreate(@Valid AuthorSaveReq authorSaveReq){
        authorService.save(authorSaveReq);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String authorList(Model model){
        List<AuthorListRes> authorListResList = authorService.findAll();
        model.addAttribute("authorList", authorListResList);
        return "/author/author_list";
    }

    @GetMapping("/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model){
        model.addAttribute("author", authorService.findById(id));
        return "/author/author_detail";
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

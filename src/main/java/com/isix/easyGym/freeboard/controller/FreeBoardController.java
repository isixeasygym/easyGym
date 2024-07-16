package com.isix.easyGym.freeboard.controller;

import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import com.isix.easyGym.freeboard.service.FreeboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freeboard")
public class FreeBoardController {

    @Autowired
    private FreeboardService freeboardService;

    @GetMapping("/articles")
    public List<FreeboardArticleDTO> getAllArticles() {
        return freeboardService.getAllArticles();
    }

    @GetMapping("/articles/{freePostNo}")
    public FreeboardArticleDTO getArticleById(@PathVariable int freePostNo) {
        return freeboardService.getArticleById(freePostNo);
    }

    @PostMapping("/articles")
    public void createArticle(@RequestBody FreeboardArticleDTO article) {
        freeboardService.createArticle(article);
    }

    @PutMapping("/articles")
    public void updateArticle(@RequestBody FreeboardArticleDTO article) {
        freeboardService.updateArticle(article);
    }

    @DeleteMapping("/articles/{freePostNo}")
    public void deleteArticle(@PathVariable int freePostNo) {
        freeboardService.deleteArticle(freePostNo);
    }
}

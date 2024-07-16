package com.isix.easyGym.freeboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import com.isix.easyGym.freeboard.service.FreeboardService;

@RestController
@RequestMapping("/api/freeboard/articles")
public class FreeboardControllerImpl implements FreeBoardController {

    private final FreeboardService freeboardService;

    @Autowired
    public FreeboardControllerImpl(FreeboardService freeboardService) {
        this.freeboardService = freeboardService;
    }

    @Override
    @GetMapping
    public List<FreeboardArticleDTO> getAllArticles() {
        return freeboardService.getAllArticles();
    }

    @Override
    @GetMapping("/{id}")
    public FreeboardArticleDTO getArticleById(@PathVariable int id) {
        return freeboardService.getArticleById(id);
    }

    @Override
    @PostMapping
    public void createArticle(@RequestBody FreeboardArticleDTO article) {
        freeboardService.createArticle(article);
    }

    @Override
    @PutMapping("/{id}")
    public void updateArticle(@RequestBody FreeboardArticleDTO article) {
        freeboardService.updateArticle(article);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable int id) {
        freeboardService.deleteArticle(id);
    }
}

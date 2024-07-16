package com.isix.easyGym.freeboard.controller;

import java.util.List;

import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;


public interface FreeBoardController {
    List<FreeboardArticleDTO> getAllArticles();
    FreeboardArticleDTO getArticleById(int id);
    void createArticle(FreeboardArticleDTO article);
    void updateArticle(FreeboardArticleDTO article);
    void deleteArticle(int id);
    
}

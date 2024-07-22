package com.isix.easyGym.freeboard.dao;

import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import java.util.List;

public interface FreeboardDAO {
    List<FreeboardArticleDTO> getAllArticles();
    FreeboardArticleDTO getArticleById(int freePostNo);
    void createArticle(FreeboardArticleDTO article);
    void updateArticle(FreeboardArticleDTO article);
    void deleteArticle(int freePostNo);
}

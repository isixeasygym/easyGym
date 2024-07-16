package com.isix.easyGym.freeboard.dao;

import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FreeboardDAOImpl implements FreeboardDAO {

    private List<FreeboardArticleDTO> articles = new ArrayList<>();

    @Override
    public List<FreeboardArticleDTO> getAllArticles() {
        return articles;
    }

    @Override
    public FreeboardArticleDTO getArticleById(int freePostNo) {
        return articles.stream()
                .filter(article -> article.getFreePostNo() == freePostNo)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createArticle(FreeboardArticleDTO article) {
        articles.add(article);
    }

    @Override
    public void updateArticle(FreeboardArticleDTO article) {
        int index = articles.indexOf(getArticleById(article.getFreePostNo()));
        if (index >= 0) {
            articles.set(index, article);
        }
    }

    @Override
    public void deleteArticle(int freePostNo) {
        articles.removeIf(article -> article.getFreePostNo() == freePostNo);
    }
}

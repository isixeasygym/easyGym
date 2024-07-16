package com.isix.easyGym.freeboard.service;

import com.isix.easyGym.freeboard.dao.FreeboardDAO;
import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeBoardServiceImpl implements FreeboardService {

    @Autowired
    private FreeboardDAO freeBoardDAO;

    @Override
    public List<FreeboardArticleDTO> getAllArticles() {
        return freeBoardDAO.getAllArticles();
    }

    
    @Override
    public FreeboardArticleDTO getArticleById(int freePostNo) {
        return freeBoardDAO.getArticleById(freePostNo);
    }

    @Override
    public void createArticle(FreeboardArticleDTO article) {
        freeBoardDAO.createArticle(article);
    }

    @Override
    public void updateArticle(FreeboardArticleDTO article) {
        freeBoardDAO.updateArticle(article);
    }

    @Override
    public void deleteArticle(int freePostNo) {
        freeBoardDAO.deleteArticle(freePostNo);
    }
}

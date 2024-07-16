package com.isix.easyGym.freeboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isix.easyGym.freeboard.dao.FreeBoardDAO;
import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import com.isix.easyGym.freeboard.dto.FreeboardImageDTO;

@Service("freeboardService")
public class FreeBoardServiceImpl {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FreeBoardDAO fbDAO;

    public List<FreeboardArticleDTO> listArticles() {
        List<FreeBoardDTO> articles = fbDAO.selectAllArticles();
        for (FreeboardArticleDTO article : articles) {
            List<FreeboardImageDTO> images = fbDAO.selectImageFileList(article.getArticleNo());
            article.setImageFileList(images);
        }
        return articles;
    }

    @Transactional
    public void addArticle(FreeboardArticleDTO article) {
    	fbDAO.insertNewArticle(article);
        if (article.getImageFileList() != null) {
            for (FreeboardImageDTO image : article.getImageFileList()) {
                image.setArticleNo(article.getArticleNo());
                fbDAO.insertNewImages(image);
            }
        }
    }

    public FreeBoardDTO viewArticle(int articleNo) {
        FreeBoardDTO article = fbDAO.selectArticle(articleNo);
        List<fbImageDTO> images = fbDAO.selectImageFileList(articleNo);
        article.setImageFileList(images);
        return article;
    }
}

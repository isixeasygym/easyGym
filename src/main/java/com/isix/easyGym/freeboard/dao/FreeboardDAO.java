package com.isix.easyGym.freeboard.dao;

import java.util.List;

@Mapper
@Repository("freeboardDAO")
public interface FreeBoardDAO {

    public List<FreeBoardDTO> selectAllArticles() throws DataAccessException;

    public void insertNewArticle(FreeBoardDTO article) throws DataAccessException;

    public void insertNewImages(fbImageDTO image) throws DataAccessException;

    public FreeBoardDTO selectArticle(@Param("articleNo") int articleNo) throws DataAccessException;

    public List<fbImageDTO> selectImageFileList(@Param("articleNo") int articleNo) throws DataAccessException;
}


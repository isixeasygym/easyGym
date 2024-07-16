package com.isix.easyGym.freeboard.service;

import java.util.Map;

public interface FreeboardService {

public Map listArticles(Map<String, Integer> pagingMap) throws DataAccessException;
	
	//한 개의 이미지 추가
	//public int addArticle(ArticleDTO articleDTO) throws DataAccessException;  //다른데는 void인데 여기는 int? => 이미지 넣는 것 때문에
	
	//여러 개의 이미지 추가
	public int addArticle(Map aticleMap) throws DataAccessException;
	
	//한 개의 이미지 상세 글보기
	//public ArticleDTO viewArticle(int articleNo) throws DataAccessException;
	
	//여러 개의 이미지 상세 글보기
	public Map viewArticle(int articleNo) throws DataAccessException;
	
	//한 개의 이미지 글 수정하기
	//public void modArticle(ArticleDTO articleDTO) throws DataAccessException;  //글 수정. 수정한걸 반영해야해서 ArticleDTO
	
	//여러 개의 이미지 글 수정하기
	public void modArticle(Map articleMap) throws DataAccessException;
	
	public void removeArticle(int articleNo) throws DataAccessException;
	
}

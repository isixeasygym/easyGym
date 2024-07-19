package com.isix.easyGym.freeboard.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface FreeBoardService {
	public Map listFboard(Map<String,Integer> pagingMap) throws DataAccessException;
	
	//public int addArticle(ArticleDTO dto) throws DataAccessException; 한개의 이미지 추가
	
	// 여러개 이미지 추가
	public int addFboard(Map<String,Object> map ) throws DataAccessException;

	// 한개의 이미지 상세보기
	//public ArticleDTO viewArticle(int ArticleNo) throws DataAccessException;
	// 여러개의 이미지 상세보기
	public Map viewFboard(int freeNo) throws DataAccessException;
	
	// 한개의 이미지 수정
	//public void modArticle(ArticleDTO dto) throws DataAccessException;
	// 여러개 이미지 수정
	public void modFboard(Map map) throws DataAccessException;
	
	public void removeFboard(int freeNo) throws DataAccessException;
	
	// 조회수 카운트
	public void readCount(int freeNo) throws DataAccessException;
}


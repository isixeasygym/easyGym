package com.isix.easyGym.detail.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;

public interface DetailService {
	
	
	public List findThing(Map searchMap) throws DataAccessException;
	
	public List findAll(String detailClassification) throws DataAccessException;
	
	public int popularThing(int detailNum) throws DataAccessException;
	
	public List findPopular(int popularRating) throws DataAccessException;
	
	public int findDetailNo(String detailClassification) throws DataAccessException; 
	
	public DetailDTO viewDetail(int detailNo) throws DataAccessException;

	public DetailDibsDTO findDibs(Map paramMap) throws DataAccessException;
	
	public void noImgReview(Map noImgReviewMap) throws DataAccessException;
	
	public void writeReview(Map reviewMap) throws DataAccessException;
	
	public void removeReview(int reviewNoww3) throws DataAccessException;
	
	public List findReview(int detailNo) throws DataAccessException;
	
	public void addOperForm(Map detailMap) throws DataAccessException;
}

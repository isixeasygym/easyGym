package com.isix.easyGym.detail.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;

public interface DetailService {
	public List selectAll(String WholeClassification) throws DataAccessException;
	
	public List selectPopular(Map selectThing)  throws DataAccessException;
	
	public DetailDTO viewDetail(int wholeNo) throws DataAccessException;

	public DetailDibsDTO findDibs(Map paramMap) throws DataAccessException;
	
	public void writeReview(Map review) throws DataAccessException;
	
	public void deleteReview(Map review) throws DataAccessException;
}

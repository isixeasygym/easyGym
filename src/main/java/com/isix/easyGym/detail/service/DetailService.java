package com.isix.easyGym.detail.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;

public interface DetailService {
	
	//public String findImage(int reviewNo) throws DataAccessException;
	
	public List<DetailReviewDTO> getReviewImages(int detailNo) throws DataAccessException;
	
	public List<DetailReviewDTO> getReviews(int detailNo) throws DataAccessException;

	public Map listReview(Map<String,Integer> pagingMap) throws DataAccessException;

	public List<DetailReviewDTO> findReviewImage(int detailNo) throws DataAccessException;
	
	public List<DetailDTO> findThing(Map searchMap) throws DataAccessException;

    public List<DetailDTO> findPLace(Map searchMap) throws DataAccessException;
	
	public int popularThing(int detailNum) throws DataAccessException;
	
	public DetailDTO findBussinessName(String detailBusinessName) throws DataAccessException;
	
	public int findDetailNo(String detailClassification) throws DataAccessException; 
	
	public DetailDTO viewDetail(int detailNo) throws DataAccessException;

	public DetailDibsDTO findDibs(Map paramMap) throws DataAccessException;
	
	public void noImgReview(Map noImgReviewMap) throws DataAccessException;
	
	public void removeReview(int reviewNoww3) throws DataAccessException;
	
	public List findReview(int detailNo) throws DataAccessException;

	public int findOperatorNo(int detailNo) throws DataAccessException;

	public void addOperForm(Map detailMap) throws DataAccessException;
	
	public int addreview(Map reviewImageMap) throws DataAccessException;
	
	public List<DetailDTO> findPopularHealth() throws DataAccessException;

	public List<DetailDTO> findPopularBoxing() throws DataAccessException;
	
	public List<DetailDTO> findPopularPilates() throws DataAccessException;

	public void addReport(Map<String, Object> reportMap) throws DataAccessException;

	public int findReportCount(int detailNo) throws DataAccessException;

	public int findReport(int memberNo) throws DataAccessException;
}

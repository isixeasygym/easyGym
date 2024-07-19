package com.isix.easyGym.detail.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
@Mapper
@Repository("detailDAO")
public interface DetailDAO {
	 
	public List selectAll(String detailClassification) throws DataAccessException;
	
	public List selectPopular(int popularRating) throws DataAccessException;
	
	public int selectDetailNo(String detailClassification) throws DataAccessException;
	
	public int selectPopularRating(int detailNum) throws DataAccessException;
	
	public DetailDibsDTO findDibs(Map ParamMap) throws DataAccessException;
	
	public void insertDibs(Map paramMap) throws DataAccessException;
	
	public void removeDibs(Map paramMap) throws DataAccessException;
	
	public DetailDTO selectBusiness(int detailNo) throws DataAccessException; 
	
	public void insertReview(Map reviewMap) throws DataAccessException;
	
	public void insertNoImgReview(Map noImgReviewMap) throws DataAccessException;
	
	public void removeReview(Map review) throws DataAccessException;
	
	public List selectReviewNo(int detailNo) throws DataAccessException;
	
	public List selectReview(int detailNo) throws DataAccessException;
}

package com.isix.easyGym.detail.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;
@Mapper
@Repository("detailDAO")
public interface DetailDAO {
	
  
	public List<DetailDTO> selectPopularHealth() throws DataAccessException;
	
	public List<DetailDTO> selectPopularBoxing() throws DataAccessException;
	
	public List<DetailDTO> selectPopularPilates() throws DataAccessException;
	
	public List<DetailReviewDTO> selectReviewImage(int detailNo) throws DataAccessException;
	
	public List<DetailDTO> selectQuery(Map searchMap) throws DataAccessException;

    public List<DetailDTO> selectPLaceQuery(Map searchMap) throws DataAccessException;

	public int getNewDetailNo() throws DataAccessException;

	public int getNewReviewNo() throws DataAccessException;
	
	public List selectAll(String detailClassification) throws DataAccessException;
	
	public List selectPopular(int popularRating) throws DataAccessException;
	
	public int selectDetailNo(String detailClassification) throws DataAccessException;
	
	public int selectPopularRating(int detailNum) throws DataAccessException;
	
	public List<DetailReviewDTO> selectReview(int detailNo) throws DataAccessException;
	
	public DetailDibsDTO selectDibs(Map ParamMap) throws DataAccessException;
	
	public void insertDibs(Map paramMap) throws DataAccessException;
	
	public DetailDTO selectBusiness(int detailNo) throws DataAccessException; 
	
	public void insertReviewAndImage(Map reviewImageMap) throws DataAccessException;
	
	//리뷰 이미지 없이 테이블 삽입
	public void insertNoImgReview(Map noImgReviewMap) throws DataAccessException;
	//업체 테이블 등록
	public void insertOperForm(Map detailMap) throws DataAccessException;
	//업체 이미지 리스트
	public void insertNewImages(Map detailMap) throws DataAccessException;
	
	public void deleteReview(int reviewNo) throws DataAccessException;
	
	public void removeDibs(Map paramMap) throws DataAccessException;
	
}

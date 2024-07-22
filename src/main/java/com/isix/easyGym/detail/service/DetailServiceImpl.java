package com.isix.easyGym.detail.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;

@Service("detailServicle")
public class DetailServiceImpl implements DetailService{

	@Autowired
	private DetailDAO detailDAO;
	
	@Autowired
	private DetailDTO detailDTO;
	
	@Autowired
	private DetailDibsDTO detailDibsDTO;
	
	
	@Override
	public List findAll(String detailClassification) throws DataAccessException {
		List selectAllList=detailDAO.selectAll(detailClassification);
		return selectAllList;
	}
	

	
	@Override
	public int popularThing(int detailNum) throws DataAccessException {
		int popularRating = detailDAO.selectPopularRating(detailNum);
		return popularRating;
	}

	@Override
	public List findPopular(int popularRating) throws DataAccessException {
		List PopularThing= detailDAO.selectPopular(popularRating);
		return null;
	}
	
	@Override
	public int findDetailNo(String detailClassification) throws DataAccessException {
		int detailNo = detailDAO.selectDetailNo(detailClassification);
		return detailNo;
	}
	
	@Override
	public DetailDTO viewDetail(int detailNo) throws DataAccessException {
		detailDTO=detailDAO.selectBusiness(detailNo);
		return detailDTO;
	}

	

	@Override
	public DetailDibsDTO findDibs(Map paramMap) throws DataAccessException {
		detailDibsDTO=detailDAO.findDibs(paramMap);
		return detailDibsDTO;
	}

	@Override
	public void noImgReview(Map noImgReviewMap) throws DataAccessException {
		detailDAO.insertNoImgReview(noImgReviewMap);
	}

	@Override
	public void writeReview(Map review) throws DataAccessException {
		detailDAO.insertReview(review);
	}



	@Override
	public void removeReview(int reviewNo) throws DataAccessException {
		detailDAO.deleteReview(reviewNo);
	}

	@Override
	public List findReview(int detailNo) throws DataAccessException {
		List reivew = detailDAO.selectReview(detailNo);
		return reivew;
	}

}

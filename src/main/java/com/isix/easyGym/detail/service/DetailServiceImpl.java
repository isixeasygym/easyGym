package com.isix.easyGym.detail.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;

@Service("detailServicle")
public class DetailServiceImpl implements DetailService{

	@Autowired
	private DetailDAO detailDAO;
	
	@Autowired
	private DetailDTO detailDTO;
	
	@Autowired
	private DetailDibsDTO detailDibsDTO;
	
	@Override
	public List<DetailDTO> findThing(Map searchMap) throws DataAccessException {
		List<DetailDTO> searchedThing = detailDAO.selectQuery(searchMap);
		return searchedThing;
	}
	
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
		detailDibsDTO=detailDAO.selectDibs(paramMap);
		return detailDibsDTO;
	}

	@Override
	public void noImgReview(Map noImgReviewMap) throws DataAccessException {
		detailDAO.insertNoImgReview(noImgReviewMap);
	}

	



	@Override
	public void removeReview(int reviewNo) throws DataAccessException {
		detailDAO.deleteReview(reviewNo);
	}

	@Override
	public List<DetailReviewDTO> findReview(int detailNo) throws DataAccessException {
		List<DetailReviewDTO> reivew = detailDAO.selectReview(detailNo);
		return reivew;
	}



	@Override
	public void addOperForm(Map detailMap) throws DataAccessException {
		int detailNo=detailDAO.getNewDetailNo();
		detailMap.put("detailNo", detailNo);
		detailDAO.insertOperForm(detailMap);
		if(detailMap.get("imageFileList")!=null) {
			detailDAO.insertNewImages(detailMap);		}
		
	}

	@Override
	public int addreview(Map reviewImageMap) throws DataAccessException {
		int reviewNo=detailDAO.getNewReviewNo();
		reviewImageMap.put("reviewNo", reviewNo);
		detailDAO.insertReviewAndImage(reviewImageMap);
		return reviewNo;
	}

	@Override
	public List<DetailReviewDTO> getReviews(int detailNo) throws DataAccessException {
		List<DetailReviewDTO> reviews = detailDAO.selectReview(detailNo); 
		return reviews;
	}

	@Override
	public List<DetailReviewDTO> findReviewImage(int detailNo) throws DataAccessException {
		List<DetailReviewDTO> reviewImage=detailDAO.selectReviewImage(detailNo);
		return reviewImage;
	}

	@Override
	public List<DetailDTO> findPopularHealth() throws DataAccessException {
		List<DetailDTO> healthList = detailDAO.selectPopularHealth();
		return healthList;
	}

	@Override
	public List<DetailDTO> findPopularBoxing() throws DataAccessException {
		List<DetailDTO> boxingList = detailDAO.selectPopularBoxing();
		return boxingList;
	}

	@Override
	public List<DetailDTO> findPopularPilates() throws DataAccessException {
		List<DetailDTO> pilatesList = detailDAO.selectPopularPilates();
		return pilatesList;
	}

	

}

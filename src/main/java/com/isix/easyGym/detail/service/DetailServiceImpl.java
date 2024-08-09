package com.isix.easyGym.detail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;

@Service("detailService")
public class DetailServiceImpl implements DetailService {

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
    public List<DetailDTO> findPLace(Map searchMap) throws DataAccessException {
        List<DetailDTO> searchedPlace = detailDAO.selectPLaceQuery(searchMap);
        return searchedPlace;
    }
	
	@Override
	public int popularThing(int detailNum) throws DataAccessException {
		int popularRating = detailDAO.selectPopularRating(detailNum);
		return popularRating;
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
	//상세 페이지에 사용
	@Override
	public List<DetailReviewDTO> findReview(int detailNo) throws DataAccessException {
		List<DetailReviewDTO> reivew = detailDAO.selectReview(detailNo);
		return reivew;
	} 

	@Override
	public int findOperatorNo(int detailNo) throws DataAccessException {
		int operatorNo =detailDAO.selectOperatorNo(detailNo);
		return operatorNo;
	}
	public int findReportCount(Map<String, Object> countMap) throws DataAccessException{
		int reportCount = detailDAO.selectReportCount(countMap);
		return reportCount;
	}

	@Override
	public int findReport(Map<String, Object> selectMap) throws DataAccessException {
		int report = detailDAO.selectReport(selectMap);
		return report;
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
	public Map listReview(Map<String,Integer> pagingMap) throws DataAccessException{
		Map reviewMap = new HashMap<>();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		int tReview = detailDAO.selectToReview(); // 토탈 게시글
		reviewMap.put("tReview", 150); // 페이징 임시 처리
		//fbmap.put("tFreeboard", tFreeboard);
		return reviewMap;
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

	@Override
	public void addReport(Map<String, Object> reportMap) throws DataAccessException {
		detailDAO.insertReport(reportMap);
	}

	public List<DetailReviewDTO> getReviewImages(int detailNo) {
		List<DetailReviewDTO> reviewImage = detailDAO.selectReviewImage(detailNo);
		return reviewImage;
	}



	@Override
	public DetailDTO findBussinessName(String detailBusinessName) throws DataAccessException {
		DetailDTO searchBusinessName = detailDAO.selectBusinessName(detailBusinessName);
		return searchBusinessName;
	}

	@Override
	public DetailReviewDTO getReviewByNo(int reviewNo) throws DataAccessException {
		DetailReviewDTO reviewDTO = detailDAO.selectReviewDTO(reviewNo);
		return reviewDTO;
	}

	@Override
	public int findReviewMember(Map selectMap) throws DataAccessException {
		int selectMember = detailDAO.selectMember(selectMap);
		return selectMember;
	}



	
	/*@Override
	public String findImage(int reviewNo) throws DataAccessException {
		String image=detailDAO.selectImage(reviewNo);
		return image;
	}*/

}
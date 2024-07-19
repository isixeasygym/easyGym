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
	public List selectPopular(Map selectThing) throws DataAccessException {
		List selectList=detailDAO.findPopular(selectThing);
		return selectList;
	}



	@Override
	public List selectAll(String WholeClassification) throws DataAccessException {
		List selectAllList=detailDAO.findAll(WholeClassification);
		return selectAllList;
	}
	
	
	
	@Override
	public DetailDTO viewDetail(int wholeNo) throws DataAccessException {
		detailDTO=detailDAO.selectBusiness(wholeNo);
		return detailDTO;
	}

	

	@Override
	public DetailDibsDTO findDibs(Map paramMap) throws DataAccessException {
		detailDibsDTO=detailDAO.findDibs(paramMap);
		return detailDibsDTO;
	}



	@Override
	public void writeReview(Map review) throws DataAccessException {
		detailDAO.insertReview(review);
	}



	@Override
	public void deleteReview(Map review) throws DataAccessException {
		detailDAO.removeReview(review);
	}
}

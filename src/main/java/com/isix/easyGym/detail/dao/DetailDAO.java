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
	 
	public List findPopular(Map popular) throws DataAccessException;
	
	public List findAll(String detailClassification) throws DataAccessException;
	
	public DetailDibsDTO findDibs(Map ParamMap) throws DataAccessException;
	
	public void insertDibs(Map paramMap) throws DataAccessException;
	
	public void removeDibs(Map paramMap) throws DataAccessException;
	
	public DetailDTO selectBusiness(int detailNo) throws DataAccessException; 
	
	public DetailDTO selectMember(int detailNo) throws DataAccessException;
}

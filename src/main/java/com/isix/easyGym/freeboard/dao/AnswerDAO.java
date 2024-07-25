package com.isix.easyGym.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Mapper
@Repository("AnswerDAO")
public interface AnswerDAO {
	
	public List selectAnswer() throws DataAccessException;

	public int getAnswerNo() throws DataAccessException; 
	
	public void insertNewAnswer(Map amap) throws DataAccessException;
	
	public void insertAnswerImages(Map amap) throws DataAccessException;

	public void updateAnswer(Map amap) throws DataAccessException;
	
	public void updateAnswerImage(Map amap) throws DataAccessException;
	
	public void deleteAnswer(int fbanswerNo) throws DataAccessException;
		
}

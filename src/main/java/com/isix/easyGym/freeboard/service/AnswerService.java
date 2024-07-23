package com.isix.easyGym.freeboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.freeboard.dto.AnswerDTO;

public interface AnswerService {
	
		public Map answerList(Map<String,Object> pagingMap) throws DataAccessException;

		public int addAnswer(Map<String,Object> map ) throws DataAccessException;
		
		public void removeAnswer(int freeNo) throws DataAccessException;
		
		public AnswerDTO getAnswerByNo(int fbanswerNo) throws Exception;
}

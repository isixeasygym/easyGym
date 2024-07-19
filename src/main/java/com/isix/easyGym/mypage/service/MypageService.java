package com.isix.easyGym.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface MypageService {

	//1-2)찜 목록
	public Map detailDibsList(Map<String, Integer> pagingMap) throws DataAccessException;
	
	
	
}

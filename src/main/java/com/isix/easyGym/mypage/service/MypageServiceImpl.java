package com.isix.easyGym.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.mypage.dao.MypageDAO;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MypageDAO mypageDAO;
	
	//1-2)찜 목록
	public Map detailDibsList(Map<String, Integer> pagingMap) throws DataAccessException {
		Map dibsMap=new HashMap<>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		int count=(section-1)*100+(pageNum-1)*10;  //페이징 => 1페이지당 10개글, 1섹션당 10페이지, 한화면에 10페이지 100개글로 보이기
		List<DetailDTO> detailList=mypageDAO.selectAllDetail(count);  //selectAllArticles => board.xml의 id
		int totDibs=mypageDAO.selectToDibs();
		dibsMap.put("detailList", detailList);
		dibsMap.put("totDibs", totDibs);
		return dibsMap;
	}
		
		
}

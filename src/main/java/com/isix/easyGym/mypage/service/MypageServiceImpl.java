package com.isix.easyGym.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.mypage.dao.MypageDAO;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MypageDAO mypageDAO;
	

	//구매내역
	
	
	//문의내역
	
	
	//신고내역
	@Override
	public List reportHistorys() {
		List reportsList=mypageDAO.selectAllreports();
		return reportsList;
	}

	
	
}

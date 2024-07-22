package com.isix.easyGym.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.member.dto.MemberDTO;

public interface MypageService {

	//1-2)찜 목록
	//public Map detailDibsList(Map<String, Integer> pagingMap) throws DataAccessException;
	public List<DetailDTO> detailDibsList(int memberNo) throws DataAccessException;
	
	//2-1)포인트
	public List<MemberDTO> getPointsByMemberNo(int memberNo) throws DataAccessException;
	
	//2-2)쿠폰
    public List<MemberDTO> getCouponsByMemberNo(int memberNo) throws DataAccessException;

    //3-2)회원정보 수정
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;
	
	
	
}

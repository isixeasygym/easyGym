package com.isix.easyGym.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.member.dto.MemberDTO;

public interface MypageService {

	//1.내 정보
    //찜 목록
    public List<DetailDTO> detailDibsList(int memberNo) throws DataAccessException;

    //이용중인 상품 목록 가져오기
    public List getPayformNo(int memberNo) throws DataAccessException;
    
    //찜 취소
    public void removeDibs(int memberNo, int detailNo) throws DataAccessException;

    
    //2.내역조회
    //구매내역
	public List getPurchase(int memberNo) throws DataAccessException;
	//신고내역
	
	//리뷰내역
	public List getReview(int memberNo) throws DataAccessException;
    
    
    //3.정보수정
    //비밀번호 체크
    public boolean checkPassword(int memberNo, String memberPwd) throws DataAccessException;

    //회원정보 수정
    public void memberUpdate(MemberDTO memberDTO) throws DataAccessException;

    //회원탈퇴
    public void delMember(int memberNo);



}

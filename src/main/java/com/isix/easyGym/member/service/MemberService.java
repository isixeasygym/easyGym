package com.isix.easyGym.member.service;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.member.dto.KakaoDTO;
import com.isix.easyGym.member.dto.MemberDTO;

public interface MemberService {
	
	public void addMember(MemberDTO memberDTO) throws DataAccessException;
	
	public MemberDTO findMember(String id) throws DataAccessException;
	
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;
	
	public void delMember(String id) throws DataAccessException;
	
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException;

//	public String checkId(String memberId) throws DataAccessException;
	
	//detail dibs 관련 메서드이니 지우지 말아 주세요.
	public int loginCheck(int memberNo) throws DataAccessException;
	//detail 댓글 관련 메스드이니 지우지 말아 주세요.
	public int findMemberNo(int memberNo) throws DataAccessException;
	
	public boolean selectId(String memberId) throws DataAccessException;
	
	public String getKakaoAccessToken(String code) throws Exception;
	public KakaoDTO getKakaoUserInfo(String accessToken) throws Exception;
	public void kakaoLogin(KakaoDTO kakaoDTO) throws Exception;
	public void kakaoLogout(String user_id) throws Exception;
}
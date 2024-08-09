package com.isix.easyGym.mypage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.member.dto.MemberDTO;

@Mapper
@Repository("mypageDAO")
public interface MypageDAO {

	//1.내 정보
	//찜 목록
	public List<DetailDTO> selectAllDetail(@Param("memberNo") int memberNo) throws DataAccessException;
	
	//이용중인 상품 목록 가져오기
	public List selectPayformNo(@Param("memberNo") int memberNo) throws DataAccessException;
	
	//찜 취소
	public void deleteDibs(@Param("memberNo") int memberNo, @Param("detailNo") int detailNo) throws DataAccessException;

	
	//2.내역조회
	//구매내역
	public List selectPurchase(@Param("memberNo") int memberNo) throws DataAccessException;
	
	//리뷰내역
	public List selectReview(@Param("memberNo") int memberNo) throws DataAccessException;
	
	//신고내역
	public List selectReport(@Param("memberNo") int memberNo) throws DataAccessException;
	
	
	//3.정보수정
	//비밀번호 체크
	public String getPasswordByMemberNo(int memberNo) throws DataAccessException;
	
	//회원정보 수정
	public void memberUpdate(MemberDTO memberDTO) throws DataAccessException;
	
	//회원 탈퇴
	public void memberDelete(int memberNo) throws DataAccessException;
	
	
}

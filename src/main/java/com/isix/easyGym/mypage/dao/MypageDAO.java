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

	//1-2)찜 목록
/*	public List<DetailDTO> selectAllDetail(@Param("count") int count) throws DataAccessException;
	public int selectToDibs() throws DataAccessException;
	public int getNewDibsNo() throws DataAccessException; */
	//public List<DetailDibsDTO> selectAllDetail(int memberNo) throws DataAccessException;
	public List<DetailDTO> selectAllDetail(@Param("memberNo") int memberNo) throws DataAccessException;
	
	//2-1)포인트
	public List<MemberDTO> selectPointsByMemberNo(@Param("memberNo") int memberNo) throws DataAccessException;

	//2-2)쿠폰
	public List<MemberDTO> selectCouponsByMemberNo(@Param("memberNo") int memberNo) throws DataAccessException;

	//3-2)회원정보 수정
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;
	
}

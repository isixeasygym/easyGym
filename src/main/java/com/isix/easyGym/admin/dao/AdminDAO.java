package com.isix.easyGym.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.admin.dto.AdminDTO;

@Mapper // 매퍼어노테이션을 선언하면 추상메소드의 이름과 같은 매퍼의 id를 읽는다.
@Repository("adminDAO")
public interface AdminDAO {
	
	//관리자 가입
	public void joinAd(AdminDTO adminDTO) throws DataAccessException;
	
	// 관리자 로그인 체크
	public AdminDTO loginCheck(AdminDTO adminDTO) throws DataAccessException;
	
	// 회원 리스트
	public List selectAll() throws DataAccessException; // mapper의 전체리스트 검색 id를 메소드명으로 지정 
	
	// 탈퇴 회원 리스트
	public List selectWithMem() throws DataAccessException;
	
	// 사업자 리스트
	public List selectOper() throws DataAccessException;
	
	// 탈퇴 사업자 리스트
	public List selectCompany() throws DataAccessException;

	// 신고 리스트
	public List selectReport() throws DataAccessException;
	
	}

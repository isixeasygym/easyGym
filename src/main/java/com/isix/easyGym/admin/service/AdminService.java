package com.isix.easyGym.admin.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.admin.dto.AdminDTO;

public interface AdminService {

	// 관리자 추가
	public void addAdmin(AdminDTO dto) throws DataAccessException;
	
	// 관리자 로그인 체크
	public AdminDTO login(AdminDTO dto) throws DataAccessException;
	
	// 회원 리스트
	public List memberList() throws DataAccessException;

	// 회원 탈퇴 리스트
	public List withdrawMember() throws DataAccessException;
	

}

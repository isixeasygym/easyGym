package com.isix.easyGym.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.member.dto.MemberDTO;

public interface MemberService {

	public List listMembers() throws DataAccessException;

	public void addMember(MemberDTO memberDTO) throws DataAccessException;
	
	public MemberDTO findMember(String id) throws DataAccessException;
	
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;
	
	public void delMember(String id) throws DataAccessException;
	
	public MemberDTO loginCheck(int userId) throws DataAccessException;
	
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException;
}

package com.isix.easyGym.member.service;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.member.dto.MemberOperDTO;

public interface MemberOperService {
	
	public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException;
		
	public void updateOperator(MemberOperDTO memberOperDTO) throws DataAccessException;
	
	public void delOperator(String id) throws DataAccessException;
	
	public MemberOperDTO login(MemberOperDTO member) throws DataAccessException;
	

}

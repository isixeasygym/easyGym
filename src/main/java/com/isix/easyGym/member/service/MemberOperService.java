package com.isix.easyGym.member.service;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.member.dto.MemberOperDTO;

public interface MemberOperService {

	

	public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException;
			
	public void delOperator(String operatorId) throws DataAccessException;
	
	public void updateOperator(MemberOperDTO memberOperDTO) throws DataAccessException;
	
	public MemberOperDTO operLogin(MemberOperDTO memberDTO) throws DataAccessException;

	public boolean checkId(String memberOperId) throws DataAccessException;
	
	public boolean selectId(String operatorId) throws DataAccessException;

}

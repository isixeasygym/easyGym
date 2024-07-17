package com.isix.easyGym.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dao.MemberOperDAO;
import com.isix.easyGym.member.dto.MemberOperDTO;

@Service("memberOperService")
public class MemberOperServiceImpl implements MemberOperService {

	@SuppressWarnings("SpringJavaInjectionPointAutowiringInspection")
	@Autowired
	private MemberOperDTO memberOperDTO;
	private MemberOperDAO memberOperDAO;
	
//	public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException{
//		memberOperDAO.insertOperator(memberOperDTO);
//	}
//	public void updateOperator(MemberOperDTO memberOperDTO) throws DataAccessException;
//	
//	public void delOperator(String id) throws DataAccessException;
//	
//	public MemberOperDTO login(MemberOperDTO member) throws DataAccessException;
}

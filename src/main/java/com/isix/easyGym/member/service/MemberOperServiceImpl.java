package com.isix.easyGym.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.isix.easyGym.member.dao.MemberOperDAO;
import com.isix.easyGym.member.dto.MemberOperDTO;

@Service("memberOperService") // 아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class MemberOperServiceImpl implements MemberOperService {

	@Autowired
	private MemberOperDAO memberOperDAO;

	public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException {
		memberOperDAO.insertMember(memberOperDTO);
	}

	public void updateOperator(MemberOperDTO memberOperDTO) throws DataAccessException {
		memberOperDAO.updateMember(memberOperDTO);
	}

	public void delOperator(String operatorId) throws DataAccessException {
		memberOperDAO.delMember(operatorId);
	}

	public MemberOperDTO login(MemberOperDTO operator) throws DataAccessException {
		return memberOperDAO.login(operator);
	}

	public boolean checkId(String operatorId) throws DataAccessException {
		return memberOperDAO.checkId(operatorId);

	}


}
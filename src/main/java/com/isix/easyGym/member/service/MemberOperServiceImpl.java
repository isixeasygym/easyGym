package com.isix.easyGym.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dao.MemberOperDAO;
import com.isix.easyGym.member.dto.MemberOperDTO;

@Service("memberOperService") // 아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class MemberOperServiceImpl implements MemberOperService {

	@Autowired
	private MemberDAO memberDAO;

	public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException {
		memberDAO.insertOperator(memberOperDTO);
	}

	public void updateOperator(MemberOperDTO memberOperDTO) throws DataAccessException {
		memberDAO.updateOperator(memberOperDTO);
	}

	public void delOperator(String operatorId) throws DataAccessException {
		memberDAO.delMember(operatorId);
	}

	public MemberOperDTO operLogin(MemberOperDTO operator) throws DataAccessException {
		return memberDAO.operLogin(operator);
	}
	@Override
	public boolean selectId(String operatorId) throws DataAccessException {
		return memberDAO.selectId(operatorId);
	}

	@Override
	public boolean checkId(String memberOperId) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}




	


}
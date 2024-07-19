package com.isix.easyGym.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.dto.MemberOperDTO;

@Service("memberService") // 아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberOperDAO memberOperDAO;

	public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException {
		memberOperDAO.insertMember(memberOperDTO);
	}

	public void updateMember(MemberDTO memberDTO) throws DataAccessException {
		memberDAO.updateMember(memberDTO);
	}

	public void delMember(String id) throws DataAccessException {
		memberDAO.delMember(id);
	}

	public MemberDTO login(MemberDTO member) throws DataAccessException {
		return memberDAO.login(member);
	}

	public boolean checkId(String memberId) throws DataAccessException {
		return memberDAO.checkId(memberId);
	}
	
}
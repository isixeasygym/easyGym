 	package com.isix.easyGym.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.MemberDTO;

@Service("memberService")  //아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class MemberServiceImpl implements MemberService {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberDTO memberDTO;
	
	public List listMembers() throws DataAccessException {
		List membersList=memberDAO.selectAllMembersList();
		return membersList;
	}

	public void addMember(MemberDTO memberDTO) throws DataAccessException {
		memberDAO.insertMember(memberDTO);		
	}
	
	public MemberDTO findMember(String id) throws DataAccessException {
	/*	MemberDTO memberDTO=new MemberDTO();
		memberDTO=memberDAO.selectMemberById(id); */
		MemberDTO memberDTO=memberDAO.selectMemberById(id);
		return memberDTO;
	}
	
	public void updateMember(MemberDTO memberDTO) throws DataAccessException {
		memberDAO.updateMember(memberDTO);
	}
	
	public void delMember(String id) throws DataAccessException {
		memberDAO.delMember(id);
	}

	public MemberDTO loginCheck(int userId) throws DataAccessException{
		memberDTO=memberDAO.loginChecking(userId);
		return memberDTO;
	}
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException {
		return memberDAO.loginCheck(memberDTO);
	}
}

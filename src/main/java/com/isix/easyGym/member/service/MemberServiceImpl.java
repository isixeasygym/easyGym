package com.isix.easyGym.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.MemberDTO;

@Service("memberService") // 아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public void addMember(MemberDTO memberDTO) throws DataAccessException {
		memberDAO.insertMember(memberDTO);
	}

	public MemberDTO findMember(String id) throws DataAccessException {
		MemberDTO memberDTO = memberDAO.selectMemberById(id);
		return memberDTO;
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
	// 중복체크
	public String checkId(String memberId) throws DataAccessException {
		return memberDAO.checkId(memberId);

	}

	@Override
	public MemberDTO loginCheck(int memberNo) throws DataAccessException {
		MemberDTO result = memberDAO.loginChecking(memberNo);
		return result;
	}

	@Override
	public int findMemberNo(int memberNo) throws DataAccessException {
		int memberNum=memberDAO.selectMemberNo(memberNo);
		return memberNum;
	}

}
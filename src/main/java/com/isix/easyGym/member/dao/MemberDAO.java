package com.isix.easyGym.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.dto.MemberOperDTO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {	
	public void insertMember(MemberDTO memberDTO) throws DataAccessException;  //member.xml의 select id
	
	public MemberDTO selectMemberById(String id) throws DataAccessException;
	
	public void updateMember(MemberDTO memberDTO) throws DataAccessException;  //수정할 작업을 넘겨줌
	
	public void delMember(String id) throws DataAccessException;  //삭제하기 위해 id를 넘김

	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException;
	
	public String checkId(String memberId) throws DataAccessException;
	//detail dibs 관련 메서드이므로 지우지 말아 주세요.
	public int loginChecking(int memberNo) throws DataAccessException;
	//detail 댓글 관련 메서드이므로 지우지 말아 주세요.
	public int selectMemberNo(int memberNo) throws DataAccessException;
	
	public void insertOperator(MemberOperDTO memberOperDTO) throws DataAccessException;  //member.xml의 select id
	
	public MemberOperDTO selectMemberByOperId(String operatorId) throws DataAccessException;
	
	public void updateOperator(MemberOperDTO memberOperDTO) throws DataAccessException;  //수정할 작업을 넘겨줌
	
	public void delOperator(String operatorId) throws DataAccessException;  //삭제하기 위해 id를 넘김

	public MemberOperDTO operLogin(MemberOperDTO operator) throws DataAccessException;
		
	public void insertGym(MemberDTO memberDTO) throws DataAccessException; 
	
	public boolean selectId(String memberId) throws DataAccessException; 
	
}

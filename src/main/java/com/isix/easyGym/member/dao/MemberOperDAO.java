package com.isix.easyGym.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.member.dto.MemberOperDTO;

@Mapper
@Repository("memberOperDAO")
public interface MemberOperDAO {	
	public void insertMember(MemberOperDTO memberOperDTO) throws DataAccessException;  //member.xml의 select id
	
	public MemberOperDTO selectMemberById(String operatorId) throws DataAccessException;
	
	public void updateMember(MemberOperDTO memberOperDTO) throws DataAccessException;  //수정할 작업을 넘겨줌
	
	public void delMember(String operatorId) throws DataAccessException;  //삭제하기 위해 id를 넘김

	public MemberOperDTO operLogin(MemberOperDTO operator) throws DataAccessException;
	
	public boolean checkOpId(String operatorId) throws DataAccessException;

	public boolean selectOpId(String operatorId) throws DataAccessException; 

}

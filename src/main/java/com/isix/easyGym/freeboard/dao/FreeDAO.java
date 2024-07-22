package com.isix.easyGym.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.freeboard.dto.FreeDTO;
import com.isix.easyGym.member.dto.MemberDTO;

@Mapper
@Repository("freeDAO")
public interface FreeDAO {
	
	public List selectAll(@Param("count") int count) throws DataAccessException;
	
	// 조회수 카운트
	public int hitCount(int freeNo) throws DataAccessException;
	
	public int selectToFboard() throws DataAccessException;
	
	public int getNewFreeNo() throws DataAccessException; // 글 번호 가져오기
	
	public MemberDTO selectMem(int freeNo) throws DataAccessException;
	
	// 여러개 이미지 파일 추가
	public void insertNewFboard(Map fbmap) throws DataAccessException;
	
	public void insertNewImages(Map fbmap) throws DataAccessException;
	
	// 한개의 이미지 파일에 데이터를 조회
	public FreeDTO selectFboard(int freeNo) throws DataAccessException;
	
	// 여러개의 이미지를 담고있는 테이블에 articleNo가 같은 것들을 리스트로 반환
	public List selectImageFileList(int freeNo) throws DataAccessException;

	// 여러개의 이미지 게시판 업데이트
	public void updateFboard(Map fbmap) throws DataAccessException;
	// 여러개의 이미지 수정
	public void updateImage(Map fbmap) throws DataAccessException;
	
	public void deleteFboard(int freeNo) throws DataAccessException;
	
	// 댓글
	public List selectAnswer() throws DataAccessException;

	public int getAnswerNo() throws DataAccessException; 
	
	public void insertNewAnswer(Map amap) throws DataAccessException;
	
	public void insertAnswerImages(Map amap) throws DataAccessException;

	public void updateAnswer(Map amap) throws DataAccessException;
	
	public void updateAnswerImage(Map amap) throws DataAccessException;
	
	public void deleteAnswer(int fbanswerNo) throws DataAccessException;
}

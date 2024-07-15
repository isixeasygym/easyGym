package com.isix.easyGym.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.notice.dto.NoticeDTO;

@Mapper // 매퍼어노테이션을 선언하면 추상메소드의 이름과 같은 매퍼의 id를 읽는다.
@Repository("noticeDAO")
public interface NoticeDAO {

	// 공지사항 리스트
	public List selectAll() throws DataAccessException; // mapper의 전체리스트 검색 id를 메소드명으로 지정 
		
	// 조회수 카운트
	public int hitCount(int noticeNo) throws DataAccessException;	
	
	// 공지사항 번호 가져오기
	public int getNewNoticeNo() throws DataAccessException;
	
	// 공지사항 등록
	public void insertNewNotice(Map noticeMap) throws DataAccessException;

	// 이미지 추가
	public void insertNewImages(Map noticeMap) throws DataAccessException;
	
	// 상세
	public NoticeDTO selectNotice(int noticeNo) throws DataAccessException; 
	
	public List selectImageFileList(int noticeNo) throws DataAccessException; 
	
	// 글수정
	public void updateNotice(Map noticeMap) throws DataAccessException;
	
	// 이미지 수정
	public void updateImage(Map noticeMap) throws DataAccessException;
	
	// 삭제
	public void delNotice(int noticeNo) throws DataAccessException;
}

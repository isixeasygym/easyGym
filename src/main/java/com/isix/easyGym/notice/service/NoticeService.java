package com.isix.easyGym.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.notice.dto.NoticeDTO;

public interface NoticeService {

	// 공지사항 리스트
	public List noticeList() throws DataAccessException;

	// 조회수 카운트
	public int readCount(int noticeNo) throws DataAccessException;
	
	//여러 개의 이미지 추가
	public int addNotice(Map aticleMap) throws DataAccessException;


	//여러 개의 이미지 상세 글보기
	public Map viewNotice(int noticeNo) throws DataAccessException;
	
	//여러 개의 이미지 글 수정하기
	public void modNotice(Map noticeMap) throws DataAccessException;
	
	// 공지사항 삭제
	public void removeNotice(int noticeNo) throws DataAccessException;

}

package com.isix.easyGym.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isix.easyGym.notice.dao.NoticeDAO;
import com.isix.easyGym.notice.dto.NoticeDTO;
import com.isix.easyGym.notice.dto.NoticeImageDTO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
	private NoticeDAO noticeDAO;
	
	// 회원 리스트
	public List noticeList() throws DataAccessException {
		
		List mlist=noticeDAO.selectAll();
		return mlist;
	}
	
	// 조회수 카운트
	@Override
	public void readCount(int noticeNo) throws DataAccessException{
		noticeDAO.hitCount(noticeNo);
		System.out.println(noticeDAO.hitCount(noticeNo)+"조회수 카운트" + "게시판번호" + noticeNo);
	}

	// 공지사항 등록
	@Override
	public int addNotice(Map noticeMap) throws DataAccessException{
		int noticeNo = noticeDAO.getNewNoticeNo();
		System.out.println(noticeNo + "번호서비스");
		noticeMap.put("noticeNo", noticeNo);
		noticeDAO.insertNewNotice(noticeMap);
		if(noticeMap.get("imageFileList") != null) {
			System.out.println("들어옴!!!!!!!!!!!!");
			noticeDAO.insertNewImages(noticeMap);
		}
		
		return noticeNo;
	}

	//여러 개의 이미지 상세 글보기
	@Override
	public Map viewNotice(int noticeNo) throws DataAccessException{
		Map noticeMap=new HashMap<>();
		noticeDAO.hitCount(noticeNo);
		NoticeDTO noticeDTO=noticeDAO.selectNotice(noticeNo);
		List<NoticeImageDTO> imageFileList = noticeDAO.selectImageFileList(noticeNo);
		noticeMap.put("notice", noticeDTO);
		noticeMap.put("imageFileList", imageFileList);
		return noticeMap;
	}
	
	//여러 개의 이미지 글 수정하기
	@Override
	public void modNotice(Map noticeMap) throws DataAccessException{
		noticeDAO.updateNotice(noticeMap);  //1.글 수정시 글만 수정할 때
		noticeDAO.updateImage(noticeMap);
	}
	
	// 공지사항 삭제
	@Override
	public void removeNotice(int noticeNo) throws DataAccessException{
		noticeDAO.delNotice(noticeNo);
	}


}

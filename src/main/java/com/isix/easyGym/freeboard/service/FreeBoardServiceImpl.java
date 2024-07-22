package com.isix.easyGym.freeboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.freeboard.dao.FreeDAO;
import com.isix.easyGym.freeboard.dto.FreeDTO;
import com.isix.easyGym.freeboard.dto.FreeImageDTO;
import com.isix.easyGym.member.dto.MemberDTO;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeDAO freeDAO;
	
	@Override
	public Map listFboard(Map<String,Integer> pagingMap) throws DataAccessException {
		Map fbmap = new HashMap<>();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		int count = (section-1)*100+(pageNum-1)*10;
		List<FreeDTO> fblist  = freeDAO.selectAll(count);
		int tFreeboard = freeDAO.selectToFboard(); // 토탈 게시글
		fbmap.put("fblist", fblist); // map안에 리스트와 토탈 글 숫자, 글 갯수 를 넣는다.
		fbmap.put("tFreeboard", 324); // 페이징 임시 처리
		//fbmap.put("tFreeboard", tFreeboard);
		return fbmap;
	}

	
	// 조회수 카운트
		@Override
		public void readCount(int freeNo) throws DataAccessException{
			freeDAO.hitCount(freeNo);
			System.out.println(freeDAO.hitCount(freeNo)+"조회수 카운트" + "게시판번호" + freeNo);
		}
	
	
	
	// 여러개의 이미지 추가
	@Override
	public int addFboard(Map fbmap) throws DataAccessException {
		int freeNo = freeDAO.getNewFreeNo(); // 게시판 번호 가져오기
		fbmap.put("freeNo", freeNo);
		freeDAO.insertNewFboard(fbmap);
		if(fbmap.get("imageFileList")!= null) {
			freeDAO.insertNewImages(fbmap);
		}
		return freeNo;
	}

	
	// 여러개의 이미지 상세보기

	public Map viewFboard(int freeNo) throws DataAccessException {
		Map fbmap = new HashMap<>();
		freeDAO.hitCount(freeNo);
		FreeDTO fboard = freeDAO.selectFboard(freeNo);
		List<FreeImageDTO> imageFileList = freeDAO.selectImageFileList(freeNo); // 이미지 저장 테이블에 있는 articleNo가 같은 것들을 전부 select해서 list에 담는다.
		MemberDTO mDTO = freeDAO.selectMem(freeNo);
		fbmap.put("fboard", fboard); 
		fbmap.put("imageFileList", imageFileList); // 위에서 가져온 리스트를 map에 담는다.
		fbmap.put("mDTO", mDTO);
		return fbmap;
	}

	
	
	
	// 여러개 이미지 수정
	public void modFboard(Map map) throws DataAccessException {
		freeDAO.updateFboard(map);
		freeDAO.updateImage(map);
	}

	public void removeFboard(int freeNo) throws DataAccessException {
		System.out.println(freeNo + "번호");
		freeDAO.deleteFboard(freeNo);
	}
}

package com.isix.easyGym.freeboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.freeboard.dao.FreeDAO;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private FreeDAO freeDAO;
	
	public Map answerList(Map<String,Object> map) throws DataAccessException{
		Map amap = new HashMap<>();
		List alist = freeDAO.selectAnswer();
		// 게시글의 번호와 댓글의 게시글(fk) 같은걸 조회
		amap.put("alist", alist);
		return amap;
	}
	
	
	
	 @Override
    public int addAnswer(Map<String, Object> amap) throws DataAccessException {
        int fbanswerNo = freeDAO.getAnswerNo(); // 게시판 번호 가져오기
        System.out.println("댓글번호 " + fbanswerNo);
        amap.put("fbanswerNo", fbanswerNo);
        freeDAO.insertNewAnswer(amap);
        if (amap.get("imageFileList") != null) {
        	freeDAO.insertAnswerImages(amap);
        }
        return fbanswerNo;
    }
	
	public void modAnswer(Map amap) throws DataAccessException {
		freeDAO.updateAnswer(amap);
		freeDAO.updateAnswer(amap);
	}

	public void removeAnswer(int fbanswerNo) throws DataAccessException {
		System.out.println(fbanswerNo + "번호");
		freeDAO.deleteAnswer(fbanswerNo);
	}
}

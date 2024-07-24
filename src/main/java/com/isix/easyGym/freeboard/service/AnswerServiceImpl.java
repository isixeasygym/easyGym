package com.isix.easyGym.freeboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.freeboard.dao.AnswerDAO;
import com.isix.easyGym.freeboard.dao.FreeDAO;
import com.isix.easyGym.freeboard.dto.AnswerDTO;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private FreeDAO freeDAO;
	
//	public Map answerList(Map<String,Object> map) throws DataAccessException{
//		Map amap = new HashMap<>();
//		List alist = answerDAO.selectAnswer();
//		// 게시글의 번호와 댓글의 게시글(fk) 같은걸 조회
//		amap.put("alist", alist);
//		return amap;
//	}
//	
//	
//	
//	 @Override
//	    public int addAnswer(Map<String, Object> amap) throws DataAccessException {
//	        int fbanswerNo = answerDAO.getAnswerNo(); // 게시판 번호 가져오기
//	        System.out.println("댓글번호 " + fbanswerNo);
//	        amap.put("fbanswerNo", fbanswerNo);
//	        answerDAO.insertNewAnswer(amap);
//	        if (amap.get("imageFileList") != null) {
//	            answerDAO.insertAnswerImages(amap);
//	        }
//	        return fbanswerNo;
//	    }
//	
//
//
//	public void removeAnswer(int fbanswerNo) throws DataAccessException {
//		System.out.println(fbanswerNo + "번호");
//		answerDAO.deleteAnswer(fbanswerNo);
//	}
	
	@Override
    public Map<String, Object> answerList(Map<String, Object> map) throws DataAccessException {
        Map<String, Object> amap = new HashMap<>();
        List<AnswerDTO> alist = freeDAO.selectAnswer((Integer) map.get("freeNo"));
        amap.put("alist", alist);
        return amap;
    }

    @Override
    public int addAnswer(Map<String, Object> amap) throws DataAccessException {
        int fbanswerNo = freeDAO.getAnswerNo();
        amap.put("fbanswerNo", fbanswerNo);
        freeDAO.insertNewAnswer(amap);
        return fbanswerNo;
    }

    @Override
    public void removeAnswer(int fbanswerNo) throws DataAccessException {
    	freeDAO.deleteAnswer(fbanswerNo);
    }

    @Override
    public AnswerDTO getAnswerByNo(int fbanswerNo) throws DataAccessException {
        return freeDAO.selectAnswerByNo(fbanswerNo);
    }
}

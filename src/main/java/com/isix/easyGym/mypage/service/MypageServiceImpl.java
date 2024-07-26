package com.isix.easyGym.mypage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.mypage.dao.MypageDAO;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MypageDAO mypageDAO;
	
	//1-2)찜 목록
/*	public Map detailDibsList(Map<String, Integer> pagingMap) throws DataAccessException {
		Map dibsMap=new HashMap<>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		int count=(section-1)*100+(pageNum-1)*10;  //페이징 => 1페이지당 10개글, 1섹션당 10페이지, 한화면에 10페이지 100개글로 보이기
		List<DetailDTO> detailList=mypageDAO.selectAllDetail(count);  //selectAllArticles => board.xml의 id
		int totDibs=mypageDAO.selectToDibs();
		dibsMap.put("detailList", detailList);
		dibsMap.put("totDibs", totDibs);
		return dibsMap;
	} */
	@Override
	public List<DetailDTO> detailDibsList(int memberNo) throws DataAccessException {
		return mypageDAO.selectAllDetail(memberNo);
	}
	
	//1-2)찜 취소
	@Override
	public void removeDibs(int memberNo, int detailNo) throws DataAccessException {
		System.out.println("Removing dibs for Member No: " + memberNo + ", Detail No: " + detailNo); // 디버깅용 로그
	    mypageDAO.deleteDibs(memberNo, detailNo);
	}
	
	
	//2-1)포인트
/*	@Override
	public List<MemberDTO> getPointsByMemberNo(int memberNo) throws DataAccessException {
        return mypageDAO.selectPointsByMemberNo(memberNo);
    }
	
	//2-2)쿠폰
	@Override
    public List<MemberDTO> getCouponsByMemberNo(int memberNo) throws DataAccessException {
        return mypageDAO.selectCouponsByMemberNo(memberNo);
    } */
	
	//3-1)비밀번호 체크
	public boolean checkPassword(int memberNo, String memberPwd) throws DataAccessException {
		//System.out.println("checkPassword service called with memberNo: " + memberNo);
	    String storedPassword = mypageDAO.getPasswordByMemberNo(memberNo);
	    //System.out.println("Stored password: " + storedPassword);
	    return storedPassword != null && storedPassword.equals(memberPwd);
    }
	
	//3-2)회원정보 수정
	public void memberUpdate(MemberDTO memberDTO) throws DataAccessException {
		mypageDAO.memberUpdate(memberDTO);
	} 
	
	// 회원 탈퇴
	@Override
		public void delMember(int memberNo) {
			mypageDAO.memberDelete(memberNo);
		}	
}

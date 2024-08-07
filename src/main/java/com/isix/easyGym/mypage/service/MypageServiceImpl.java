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
	
	//1.내 정보
	//찜 목록
	@Override
	public List<DetailDTO> detailDibsList(int memberNo) throws DataAccessException {
		return mypageDAO.selectAllDetail(memberNo);
	}

	//이용중인 상품 목록 가져오기
	@Override
	public List getPayformNo(int memberNo) throws DataAccessException {
		return mypageDAO.selectPayformNo(memberNo);
	}
	
	//찜 취소
	@Override
	public void removeDibs(int memberNo, int detailNo) throws DataAccessException {
		System.out.println("Removing dibs for Member No: " + memberNo + ", Detail No: " + detailNo); // 디버깅용 로그
	    mypageDAO.deleteDibs(memberNo, detailNo);
	}
	
	
	//2.내역조회
	//구매내역
	@Override
	public List getPurchase(int memberNo) throws DataAccessException {
		return mypageDAO.selectPurchase(memberNo);
	}
	//신고내역
	
	//리뷰내역
	@Override
	public List getReview(int memberNo) throws DataAccessException {
		return mypageDAO.selectReview(memberNo);
	}
	
	
	//3.정보수정
	//비밀번호 체크
	public boolean checkPassword(int memberNo, String memberPwd) throws DataAccessException {
		//System.out.println("checkPassword service called with memberNo: " + memberNo);
	    String storedPassword = mypageDAO.getPasswordByMemberNo(memberNo);
	    //System.out.println("Stored password: " + storedPassword);
	    return storedPassword != null && storedPassword.equals(memberPwd);
    }
	
	//회원정보 수정
	public void memberUpdate(MemberDTO memberDTO) throws DataAccessException {
		mypageDAO.memberUpdate(memberDTO);
	} 
	
	//회원 탈퇴
	@Override
		public void delMember(int memberNo) {
			mypageDAO.memberDelete(memberNo);
		}

		
}

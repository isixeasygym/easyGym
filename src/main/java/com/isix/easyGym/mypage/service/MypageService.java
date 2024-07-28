package com.isix.easyGym.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.member.dto.MemberDTO;

public interface MypageService {

    //1-2)찜 목록
    //public Map detailDibsList(Map<String, Integer> pagingMap) throws DataAccessException;
    public List<DetailDTO> detailDibsList(int memberNo) throws DataAccessException;

    //1-2)찜 취소
    public void removeDibs(int memberNo, int detailNo) throws DataAccessException;

    public List getPayformNo(int memberNo) throws DataAccessException;

    //2-1)포인트
    //public List<MemberDTO> getPointsByMemberNo(int memberNo) throws DataAccessException;

    //2-2)쿠폰
    //public List<MemberDTO> getCouponsByMemberNo(int memberNo) throws DataAccessException;

    //3-1)비밀번호 체크
    public boolean checkPassword(int memberNo, String memberPwd) throws DataAccessException;

    //3-2)회원정보 수정
    public void memberUpdate(MemberDTO memberDTO) throws DataAccessException;


    public void delMember(int memberNo);


}

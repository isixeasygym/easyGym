package com.isix.easyGym.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface MypageController {
	
	//1.내 정보
	//첫 페이지(이용중인 상품)
	public ModelAndView mypageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;

	//이용중인 상품 목록 가져오기 / 찜 목록 가져오기
	public Map<String, Object> mypageData(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//찜 취소
	public ModelAndView removeDibs(@RequestParam("detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//포인트
	
	
	//2.내역조회
	public Map<String, Object> searchHistory(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	//3.정보수정
	//비밀번호 체크
	public ResponseEntity<Boolean> checkPassword(
	        @RequestParam("memberNo") int memberNo,
	        @RequestParam("memberPwd") String memberPwd) throws Exception;
			
	//회원정보 수정
	public ModelAndView memberUpdate(String memberPwd, String memberPhone,
			String memberEmail, HttpServletRequest request) throws Exception;

	//회원탈퇴
	public ModelAndView delMember(int memberNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

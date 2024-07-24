package com.isix.easyGym.mypage.controller;

import java.util.List;

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
	
	//1.내 정보 - 첫 페이지(이용중인 상품)
	public ModelAndView mypageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//1-1)이용중인 상품 - 이용권 취소하기
	public ModelAndView ticketCancel(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//1-1)이용중인 상품 - 이용권 환불하기
	public ModelAndView ticketRefund(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//1-2)찜 목록
	//public ModelAndView detailDibsList(@RequestParam("section") String _section, @RequestParam("pageNum") String _pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public List<DetailDTO> detailDibsList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//2.포인트&쿠폰
	public String pointsAndCoupons(@RequestParam("memberNo") int memberNo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//3.정보수정
	//3-1)비밀번호 체크
	public String checkPassword(@RequestParam("password") String password, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
			
	//3-2)회원정보 수정
	public ModelAndView updateMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

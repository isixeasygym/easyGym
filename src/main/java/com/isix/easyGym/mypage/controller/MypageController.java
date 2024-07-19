package com.isix.easyGym.mypage.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MypageController {
	
	//1.내 정보 - 첫 페이지(이용중인 상품)
	public ModelAndView mypageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//1-1)이용중인 상품 - 이용권 취소하기
	public ModelAndView ticketCancel(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//1-1)이용중인 상품 - 이용권 환불하기
	public ModelAndView ticketRefund(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//1-2)찜 목록
	public ModelAndView detailDibsList(@RequestParam("section") String _section, @RequestParam("pageNum") String _pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
	//들어올 때 맵핑으로 해당 컨트롤러로 가면 
	//회원 번호에 있는 찜 목록 select문 수행해서 리스트형 변수값에 넣고  
	
}

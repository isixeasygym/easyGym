package com.isix.easyGym.mypage.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MypageController {
	
	//내 정보(이용중인 상품, 정보 수정)
/*	public ModelAndView mypageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//구매내역
	public ModelAndView buyHistory(@RequestParam("buyNo") int buyNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//문의내역
	public ModelAndView contactHistory(@RequestParam("contactNo") int contactNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
*/
	//신고내역
	public ModelAndView reportHistorys(@RequestParam("reportNo") int reportNo, HttpServletRequest request, HttpServletResponse response) throws Exception;

}

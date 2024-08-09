package com.isix.easyGym.admin.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.admin.dto.AdminDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AdminController {

	
	public ModelAndView joinAd(@ModelAttribute("adminDTO") AdminDTO adminDTO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView loginForm(@ModelAttribute("admin") AdminDTO admin,@RequestParam(value="action" ,required=false) String action, @RequestParam(value="result", required=false) String result,  HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView login(@ModelAttribute("admin") AdminDTO admin, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;	
	
	// 회원 리스트 
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 탈퇴 회원 리스트
	public ModelAndView withdrawMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 사업자 리스트 
	public ModelAndView operatorList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 업체 리스트
	public ModelAndView companyList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	// 회원 가입폼
	public ModelAndView adminForm(HttpServletRequest request, HttpServletResponse response) throws Exception;  //memberForm은 미수정 => 회원가입한 폼만 보여주는거라서 Annotation 관련 수정은 필요없음
	
	// 신고 리스트
	public ModelAndView reportList(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

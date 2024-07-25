package com.isix.easyGym.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.member.dto.MemberOperDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberOperController {
	
//사업자가입 페이지 
	public ModelAndView operJoinForm(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;   
//사업자가입 기능 
	public ModelAndView addOperator(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;
//사업자로그인 페이지
	public ModelAndView operLoginForm(@ModelAttribute("operator") MemberOperDTO operator, @RequestParam(value = "action", required = false) String action, @RequestParam(value = "result", required = false) String result, HttpServletRequest request, HttpServletResponse response) throws Exception;    //action : 어디서 왔는지 판단 => 로그인을 눌러서 로그인창으로 왔는지? -> 메인페이지 / 글 목록창에서 글쓰기하려다 로그인하라고 해서 로그인 창으로 왔는지? -> 다시 글 목록창으로 이동
//사업자 로그인 기능 
	public ModelAndView operLogin(@ModelAttribute("operator") MemberOperDTO operator, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
//사업자 로그인 완료 페이지
	public ModelAndView afterEntJoin(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;   
//사업자 탈퇴?	
	public ModelAndView delOperator(@RequestParam("operatorId") String operatorId, HttpServletRequest request, HttpServletResponse response) throws Exception;
//사업자 아이디 중복확인
	public ResponseEntity<Boolean> confirmOpId(String operatorId);

	
}

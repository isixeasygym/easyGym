package com.isix.easyGym.member.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.member.dto.MemberOperDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberOperController {

	public ModelAndView addOperator(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView delOperator(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView operLoginForm(@ModelAttribute("operator") MemberOperDTO operator, @RequestParam(value = "action", required = false) String action, @RequestParam(value = "result", required = false) String result, HttpServletRequest request, HttpServletResponse response) throws Exception;    //action : 어디서 왔는지 판단 => 로그인을 눌러서 로그인창으로 왔는지? -> 메인페이지 / 글 목록창에서 글쓰기하려다 로그인하라고 해서 로그인 창으로 왔는지? -> 다시 글 목록창으로 이동
	//result : 로그인시 가입한 이력이 있는지 판단 => 회원가입이 안되어 있다면 회원가입창으로 이동시키기 

	public ModelAndView operLogin(@ModelAttribute("operator") MemberOperDTO member, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

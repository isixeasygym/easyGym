package com.isix.easyGym.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.dto.MemberOperDTO;
import com.isix.easyGym.member.service.MemberOperService;
import com.isix.easyGym.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller("memberOperController")
public class MemberOperControllerImpl implements MemberOperController {

	@Autowired
	private MemberOperService memberOperService;
	
	@Autowired
	private MemberOperDTO memberOperDTO;

//	@Override
//	@RequestMapping(value="/member/operJoin.do")
//	public ModelAndView addOperator(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request,
//			HttpServletResponse response) throws Exception{
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/member/operJoin.do");
//		return mav;
//	}

}

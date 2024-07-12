package com.isix.easyGym.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.service.MemberService;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberDTO memberDTO;
	
	
}

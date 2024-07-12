package com.isix.easyGym.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.dto.MemberOperDTO;
import com.isix.easyGym.member.service.MemberOperService;
import com.isix.easyGym.member.service.MemberService;

@Controller("memberOperController")
public class MemberOperControllerImpl implements MemberOperController {

	@Autowired
	private MemberOperService memberOperService;
	
	@Autowired
	private MemberOperDTO memberOperDTO;
	
	
}

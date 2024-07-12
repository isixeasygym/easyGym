package com.isix.easyGym.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dao.MemberOperDAO;

@Service("memberOperService")
public class MemberOperServiceImpl implements MemberOperService {

	@Autowired
	private MemberOperDAO memberOperDAO;
	
	
}

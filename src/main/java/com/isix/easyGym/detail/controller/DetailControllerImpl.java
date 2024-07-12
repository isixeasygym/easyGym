package com.isix.easyGym.detail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.service.DetailService;

@Controller("detailController")
public class DetailControllerImpl implements DetailController {

	@Autowired
	private DetailService detailService;
	
	@Autowired
	private DetailDAO detailDAO;
	
	
}

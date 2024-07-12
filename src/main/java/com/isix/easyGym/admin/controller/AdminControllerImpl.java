package com.isix.easyGym.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.admin.dto.AdminDTO;
import com.isix.easyGym.admin.service.AdminService;

@Controller("adminController")
public class AdminControllerImpl implements AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminDTO adminDTO;
	
	
}

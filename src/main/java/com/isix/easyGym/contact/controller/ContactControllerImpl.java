package com.isix.easyGym.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.contact.dao.ContactDAO;
import com.isix.easyGym.contact.service.ContactService;

@Controller("contactController")
public class ContactControllerImpl implements ContactController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ContactDAO contactDAO;
	
	
}

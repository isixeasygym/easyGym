package com.isix.easyGym.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.contact.dao.ContactDAO;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;
	
	
}

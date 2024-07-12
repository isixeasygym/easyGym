package com.isix.easyGym.payform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.payform.dto.PayformDTO;
import com.isix.easyGym.payform.service.PayformService;

@Controller("payformController")
public class PayformControllerImpl implements PayformController {

	@Autowired
	private PayformService payformService;
	
	@Autowired
	private PayformDTO payformDTO;
	
	
}

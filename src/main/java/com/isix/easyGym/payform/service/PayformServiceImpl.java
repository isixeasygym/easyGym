package com.isix.easyGym.payform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.payform.dao.PayformDAO;

@Service("payformService")
public class PayformServiceImpl implements PayformService {

	@Autowired
	private PayformDAO payformDAO;
	
	
}

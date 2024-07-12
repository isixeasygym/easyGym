package com.isix.easyGym.detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.detail.dao.DetailDAO;

@Service("detailService")
public class DetailServiceImpl implements DetailService {

	@Autowired
	private DetailDAO detailDAO;
	
	
}

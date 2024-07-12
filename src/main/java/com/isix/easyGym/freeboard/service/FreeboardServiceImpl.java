package com.isix.easyGym.freeboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.freeboard.dao.FreeboardDAO;

@Service("freeboardService")
public class FreeboardServiceImpl implements FreeboardService {

	@Autowired
	private FreeboardDAO freeboardDAO;
}

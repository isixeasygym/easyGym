package com.isix.easyGym.freeboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.freeboard.dao.FreeboardDAO;
import com.isix.easyGym.freeboard.dto.FreeboardArticleDTO;
import com.isix.easyGym.freeboard.service.FreeboardService;

@Controller("freeboardController")
public class FreeboardControllerImpl implements FreeboardController {

	@Autowired
	private FreeboardService freeboardService;
	
	@Autowired
	private FreeboardArticleDTO freeboardarticleDTO;  //fbImageDTO는 안씀 => 이미지 하나만 올릴 수 있고, 여러 이미지는 올릴 수 없다함.
	
	
}

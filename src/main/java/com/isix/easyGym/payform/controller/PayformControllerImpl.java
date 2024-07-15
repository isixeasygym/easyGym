package com.isix.easyGym.payform.controller;

import com.isix.easyGym.payform.service.PayformServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.payform.dto.PayformDTO;
import com.isix.easyGym.payform.service.PayformService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller("payformController")
public class PayformControllerImpl implements PayformController {

	@Autowired
	private PayformServiceImpl payformService;
	
	@Autowired
	private PayformDTO payformDTO;

	@Override
	@GetMapping("/buy/buyForm.do")
	public ModelAndView buyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		Map payForm = payformService.payForm(pagingMap);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/buy/buyForm");
		mav.addObject("payForm", payForm);
		return mav;
	}
	
}

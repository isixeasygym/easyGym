package com.isix.easyGym.payform.controller;

import com.isix.easyGym.payform.service.PayformServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.payform.dto.PayformDTO;
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
	@GetMapping("/payform/payformForm.do")
	public ModelAndView payformForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		Map payformForm = payformService.payformForm(pagingMap);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/payform/payformForm");
		mav.addObject("payformForm", payformForm);
		return mav;
	}

	@GetMapping("/payform/payformDone.do")
	public ModelAndView payformDone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		Map payformDone = payformService.payformDone(pagingMap);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/payform/payformDone");
		mav.addObject("payformDone", payformDone);
		return mav;
	}

	@GetMapping("/payform/payformCancel.do")
	public ModelAndView payformCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		Map payformCancel = payformService.payformCancel(pagingMap);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/payform/payformCancel");
		mav.addObject("payformCancel", payformCancel);
		return mav;
	}

	@GetMapping("/payform/payformRefund.do")
	public ModelAndView payformRefund(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		Map payformRefund = payformService.payformRefund(pagingMap);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/payform/payformRefund");
		mav.addObject("payformRefund", payformRefund);
		return mav;
	}
	@GetMapping("/payform/payformTosspay.do")
	public ModelAndView payformTosspay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		Map payformTosspay = payformService.payformTosspay(pagingMap);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/payform/payformTosspay");
		mav.addObject("payformTosspay", payformTosspay);
		return mav;
	}
	
}

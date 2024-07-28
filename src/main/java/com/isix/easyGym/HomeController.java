package com.isix.easyGym;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.service.DetailServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@Autowired
	private DetailServiceImpl detailService;
	
	@GetMapping("/main.do")  //127.0.0.1:8090 => 이렇게만 매핑 보내기
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DetailDTO> healthList=detailService.findPopularHealth();
		List<DetailDTO> boxingList=detailService.findPopularBoxing();
		List<DetailDTO> pilatesList=detailService.findPopularPilates();
		ModelAndView mav=new ModelAndView();
		mav.addObject("healthList", healthList);
		mav.addObject("boxingList", boxingList);
		mav.addObject("pilatesList", pilatesList);
		mav.setViewName("main");
		return mav;
	}
}

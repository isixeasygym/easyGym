package com.isix.easyGym.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.member.dto.KakaoDTO;
import com.isix.easyGym.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("memberLogout")
public class MemberLogoutController {

	@Autowired MemberService memberService;
	
	
    //@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
    //public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    //    // 세션 무효화
    //    request.getSession().invalidate();
    //    // 세션 ID 쿠키 삭제
    //    Cookie sessionCookie = new Cookie("JSESSIONID", "");
    //    sessionCookie.setMaxAge(0);
    //    sessionCookie.setPath("/");
    //    response.addCookie(sessionCookie);
    //
    //    ModelAndView mav = new ModelAndView();
    //    mav.setViewName("redirect:/main.do");
    //    return mav;
    //}
    
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=(HttpSession) request.getSession(false);
		if(session != null) {
			int sns=(int)session.getAttribute("sns");
			if(sns == 1) {
				KakaoDTO kakaoDTO=(KakaoDTO)session.getAttribute("member");
				memberService.kakaoLogout(kakaoDTO.getKakaoId());
			}
			session.removeAttribute("member");
			session.removeAttribute("isLogOn");		
		}		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}
}
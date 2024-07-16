package com.isix.easyGym.member.kakao;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.isix.easyGym.member.kakao.KakaoAPI;

import jakarta.servlet.http.HttpSession;

@RestController
public class KakaoController {
	KakaoAPI kakaoApi = new KakaoAPI();
	
	@RequestMapping(value="/kakao-login")
	public ModelAndView kakao_login(@RequestParam("code") String code, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달
		String access_token = kakaoApi.getAccessToken(code);
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(access_token);
		
		System.out.println("login info : " + userInfo.toString());
		
		if(userInfo.get("email")!=null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("access_token",  access_token);
			
		}
		mav.addObject("userId", userInfo.get("email"));
		mav.setViewName("loginForm");
		return mav;
	}

	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		kakaoApi.kakaoLogout((String)session.getAttribute("access_token"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("loginForm");
		return mav;
	}
	
}
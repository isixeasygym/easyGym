package com.isix.easyGym.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.dto.MemberOperDTO;
import com.isix.easyGym.member.service.MemberOperService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("memberOperController")
public class MemberOperControllerImpl implements MemberOperController {

	@Autowired
	private MemberOperService memberOperService;
	
	@Autowired
	private MemberOperDTO memberOperDTO;
	
	// 사업자 회원가입 페이지
	@RequestMapping(value = "/member/operJoinForm.do")
	public ModelAndView operJoinPage(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/operJoin");
		return mav;
	}
	// 사업자 회원가입 기능
	@PostMapping(value = "/member/operJoin.do")
	public ModelAndView addOperator(@ModelAttribute("memberOperDTO") MemberOperDTO memberOperDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberOperService.addOperator(memberOperDTO);
		mav.setViewName("redirect:/afterEntJoin.do");
		return mav;
	}
	
	// 사업자 회원가입 완료 페이지
	@Override
	public ModelAndView afterEntJoin(MemberOperDTO memberOperDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/afterEntJoin");
		return mav;
	}
	
	// 사업자 로그인 페이지
	@Override
	@RequestMapping(value = "/member/operLoginForm.do")
	public ModelAndView operLoginForm(MemberOperDTO operator, String action, String result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/operLoginForm");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/operLogin.do", method = RequestMethod.POST)
	public ModelAndView operLogin(@ModelAttribute("operator") MemberOperDTO operator, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		memberOperDTO = memberOperService.operLogin(operator);
		ModelAndView mv = new ModelAndView();	
		if (memberOperDTO != null) {
			HttpSession session= request.getSession();
			session.setMaxInactiveInterval(30 * 60);
			session.setAttribute("operator", memberOperDTO);
			session.setAttribute("isLogOn", true);
			String action = (String) session.getAttribute("action");
			if (action != null) {
				mv.setViewName("redirect:" + action);
			} else {
				mv.setViewName("redirect:/main.do");
			}
		} else {
			rAttr.addAttribute("result", "아이디, 비밀번호가 다릅니다. 다시 로그인해주세요.");
			mv.setViewName("redirect:/member/operLoginForm.do");
		}
		return mv;
	}

	
//	// 사업자 아이디 중복확인
//	@Override
//	@RequestMapping(value="/member/checkOpId.do", produces="application/text;charset=utf-8")
//	public ModelAndView checkOpId(@RequestParam("operatorId") String operatorId, HttpServletRequest request, HttpServletResponse response)throws Exception{
//		ModelAndView mav = new ModelAndView();
//		if(memberOperService.checkId(operatorId)) {
//			mav.addObject("message", "이미 사용중인 ID입니다.");
//		} else {
//			mav.addObject("message", "사용 가능한 ID입니다.");
//		}
//		return mav;
//	}
	// 사업자 삭제 기능
	public ModelAndView delOperator(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ModelAndView operJoinForm(MemberOperDTO memberOperDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	// 아이디 중복체크
		@PostMapping("/operator/checkId.do")
		@ResponseBody
		public ResponseEntity<Boolean> confirmOpId(@RequestParam("operatorId")String operatorId) {
			
			boolean result = true;
			
			if(operatorId.trim().isEmpty()) {
				System.out.print("id : " + operatorId);
				result = false;
			} else {
				if (memberOperService.selectId(operatorId)) {
					result = false;
				} else {
					result = true;
				}
			}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
//		@Override
//		public ModelAndView checkOpId(String operatorId, HttpServletRequest request, HttpServletResponse response)
//				throws Exception {
//			// TODO Auto-generated method stub
//			return null;
//		}


}

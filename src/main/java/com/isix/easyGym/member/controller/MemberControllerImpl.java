package com.isix.easyGym.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberDTO memberDTO;

	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/listMembers");
		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/newJoin.do")
	public ModelAndView addMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/newJoin");
		return mav;
	}

	@GetMapping("/report/report.do") // 127.0.0.1:8090 => 이렇게만 매핑 보내기
	public ModelAndView report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/report/report");
		return mav;
	}

	@Override
	@GetMapping("/member/joinSelect.do")
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/joinSelect");
		return mav;
	} // memberForm으로 이동할 거라서 addObject는 필요없음

	@Override
	@GetMapping("/member/modMemberForm.do")
	public ModelAndView modMemberForm(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		memberDTO = memberService.findMember(id); // 회원정보 id를 찾아서 memberDTO에 넘겨줌
		ModelAndView mav = new ModelAndView("/member/modMemberForm");
		mav.addObject("member", memberDTO); // memberDTO=memberService.findMember(id);의 memberDTO에 담긴 id를 member에 담음
		return mav;
	}

	@Override
	@PostMapping("/member/updateMember.do")
	public ModelAndView updateMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.updateMember(memberDTO); // 업데이트 하기
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do"); // 회원정보수정하면 listMembers 메서드를 찾아가서 다시
																				// 회원목록을 보여주게 됨
		return mav;
	}

	@Override
	@GetMapping("/member/delMember.do")
	public ModelAndView delMember(String id, HttpServletRequest request, HttpServletResponse response)throws Exception {
		memberService.delMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do"); // 회원삭제하면 listMembers 메서드를 찾아가서 다시 회원목록을
																				// 보여주게 됨
		return mav;
	}

	@Override
	@GetMapping("/member/loginForm.do") // 회원의 정보를 가지고 간다. 없으면 로그인 폼으로 다시 보낸다.
	public ModelAndView loginForm(@ModelAttribute("member") MemberDTO member,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "result", required = false) String result, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result); // 로그인 실패시 띄우는 메세지 ...
		mv.setViewName("/member/loginForm");
		return mv;
	}
	
	
	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		memberDTO = memberService.login(member);
		ModelAndView mv = new ModelAndView();
		if (memberDTO != null) {
			HttpSession session= request.getSession();
			session.setMaxInactiveInterval(30 * 60);
			session.setAttribute("member", memberDTO);
			session.setAttribute("isLogOn", true);
			String action = (String) session.getAttribute("action");
			if (action != null) {
				mv.setViewName("redirect:" + action);
			} else {
				mv.setViewName("redirect:/main.do");
			}
		} else {
			rAttr.addAttribute("result", "아이디, 비밀번호가 다릅니다. 다시 로그인해주세요.");
			mv.setViewName("redirect:/member/loginForm.do");
		}
		return mv;
	}
	

	
	@Autowired
	private LogoutController logoutController;

	@Override
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return logoutController.logout(request, response);
	}


}

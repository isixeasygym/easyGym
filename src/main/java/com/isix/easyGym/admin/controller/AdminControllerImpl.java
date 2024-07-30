package com.isix.easyGym.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.admin.dao.AdminDAO;
import com.isix.easyGym.admin.dto.AdminDTO;
import com.isix.easyGym.admin.service.AdminServiceImpl;
import com.isix.easyGym.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("adminController")
public class AdminControllerImpl implements AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private AdminDTO adminDTO;
	
	@Autowired
	private AdminDAO adminDAO;
	
	// 관리자 가입
	@Override
	@PostMapping("/admin/joinAd.do")
	public ModelAndView joinAd(AdminDTO adminDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		adminService.addAdmin(adminDTO);
		ModelAndView mv = new ModelAndView("redirect:/admin/loginForm.do");
		return mv;
	}

	@Override
	@GetMapping("/admin/joinForm.do")
	public ModelAndView adminForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/joinForm");
		return mav;
	} 

	

	
	
	@Override
	@GetMapping("/admin/loginForm.do") // 회원의 정보를 가지고 간다. 없으면 로그인 폼으로 다시 보낸다.
	public ModelAndView loginForm(@ModelAttribute("admin") AdminDTO admin, @RequestParam(value="action" ,required=false) String action, @RequestParam(value="result", required=false) String result,  HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession(); 
		session.setAttribute("action", action);  
		ModelAndView mv = new ModelAndView();
		mv.addObject("result",result); 
		mv.addObject("admin",admin); 
		mv.setViewName("admin/loginForm");
		return mv;
	}
	
	
	
	// 관리자 로그인
	@Override
	@PostMapping("/admin/login.do")
	public ModelAndView login(@ModelAttribute("admin") AdminDTO admin, RedirectAttributes rAttr, HttpServletRequest req, HttpServletResponse res) throws Exception {
		adminDTO = adminService.login(admin);
		ModelAndView mv = new ModelAndView();
		if(adminDTO != null) {
			HttpSession session = req.getSession();
			session.setAttribute("admin", adminDTO);
			session.setAttribute("isLogOn", true);
			String action = (String)session.getAttribute("action");
			if(action != null) {
				mv.setViewName("redirect:" + action);
			}else {
				mv.setViewName("redirect:/admin/memberList.do");
			}
		}else {
			rAttr.addAttribute("result","아이디, 비밀번호가 다릅니다.");
			mv.setViewName("redirect:/admin/loginForm.do");
		}
		return mv;
	}
	
	// 로그아웃
	@Override
	@GetMapping("/admin/logout.do")
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		session.removeAttribute("admin");
		session.removeAttribute("isLogOn");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/loginForm.do");
		return mv;
	}
	
	
	
	
	// 회원 전체 조회 
	@RequestMapping(value = "/admin/memberList.do", method = RequestMethod.GET)
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List mlist=adminService.memberList();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/memberList");
		mav.addObject("mlist",mlist);
		return mav;
	}
	
	// 탈퇴 회원 조회 
	@RequestMapping(value = "/admin/withdrawMem.do", method = RequestMethod.GET)
	public ModelAndView withdrawMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List mlist=adminService.withdrawMember();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/withdrawMem");
		mav.addObject("mlist",mlist);
		return mav;
	}
	
	
	// 사업자 전체 조회
	@RequestMapping(value = "/admin/operList.do", method = RequestMethod.GET)
	public ModelAndView operatorList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List olist=adminService.operList();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/operList");
		mav.addObject("olist",olist);
		return mav;
	}

	// 업체 리스트 조회 
	@RequestMapping(value = "/admin/companyList.do", method = RequestMethod.GET)
	public ModelAndView companyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List clist=adminService.comList();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/companyList");
		mav.addObject("clist",clist);
		return mav;
	}
	
	
	
	

	
	
	
	
}
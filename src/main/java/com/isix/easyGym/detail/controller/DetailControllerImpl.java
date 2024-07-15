package com.isix.easyGym.detail.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.service.DetailService;
import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("detailController")
public class DetailControllerImpl implements DetailController{
	
	@Autowired
	private DetailDTO detailDTO;
	
	@Autowired
	private DetailService detailService;
	
	@Autowired
	private DetailDAO detailDAO;
	
	@Autowired
	private DetailDibsDTO detailDibsDTO;
	
	@Autowired
	private MemberDTO memberDTO;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	@GetMapping("/test.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/detail/test");
		return mav;
	}
	
	@Override
	@GetMapping("/detail/detail.do")
	public ModelAndView detailForm(@RequestParam("wholeNo") int wholeNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		detailDTO=detailService.viewDetail(wholeNo);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/detail/detail");
		mav.addObject("details",detailDTO);
		return mav;
	}
	
	
	@Override
	@GetMapping("/detail/showAll.do")
	public ModelAndView selectAll(
			@RequestParam("wholeClassification") String WholeClassification, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			List findAll  = new ArrayList<>();
			findAll = detailService.selectAll(WholeClassification);
			ModelAndView mav = new ModelAndView();
			mav.addObject("allList", findAll);
			mav.setViewName("/detail/healthList");
		return mav;
	}
	
	@Override
	public ModelAndView selectPopular(@RequestParam("wholeStatus") String wholeStatus, @RequestParam("wholeClassification") String wholeClassification, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List findPopular = new ArrayList<>();
		Map<String, String> selectThing = new HashMap<String, String>();
		selectThing.put("wholeStatus", wholeStatus);
		selectThing.put("wholeClassification", wholeClassification);
		selectThing=(Map<String, String>) detailService.selectPopular(selectThing);
		ModelAndView mav = new ModelAndView();
		mav.addObject("allList", selectThing);
		mav.setViewName("/detail/healthList");
		return mav;
	}


	
	@RequestMapping(value="/addFavorite", method=RequestMethod.GET)
	public String dibs(@RequestParam("companyId") String companyId, @RequestParam("userId") String userId,
	                   @RequestParam(value = "action", required = false) String action,
	                   RedirectAttributes rAttr, HttpServletRequest request,
	                   HttpServletResponse response) throws Exception {
	    String status = "null";
	    //System.out.print(userId);
	    // 사용자 로그인 체크
	    String result = memberService.loginCheck(userId);
	    HttpSession session = request.getSession(); // 로그인 정보 세션에 저장
	    session.setAttribute("action", action);
	    session.setAttribute("member", memberDTO);
	    
	    // 로그인 체크
	    if (!result.equals("true")) {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("companyId", companyId);
	        paramMap.put("userId", userId);
	        
	        // 찜 상태 확인
	        detailDibsDTO = detailService.findDibs(paramMap);
	        
	        // 찜 상태에 따라 insert 또는 delete 수행
	        if (detailDibsDTO == null) {
	            detailDAO.insertDibs(paramMap);
	            status = "insert";
	        } else {
	            detailDAO.removeDibs(paramMap);
	            status = "delete";
	        }
	    } else {
	        // 로그인 폼으로 리다이렉트
	        return "redirect:/member/loginForm.do";
	    }
	    
	    return status;
	}
	
	@Override
	public ModelAndView doReport(int memberNo, int wholeNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView review(int memberNo, int wholeNo, MultipartHttpServletRequest MultipartRequest,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}


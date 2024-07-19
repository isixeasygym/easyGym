package com.isix.easyGym.detail.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.service.DetailServiceImpl;
import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.service.MemberServiceImpl;
import com.isix.easyGym.payform.dto.PayformDTO;
import com.isix.easyGym.payform.service.PayformServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Controller("detailController")
public class DetailControllerImpl implements DetailController{
	

	@Autowired
	private DetailServiceImpl detailService;
	
	@Autowired
	private DetailDAO detailDAO;
	
	@Autowired
	private DetailDibsDTO detailDibsDTO;
	
	@Autowired
	private MemberDTO memberDTO;
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PayformDTO payformDTO;
	
	@Autowired
	private PayformServiceImpl payformService;
	
	@Override
	@GetMapping("/test.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/detail/test");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/detail/detail.do", method = RequestMethod.GET)
	public ModelAndView detailForm(@RequestParam("detailNo") String detailNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		detailDTO=detailService.viewDetail(Integer.parseInt(detailNo));
		ModelAndView mav=new ModelAndView();
		mav.addObject("details",detailDTO);
		mav.setViewName("/detail/detail");
		return mav;
	}
	
	
    
	@Override
	@RequestMapping("/detail/showAll.do")
	public ModelAndView selectAll(
			@RequestParam("detailClassification") String detailClassification, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			List findAll  = new ArrayList<>();
			findAll = detailService.selectAll(detailClassification);
			ModelAndView mav = new ModelAndView();
			mav.addObject("allList", findAll);
			mav.setViewName("/detail/healthList");
		return mav;
	}
	
	@Override
	public ModelAndView selectPopular(@RequestParam("detailStatus") String detailStatus, @RequestParam("detailClassification") String detailClassification, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List findPopular = new ArrayList<>();
		Map<String, String> selectThing = new HashMap<String, String>();
		selectThing.put("detailStatus", detailStatus);
		selectThing.put("detailClassification", detailClassification);
		selectThing=(Map<String, String>) detailService.selectPopular(selectThing);
		ModelAndView mav = new ModelAndView();
		mav.addObject("allList", selectThing);
		mav.setViewName("/detail/healthList");
		return mav;
	}


	@Override
	@ResponseBody
	@RequestMapping(value="/addFavorite", method=RequestMethod.GET)
	public String dibs(@RequestParam("companyId") String companyId, @RequestParam("userId") String memberNo,
	                   @RequestParam(value = "action", required = false) String action,
	                   RedirectAttributes rAttr, HttpServletRequest request,
	                   HttpServletResponse response) throws Exception {
		String status;
	    //System.out.print(userId);
	    // 사용자 로그인 체크
	    MemberDTO result = memberService.loginCheck(Integer.parseInt(memberNo));
	    HttpSession session = request.getSession(); // 로그인 정보 세션에 저장
	    
	    // 로그인 체크
	    if (!result.equals("true")) {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("detailNo", companyId);
	        paramMap.put("memberNo", memberNo);
	        
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
	    System.out.print(status);
	    return status;
	}

	@Override
	public String deleteReview(@RequestParam("companyId") String detailNo, @RequestParam("userId") String memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
		Map<String, String> seletedDelete = new HashMap<>();
		seletedDelete.put("detailNo", companyId);
		seletedDelete.put("detailNo", userId);
		delete=detailService.deleteReview(seletedDelete);
		return delete;
	}

	@Override
	public ModelAndView writeReview(@RequestParam("companyId") String detailNo, @RequestParam("userId") String memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		MemberDTO check=memberService.loginCheck(Integer.parseInt(memberNo));
		HttpSession session=request.getSession();
		session.setAttribute("action", session); //로그인폼에서 로그인 성공하면 이쪽으로 오는 지. 맵 세션이 고정적으로 이쪽으로 되있는지.
		if(check != null) {
			payformDTO=payformService.buyCheck(Integer.parseInt(memberNo));
			if(payformDTO != null) {
			mav.setViewName("/detail/reviewForm");
			}
		}else {
			
			mav.setViewName("redirect:/member/loginForm.do");//해당 메서드로 가는 것
		}
		
		return mav;
	}
	
	/*Map<String, String> review = new HashMap<>();
	review.put("memberNo", memberNo);
	review.put("detailNo", detailNo);
	goInsert=d*/

}


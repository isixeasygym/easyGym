package com.isix.easyGym.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.mypage.service.MypageService;
import com.isix.easyGym.report.dto.ReportDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller("mypageController")
public class MypageControllerImpl implements MypageController {

	@Autowired
	private MypageService mypageService;
	
	@Autowired
	//private MemberDTO memberDTO;    //멤버
	//private BuyDTO buyDTO;          //구매하기
	//private ContactDTO contactDTO;  //문의하기
	private ReportDTO reportDTO;    //신고하기
	
	//내 정보
/*	@RequestMapping(value = "/mypage/mypageMain.do")
	public ModelAndView mypageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/mypage/mypageMain");
		return mav;
	}
	
	//구매내역
	public ModelAndView buyHistory(@RequestParam("buyNo") int buyNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}
	
	//문의내역
	public ModelAndView contactHistory(@RequestParam("contactNo") int contactNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}
*/
	//신고내역
	@RequestMapping(value = "/mypage/listArticles.do", method = RequestMethod.GET)
	public ModelAndView reportHistorys(@RequestParam("reportNo") int reportNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		List reportsList=mypageService.reportHistorys();
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("reportsList",reportsList);
		return mav;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//요청명과 메서드와 jsp를 동일한 이름으로 표시 메서드
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath=request.getContextPath();
		//System.out.println(contextPath);
		String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
		//System.out.println(uri);
		if(uri == null || uri.trim().equals("")) {
			uri=request.getRequestURI();
		}
		//System.out.println(uri);
		int begin=0, end;
		if(!(contextPath == null || "".equals(contextPath))) {
			begin=contextPath.length();
		}
		if(uri.indexOf(";") != -1) {
			end=uri.indexOf(";");
		}else if(uri.indexOf("?") != -1) {
			end=uri.indexOf("?");
		}else {
			end=uri.length();
		}
		String fileName=uri.substring(begin,end);
		if(fileName.lastIndexOf(".") != -1) {
			fileName=fileName.substring(0, fileName.lastIndexOf("."));  //. 점 뒤에거 제외
		}
		if(fileName.lastIndexOf("/") != -1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"), fileName.length());  //마지막 / 슬러시를 찾아서 앞에걸 제외
		}
		return fileName;
	}

	
}

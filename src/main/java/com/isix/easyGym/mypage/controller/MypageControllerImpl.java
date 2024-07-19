package com.isix.easyGym.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.mypage.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller("mypageController")
public class MypageControllerImpl implements MypageController {

	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private MemberDTO memberDTO;  //멤버
	private DetailDTO detailDTO;  //업체정보
	private DetailDibsDTO detailDibsDTO;  //찜목록
	
	//1.내 정보 - 첫 페이지(이용중인 상품)
	@RequestMapping(value = "/mypage/mypageMain.do")
	public ModelAndView mypageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/mypage/mypageMain");
		return mav;
	}
	
	//1-1)이용중인 상품 - 이용권 취소하기
	@RequestMapping(value = "/mypage/ticketCancel.do")
	public ModelAndView ticketCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/mypage/ticketCancel");
		return mav;
	}
	
	//1-1)이용중인 상품 - 이용권 환불하기
	@RequestMapping(value = "/mypage/ticketRefund.do")
	public ModelAndView ticketRefund(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/mypage/ticketRefund");
		return mav;
	}
	
	//1-2)찜 목록
	@RequestMapping(value = "/dibs-list")
	public ModelAndView detailDibsList(@RequestParam(value = "section", required = false) String _section, @RequestParam(value = "pageNum", required = false) String _pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int section=Integer.parseInt((_section == null)?"1":_section);  //1섹션
		int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);  //1페이지
		Map<String, Integer> pagingMap=new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		Map dibsMap=mypageService.detailDibsList(pagingMap);  //MypageService의 detailDibsList에서 글 목록을 받아와서 dibsList에 담기
		dibsMap.put("section", section);
		dibsMap.put("pageNum", pageNum);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/dibs-list");
		mav.addObject("dibsMap", dibsMap);
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

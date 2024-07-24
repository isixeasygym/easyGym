package com.isix.easyGym.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.mypage.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


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
/*	@RequestMapping(value = "/dibs-list")
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
	} */
	@Override
	@ResponseBody  // = setAttribute 역할. JSON 등 여러 형태로 뷰에 다시 넘겨줌
	@RequestMapping(value = "/mypage/mypageMain.do", method = RequestMethod.POST)
	public List<DetailDTO> detailDibsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession(false);
		MemberDTO memberDTO=(MemberDTO)session.getAttribute("member");
    	List<DetailDTO> dibsList = mypageService.detailDibsList(memberDTO.getMemberNo());
	    return dibsList;
	}
	
	//1-2)찜 취소
	@Override
	@ResponseBody
	@RequestMapping(value = "/mypage/removeDibs.do", method = RequestMethod.GET)
	public ModelAndView removeDibs(@RequestParam("detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String detailNoStr = request.getParameter("detailNo");
	    System.out.println("Received detailNo: " + detailNoStr); // 디버깅용 로그

	    if (detailNoStr == null || detailNoStr.isEmpty()) {
	        System.out.println("Invalid detailNo: " + detailNoStr); // 디버깅용 로그
	        throw new IllegalArgumentException("Invalid detail number.");
	    }
	    //int detailNo = Integer.parseInt(detailNoStr);
	    HttpSession session = request.getSession(false);
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
	    System.out.println("Member No: " + memberDTO.getMemberNo() + ", Detail No: " + detailNo); // 디버깅용 로그
	    mypageService.removeDibs(memberDTO.getMemberNo(), detailNo);
	    ModelAndView mav=new ModelAndView("redirect:/mypage/mypageMain.do");
		return mav;
	}

	
	//2.포인트&쿠폰
/*	@Override
	@ResponseBody
	@RequestMapping(value = "/mypage/pointsAndCoupons.do", method = RequestMethod.GET)
    public String pointsAndCoupons(@RequestParam("memberNo") int memberNo, Model model) {
        try {
            List<MemberDTO> points = mypageService.getPointsByMemberNo(memberNo);
            List<MemberDTO> coupons = mypageService.getCouponsByMemberNo(memberNo);
            model.addAttribute("pointsList", points);
            model.addAttribute("couponsList", coupons);
        } catch (DataAccessException e) {
            e.printStackTrace(); // 오류 처리
        }
        return "mypage/mypageMain";
    } */
	
	//3.정보수정
	//3-1)비밀번호 체크
	@Override
	@ResponseBody
	@RequestMapping(value = "/mypage/checkPassword.do", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkPassword(@RequestParam("memberNo") int memberNo, @RequestParam("memberPwd") String memberPwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//HttpSession session= request.getSession(false);
		//MemberDTO memberDTO=(MemberDTO)session.getAttribute("member");
		//System.out.println("checkPassword called with memberNo: " + memberNo + " and memberPwd: " + memberPwd);
	    boolean isCorrect = mypageService.checkPassword(memberNo, memberPwd);
	    //System.out.println("Password check result: " + isCorrect);
	    return new ResponseEntity<>(isCorrect, HttpStatus.OK);
    }
	
	//3-2)회원정보 수정
/*	@Override
	@ResponseBody
	@RequestMapping(value = "/mypage/memberUpdate.do", method = RequestMethod.GET)
	public ModelAndView memberUpdate(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		mypageService.memberUpdate(memberDTO);  //업데이트 하기
		ModelAndView mav=new ModelAndView("redirect:/mypage/mypageMain.do");
		return mav;
	} */
	
	
	
	
	
	
	
	
	
	
	
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

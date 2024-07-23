package com.isix.easyGym.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	@ResponseBody  //JSON 형태로 뷰에 다시 넘겨줌
	@RequestMapping(value = "/mypage/mypageMain.do", method = RequestMethod.POST)
	public List<DetailDTO> detailDibsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // 서비스 메서드 호출
		HttpSession session= request.getSession(false);
		MemberDTO memberDTO=(MemberDTO)session.getAttribute("member");
    	List<DetailDTO> dibsList = mypageService.detailDibsList(memberDTO.getMemberNo());
	    return dibsList;
	}
	
	//2.포인트&쿠폰
	@RequestMapping(value = "/pointsAndCoupons.do", method = RequestMethod.GET)
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
    }
	
	//3.정보수정
	//3-1)비밀번호 체크
	@RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
	public String checkPassword(@RequestParam("password") String password, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return password;
		/*MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member != null) {
            String storedPassword = member.getMemberPwd(); // 저장된 해시된 비밀번호
            if (PasswordUtil.checkPassword(password, storedPassword)) {
                // 비밀번호가 맞으면 정보 수정 폼을 보여줍니다.
                return "mypage/updateForm"; // 수정 폼 페이지로 이동
            } else {
                model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
                return "mypage/mypageMain"; // 비밀번호 오류 페이지로 이동
            }
        }
        return "redirect:/login"; // 로그인 페이지로 이동
    }
	
	//3-2)회원정보 수정
	@RequestMapping(value = "/mypage/updateMember.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		mypageService.updateMember(memberDTO);  //업데이트 하기
		ModelAndView mav=new ModelAndView("redirect:/mypage/mypageMain.do");
		return mav;
	}*/
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

	@Override
	public String pointsAndCoupons(int memberNo, Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView updateMember(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}

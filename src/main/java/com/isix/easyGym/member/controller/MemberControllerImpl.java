package com.isix.easyGym.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.member.dto.KakaoDTO;
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
	
	@Autowired
	private KakaoDTO kakaoDTO;
	
	@Override
	@GetMapping("/member/joinSelect.do")
	public ModelAndView joinSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/joinSelect");
		return mav;
	}
	
	// 회원가입 페이지
	@RequestMapping(value = "/member/memJoin.do")
    public ModelAndView showJoinForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("member/memJoin"); 
        return mav;
    }
	
	// 회원가입 기능
	@PostMapping(value = "/member/addMember.do")
	public ModelAndView addMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberService.addMember(memberDTO);
		mav.setViewName("redirect:/member/afterMemJoin.do");
		return mav;
	}
	
	@Override 
	@GetMapping("/member/gymRegister.do")
	public ModelAndView gymRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/gymRegister");
		return mav;
	}	
	
	@RequestMapping(value = "/member/afterMemJoin.do")
    public ModelAndView afterMemJoin() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("member/afterMemJoin"); 
        return mav;
    }
	
	@Override
	@RequestMapping(value = "/member/joinCheck.do")	// 이용 약관 동의
	public ModelAndView joinCheck(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/joinCheck");
		return mav;
	}

	@Override 
	@GetMapping("/member/loginSelect.do")
	public ModelAndView loginSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/loginSelect");
		return mav;
	}	
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

	@GetMapping("/member/loginForm.do")
	public ModelAndView loginForm(@ModelAttribute("member") MemberDTO member,
	                               @RequestParam(value = "action", required = false) String action,
	                               @RequestParam(value = "result", required = false) Integer result, // Integer로 변경
	                               HttpServletRequest req,
	                               HttpServletResponse res) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    
	    // result 값이 0이라면 로그인 폼으로 이동
	    if (result != null && result == 0) {
	        mv.setViewName("redirect:/member/loginForm.do");
	        return mv;
	    }
	    
	    mv.addObject("result", result); // 로그인 실패시 띄우는 메세지 ...
	    mv.setViewName("member/loginForm");
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
			session.setAttribute("sns", 0);
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


	// 아이디 중복체크
	@PostMapping("/member/checkId.do")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(@RequestParam("memberId")String memberId) {
		
		boolean result = true;
		
		if(memberId.trim().isEmpty()) {
			System.out.print("id : " + memberId);
			result = false;
		} else {
			if (memberService.selectId(memberId)) {
				result = false;
			} else {
				result = true;
			}
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
//	@Override
//	@RequestMapping(value = "/member/checkId.do", produces = "application/text;charset=utf8")
//	public ModelAndView checkId(@RequestParam("memberId") String memberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView("jsonView");
//		boolean result = true;
//		System.out.println("id:"+ memberId);
//
//		if(memberId.trim().isEmpty()) {
//			System.out.print("id:"+ memberId);
//			result = false;
//		}else {
//			if(memberService.checkId(memberId) != null) {
//				result = false;
//			}else {
//				result = true;
//			}
//		}
//		mav.addObject("result", result);
//		mav.setStatus(HttpStatus.OK);
//		return mav;
//	}

	@Override
	public ModelAndView loginForm(MemberDTO member, String action, String result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/kakao-login", method = RequestMethod.GET)
	@Override
	public ModelAndView oauth(
	        @RequestParam(value = "code", required = false) String code,
	        @RequestParam(value = "error", required = false) String error,
	        @RequestParam(value = "error_description", required = false) String error_description,
	        @RequestParam(value = "state", required = false) String state,
	        HttpServletRequest request, HttpServletResponse response) throws Exception {

	    HttpSession session = request.getSession();
	    
	    try {
	        String access_token = memberService.getKakaoAccessToken(code);
	        KakaoDTO kakaoDTO = memberService.getKakaoUserInfo(access_token);
	        
	        memberService.kakaoLogin(kakaoDTO);

	        session.setAttribute("member", kakaoDTO);
	        session.setAttribute("isLogOn", true);
	        session.setAttribute("sns", 1);

	    } catch (Exception e) {
	        e.printStackTrace();
	        // 예외 발생 시 오류 페이지로 리다이렉트
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("redirect:/errorPage.do");
	        return mav;
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("redirect:/main.do");
	    return mav;
	}


}
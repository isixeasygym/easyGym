package com.isix.easyGym.detail.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.detail.dao.DetailDAO;
import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;
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
	private static String ARTICLE_IMG_REPO= "C:\\Users\\USER\\Desktop\\isix\\easyGym\\src\\main\\resources\\static\\images\\detail\\reviewImage";
	@Autowired
	private DetailDTO detailDTO;
	
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
	
	@Autowired
	private DetailReviewDTO detailReviewDTO;
	
	@Override
	@GetMapping("/test.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/detail/test");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/detail/detail.do", method = RequestMethod.GET)
	public ModelAndView detailForm(
			@RequestParam("detailNo") int detailNo,
			@RequestParam(value = "memberNo", required = false) String memberNo,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session= request.getSession();
		detailDTO=detailService.viewDetail(detailNo);
		System.out.print(detailNo);
		ModelAndView mav=new ModelAndView();
		List reviewNo = new ArrayList<>();
		reviewNo = detailService.findReviewNo(detailNo); 
		System.out.print(reviewNo);
		if(reviewNo != null ) {
			List<DetailReviewDTO> review = detailService.findReview(detailNo);
			if(review != null) {
				session.setAttribute("getReview", 1);
				mav.addObject("review", review);
				
			}else {
				session.setAttribute("getReview", 0);
				
			}
		}
		mav.addObject("details", detailDTO);
		mav.setViewName("/detail/detail");
		return mav;
	}
	
    
	@Override
	@RequestMapping("/detail/showAll.do")
	public ModelAndView selectAll(
			@RequestParam("detailClassification") String detailClassification,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			List selectAllList = new ArrayList<>();
			selectAllList = detailService.findAll(detailClassification);
			ModelAndView mav = new ModelAndView();
			mav.addObject("allList", selectAllList);
			mav.setViewName("/detail/List");
		return mav;
	}
	
	@Override
	public ModelAndView selectPopular(@RequestParam("detailClassification") String detailClassification,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int detailNum = detailService.findDetailNo(detailClassification);
		int popularRating = detailService.popularThing(detailNum);
		List PopularThing = new ArrayList<>();
		PopularThing = detailService.findPopular(popularRating);
		ModelAndView mav = new ModelAndView();
		mav.addObject("allList", PopularThing);
		mav.setViewName("/detail/List");
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
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteReview(@RequestParam("companyId") String detailNo, @RequestParam("userId") int memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
		String success=null;
		int memberNum=memberService.findmemberNo(Integer.parseInt(memberNo));
		if(memberNum != 0) {
			int buyNo=payformService.buyCheck(memberNum);
			if(buyNo !=0 ) {
				Map<String, String> selectedDelete = new HashMap<String, String>(); 
				selectedDelete.put("detailNo", detailNo);
				selectedDelete.put("detailNo", String.valueOf(memberNum));
				selectedDelete.put("buyNo", String.valueOf(buyNo));
				detailService.deleteReview(selectedDelete);
				success="success";
			}else {
				success="noBuy";
			}
		}else {
			success="noLogin";
		}
		return success;
	}
	@Override
	@ResponseBody
	@RequestMapping(value="/writeReview.do", method = RequestMethod.POST)
	public String writeReview(@RequestParam("companyId") String detailNo, @RequestParam("userId") String memberNo,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "reviewComment", required = false) String reviewComment,
            @RequestParam(value = "reviewRating", required = false) String reviewRating,
            @RequestParam(value = "reviewImageName", required= false) MultipartFile reviewImageName,
            MultipartHttpServletRequest MultipartRequest,
            HttpServletResponse response) throws Exception{
		System.out.print(reviewImageName);
		System.out.print(reviewComment);
		String status= null;
		int memberNum=memberService.findmemberNo(Integer.parseInt(memberNo));
		if(memberNum != 0) {
			int buyNo=payformService.buyCheck(memberNum);
			if(buyNo != 0) {
				MultipartRequest.setCharacterEncoding("utf-8");
				Map<String,Object> reviewImageMap = new HashMap<>();
				System.out.print(reviewImageName);
				reviewImageMap.put("reviewImageName", reviewImageName);
				String reviewImg=fileUpload(reviewImageMap);
				System.out.print(reviewImageName);
				if(reviewImageName != null ) {
					
					Map<String,String> reviewMap=new HashMap<String,String>();
					reviewMap.put("reviewComment", reviewComment);
					reviewMap.put("reviewImgName", reviewImageName);
					reviewMap.put("reviewRating", reviewRating);
					reviewMap.put("memberNo", memberNo);
					reviewMap.put("buyNo", String.valueOf(buyNo));
					reviewMap.put("detailNo", detailNo);
					detailService.writeReview(reviewMap);
					File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + reviewImg); 
					File destDir=new File(ARTICLE_IMG_REPO + "\\" + reviewImg + "\\" + memberNum);
					
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					status="success";
				}else {
					Map<String,String> noImgReviewMap=new HashMap<String,String>();
					noImgReviewMap.put("reviewComment", reviewComment);
					noImgReviewMap.put("reviewRating", reviewRating);
					noImgReviewMap.put("memberNo", memberNo);
					noImgReviewMap.put("buyNo", String.valueOf(buyNo));// int타입이라서 string string 못하는데 변수 타입인데 string object안되나요?
					noImgReviewMap.put("detailNo", detailNo);
					detailService.noImgReview(noImgReviewMap);
					status="success";
				}
			}else {
				status="noBuy";
			}
		}else {
			status="noLogin";
		}
		return status;
	}
	//한 개 이미지 파일 업로드(fileUpload)
	private String fileUpload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String imageFileName=null;
		System.out.print("1123123");
		Iterator<String> fileNames=multipartRequest.getFileNames();
		while(fileNames.hasNext()) {  //fileNames가 존재하면 while문이 hasNext 다음으로 계속 돔
			
			String fileName=fileNames.next();
			MultipartFile mFile=multipartRequest.getFile(fileName);
			imageFileName=mFile.getOriginalFilename();
			File file=new File(ARTICLE_IMG_REPO + "\\" + fileName);  //이미지 파일 저장하는 내 경로 (상단에 경로 있음)
			if(mFile.getSize() != 0) {  //이미지 파일 사이즈가 0이 아닐 때 => 이미지가 첨부되어 있는 상태
				if(! file.exists()) {  //파일이 존재하지 않는다면~
					if(file.getParentFile().mkdir()) {  //mkdir = 폴더 생성
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName));  //transferTo => 파일 전송 / new File => 익명으로 파일 객체 생성 / temp에 임시 저장
			}
		}
		return imageFileName;
	}
}




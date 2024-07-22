package com.isix.easyGym.detail.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.isix.easyGym.detail.dto.DetailImageDTO;
import com.isix.easyGym.detail.dto.DetailReviewDTO;
import com.isix.easyGym.detail.service.DetailServiceImpl;
import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.member.dto.MemberOperDTO;
import com.isix.easyGym.member.service.MemberServiceImpl;
import com.isix.easyGym.payform.dto.PayformDTO;
import com.isix.easyGym.payform.service.PayformServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Controller("detailController")
public class DetailControllerImpl implements DetailController{
	
	private static String ARTICLE_IMG_REPO= "C:\\Users\\USER\\Desktop\\isix\\easyGym\\src\\main\\resources\\static\\images\\detail";
	
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
	
	@Autowired
	private DetailImageDTO detailImageDTO;
	
	@Autowired
	private MemberOperDTO memberOperDTO;
	
	@GetMapping("/detail/registration.do")  //127.0.0.1:8090 => 이렇게만 매핑 보내기
	public ModelAndView registration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/detail/registration");
		return mav;
	}
	
	//사업자 폼 제출 및 이미지 여러개 추가
		@Override
		@RequestMapping(value="/detail/signUpForm.do", method = RequestMethod.POST)
		public ModelAndView signUpForm(@RequestParam("detailBusinessEng") String detailBusinessEng,
				@RequestParam("operatorNo") int operatorNo,@RequestParam("detailClassification") String detailClassification, 
				MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
			String imageFileName=null;
			multipartRequest.setCharacterEncoding("utf-8");
			Map<String, Object> detailMap=new HashMap<String, Object>();
			Enumeration enu=multipartRequest.getParameterNames();
			while(enu.hasMoreElements()) {
				String name=(String)enu.nextElement();
				String value=multipartRequest.getParameter(name);  //name = articleForm.html에서 title, content 등의 매개변수 이름
				detailMap.put(name, value);
			}
			List<String> fileList=multiFileUpload(multipartRequest);  //여러 개의 이미지를 받을거라 리스트로 작성
			List<DetailImageDTO> imageFileList=new ArrayList<DetailImageDTO>();
			if(fileList != null && fileList.size() != 0) {
				for(String fileName:fileList) {
					detailImageDTO.setImageFileName(fileName);
					imageFileList.add(detailImageDTO);
				}
				detailMap.put("imageFileList", imageFileList);
			}
			HttpSession session=multipartRequest.getSession();  //session ~ Map.put(id) => 글쓰기할 때 세션을 통해 id로 접속한 사람의 id가 작성자 칸에 보이게 하기
			//memberOperDTO=(MemberOperDTO)session.getAttribute("memberOper");
			//int operatorNo=memberOperDTO.getOperatorNo(); 
			detailMap.put("operatorNo", operatorNo);
			try {
				detailService.addOperForm(detailMap);  //addArticle을 articleNo에 담아서 boardService에 가져가기???
				if(imageFileList != null && imageFileList.size() != 0) {  //이미지를 첨부했다면 ~
					for(DetailImageDTO detailImageDTO : imageFileList) {
						imageFileName=detailImageDTO.getImageFileName();
						File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);  //File = 객체
						File destDir=new File(ARTICLE_IMG_REPO + "\\" + detailClassification + "\\" + detailBusinessEng);
						FileUtils.moveFileToDirectory(srcFile, destDir, true);
					}
				}
			}catch (Exception e) {
				//글쓰기 수행 중 오류
				if(imageFileList != null && imageFileList.size() != 0) {  //이미지를 첨부했다면 ~
					for(DetailImageDTO detailImageDTO : imageFileList) {
						imageFileName=detailImageDTO.getImageFileName();
						File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);  //File = 객체
						srcFile.delete();
					}
				}
			}
			ModelAndView mav=new ModelAndView("redirect:/board/listArticles.do");  //글 리스트 추가한걸 리스트목록에서 보여주기
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
		List review = new ArrayList<>();
		review = detailService.findReview(detailNo); 
		if(review != null ) {
			session.setAttribute("getReview", 1);
			mav.addObject("review", review);
		}else {
			session.setAttribute("getReview", 0);
		}
		mav.addObject("details", detailDTO);
		mav.setViewName("/detail/detail");
		return mav;
	}
	
    
	@Override
	@RequestMapping(value="/detail/showAll.do", method=RequestMethod.GET)
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
	@ResponseBody
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String deleteReview(@RequestParam("reviewNo") int reviewNo, @RequestParam("userId") int memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception{
		String success=null;
		int memberNum=memberService.findmemberNo(memberNo);
		if(memberNum != 0) {
			int buyNo=payformService.buyCheck(memberNum);
			if(buyNo !=0 ) {
				detailService.removeReview(reviewNo);
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
				//Map<String,Object> reviewImageMap = new HashMap<>();
				//System.out.print(reviewImageName);
				//reviewImageMap.put("reviewImageName", reviewImageName);
				//System.out.print(reviewImageName);
				/*if(reviewImageName != null ) {
					
					?Map<String,String> reviewMap=new HashMap<String,String>();
					reviewMap.put("reviewComment", reviewComment);
					reviewMap.put("reviewImgName", reviewImageName);
					reviewMap.put("reviewRating", reviewRating);
					reviewMap.put("memberNo", memberNo);
					reviewMap.put("buyNo", String.valueOf(buyNo));
					reviewMap.put("detailNo", detailNo);
					String reviewImg=fileUpload(reviewImageMap);
					detailService.writeReview(reviewMap);
					File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + reviewImg); 
					File destDir=new File(ARTICLE_IMG_REPO + "\\" + reviewImg + "\\" + memberNum);
					
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					status="success";
				}else {*/
					Map<String,String> noImgReviewMap=new HashMap<String,String>();
					noImgReviewMap.put("reviewComment", reviewComment);
					noImgReviewMap.put("reviewRating", reviewRating);
					noImgReviewMap.put("memberNo", memberNo);
					noImgReviewMap.put("buyNo", String.valueOf(buyNo));// int타입이라서 string string 못하는데 변수 타입인데 string object안되나요?
					noImgReviewMap.put("detailNo", detailNo);
					detailService.noImgReview(noImgReviewMap);
					status="success";
				
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
	//여러개 이미지 파일 업로드(multiFileUpload) => MySQL - 여러개 이미지 파일 저장 테이블 생성
		private List<String> multiFileUpload(MultipartHttpServletRequest multipartRequest) throws Exception {
			List<String> fileList=new ArrayList<String>();  //List<제너럴타입>
			Iterator<String> fileNames=multipartRequest.getFileNames();
			while(fileNames.hasNext()) {  //fileNames가 존재하면 while문이 hasNext 다음으로 계속 돔
				String fileName=fileNames.next();
				MultipartFile mFile=multipartRequest.getFile(fileName);
				String originalFileName=mFile.getOriginalFilename();  //첨부한 이미지 이름을 가져옴
				fileList.add(originalFileName);  //fileList에 originalFileName 첨부한 이미지 이름을 추가하기
				File file=new File(ARTICLE_IMG_REPO + "\\" + fileName);  //이미지 파일 저장하는 내 경로 (상단에 경로 있음)
				if(mFile.getSize() != 0) {  //이미지 파일 사이즈가 0이 아닐 때 => 이미지가 첨부되어 있는 상태
					if(! file.exists()) {  //파일이 존재하지 않는다면~
						if(file.getParentFile().mkdir()) {  //mkdir = 폴더 생성
							file.createNewFile();
						}
					}
					mFile.transferTo(new File(ARTICLE_IMG_REPO + "\\temp\\" + originalFileName));  //transferTo => 파일 전송 / new File => 익명으로 파일 객체 생성 / temp에 임시 저장
				}
			}
			return fileList;
		}

	

}




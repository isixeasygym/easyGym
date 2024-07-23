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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@Override
	@GetMapping("/detail/search.do")
	public ModelAndView searchData(@RequestParam("query") String query, 
			@RequestParam("detailClassification") String detailClassification,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		List<DetailDTO> selectedThing = new ArrayList<>();
		Map<String, String> searchMap= new HashMap<String, String>();
		searchMap.put("query", query);
		searchMap.put("detailClassification", detailClassification);
		selectedThing = detailService.findThing(searchMap);
		mav.addObject("allList", selectedThing);
		mav.setViewName("/detail/detail");
		return mav;
	}
	
	@RequestMapping(value = "/detail/signUpForm.do", method = RequestMethod.POST)
	public ModelAndView signUpForm(
	        @RequestParam("detailBusinessEng") String detailBusinessEng,
	        @RequestParam("operatorNo") int operatorNo,
	        @RequestParam("detailClassification") String detailClassification,
	        MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

	    String imageFileName = null;
	    multipartRequest.setCharacterEncoding("utf-8");
	    Map<String, Object> detailMap = new HashMap<>();
	    Enumeration<String> enu = multipartRequest.getParameterNames();

	    while (enu.hasMoreElements()) {
	        String name = enu.nextElement();
	        String value = multipartRequest.getParameter(name);
	        detailMap.put(name, value);
	    }

	    List<String> fileList = multiFileUpload(multipartRequest);
	    List<DetailImageDTO> imageFileList = new ArrayList<>();

	    if (fileList != null && !fileList.isEmpty()) {
	        for (String fileName : fileList) {
	            DetailImageDTO detailImageDTO = new DetailImageDTO();
	            detailImageDTO.setImageFileName(fileName);
	            imageFileList.add(detailImageDTO);
	        }
	        detailMap.put("imageFileList", imageFileList);
	    }

	    HttpSession session = multipartRequest.getSession();
	    detailMap.put("operatorNo", operatorNo);

	    try {
	        detailService.addOperForm(detailMap);
	        if (imageFileList != null && !imageFileList.isEmpty()) {
	            for (DetailImageDTO detailImageDTO : imageFileList) {
	                imageFileName = detailImageDTO.getImageFileName();
	                File srcFile = new File(ARTICLE_IMG_REPO + File.separator + detailClassification + File.separator + "temp" + File.separator + imageFileName);
	                File destDir = new File(ARTICLE_IMG_REPO + File.separator + detailClassification + File.separator + detailBusinessEng);

	                if (!destDir.exists()) {
	                    destDir.mkdirs(); // Ensure destination directory exists
	                }
	                
	                File destFile = new File(destDir, imageFileName);
	                FileUtils.moveFile(srcFile, destFile); // Move the file
	            }
	        }
	    } catch (Exception e) {
	        if (imageFileList != null && !imageFileList.isEmpty()) {
	            for (DetailImageDTO detailImageDTO : imageFileList) {
	                imageFileName = detailImageDTO.getImageFileName();
	                File srcFile = new File(ARTICLE_IMG_REPO + File.separator + detailClassification + File.separator + "temp" + File.separator + imageFileName);
	                if (srcFile.exists()) {
	                    srcFile.delete(); // Delete the file if it exists
	                }
	            }
	        }
	        e.printStackTrace(); // Consider proper logging
	    }

	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/main");
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
	@RequestMapping(value="/getReviews.do", method = RequestMethod.GET)
	public ResponseEntity<List<DetailReviewDTO>> getReviews(int detailNo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			List<DetailReviewDTO> reviews = detailService.findReview(detailNo);
	        return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/writeReview.do", method = RequestMethod.POST)
	public String writeReview(
	        @RequestParam("companyId") String detailNo, 
	        @RequestParam("memberNo") String memberNo,
	        @RequestParam(value = "action", required = false) String action,
	        @RequestParam(value = "reviewComment", required = false) String reviewComment,
	        @RequestParam(value = "reviewRating", required = false) String reviewRating,
	        @RequestParam(value = "reviewImageName", required = false) MultipartFile reviewImageName,
	        MultipartHttpServletRequest multipartRequest,
	        HttpServletResponse response) throws Exception {

	    String status = null;
	    String detailBusinessEng = multipartRequest.getParameter("detailBusinessEng");
	    try {
	     
	        int memberNum = memberService.findmemberNo(Integer.parseInt(memberNo));

	        if (memberNum != 0) {
	            int buyNo = payformService.buyCheck(memberNum);

	            if (buyNo != 0) {
	                multipartRequest.setCharacterEncoding("utf-8");

	                String imageFileName = fileUpload(multipartRequest); // Check if image file is uploaded

	                if (imageFileName != null && !imageFileName.isEmpty()) {
	                    // Handle image upload
	                    Map<String, Object> reviewImageMap = new HashMap<>();
	                    Enumeration<String> enu = multipartRequest.getParameterNames();

	                    while (enu.hasMoreElements()) {
	                        String name = enu.nextElement();
	                        String value = multipartRequest.getParameter(name);
	                        reviewImageMap.put(name, value);
	                    }

	                    reviewImageMap.put("reviewImageName", imageFileName);
	                    reviewImageMap.put("buyNo", buyNo);
	                    reviewImageMap.put("detailNo", detailNo);
	                    HttpSession session = multipartRequest.getSession();
	                    reviewImageMap.put("memberNo", memberNo);

	                    int reviewNo = detailService.addreview(reviewImageMap);

	                    File srcFile = new File(ARTICLE_IMG_REPO + File.separator + "reviewImage" + File.separator + "temp" + File.separator + imageFileName);
	                    File destDir = new File(ARTICLE_IMG_REPO + File.separator + "reviewImage" + File.separator + detailBusinessEng + File.separator + memberNo);
	                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
	                    
	                    if (!destDir.exists()) {
	                        destDir.mkdirs(); // Ensure destination directory exists
	                    }

	                    File destFile = new File(destDir, imageFileName);
	                    FileUtils.moveFile(srcFile, destFile); // Move the file

	                    status = "success";
	                } else {
	                    // Handle no image case
	                    Map<String, String> noImgReviewMap = new HashMap<>();
	                    noImgReviewMap.put("reviewComment", reviewComment);
	                    noImgReviewMap.put("reviewRating", reviewRating);
	                    noImgReviewMap.put("memberNo", memberNo);
	                    noImgReviewMap.put("buyNo", String.valueOf(buyNo));
	                    noImgReviewMap.put("detailNo", detailNo);

	                    detailService.noImgReview(noImgReviewMap);
	                    status = "success";
	                }
	            } else {
	                status = "noBuy";
	            }
	        } else {
	            status = "noLogin";
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Consider using proper logging
	        status = "error";
	    }

	    return status;
	}
	
	//한 개 이미지 파일 업로드(fileUpload)
	private String fileUpload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String imageFileName=null;
		Iterator<String> fileNames=multipartRequest.getFileNames();
		while(fileNames.hasNext()) {  //fileNames가 존재하면 while문이 hasNext 다음으로 계속 돔
			String detailBusinessEng = multipartRequest.getParameter("detailBusinessEng");
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
				mFile.transferTo(new File(ARTICLE_IMG_REPO + File.separator + "reviewImage" + File.separator + "temp" + File.separator + imageFileName));  //transferTo => 파일 전송 / new File => 익명으로 파일 객체 생성 / temp에 임시 저장
			}
		}
		return imageFileName;
	}
	private List<String> multiFileUpload(MultipartHttpServletRequest multipartRequest) throws Exception {
	    List<String> fileList = new ArrayList<>();
	    Iterator<String> fileNames = multipartRequest.getFileNames();

	    while (fileNames.hasNext()) {
	        String fileName = fileNames.next();
	        MultipartFile mFile = multipartRequest.getFile(fileName);
	        String detailClassification = multipartRequest.getParameter("detailClassification");
	        String originalFileName = mFile.getOriginalFilename();
	        
	        if (originalFileName != null && mFile.getSize() != 0) {
	            fileList.add(originalFileName);
	            File file = new File(ARTICLE_IMG_REPO + File.separator + detailClassification + File.separator + "temp" + File.separator + originalFileName);
	            if (!file.getParentFile().exists()) {
	                file.getParentFile().mkdirs(); // Ensure directory exists
	            }
	            mFile.transferTo(file); // Transfer file
	        }
	    }
	    return fileList;
	}

}




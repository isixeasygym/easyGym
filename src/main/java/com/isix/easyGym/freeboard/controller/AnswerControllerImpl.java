package com.isix.easyGym.freeboard.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.freeboard.dto.AnswerDTO;
import com.isix.easyGym.freeboard.dto.FreeImageDTO;
import com.isix.easyGym.freeboard.service.AnswerService;
import com.isix.easyGym.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("AnswerController")
public class AnswerControllerImpl implements AnswerController{

	private static String ARTICLE_IMG_REPO ="C:\\kh\\fileupload";
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerDTO answerDTO;
	
	// 댓글 리스트
	@RequestMapping(value= "/freeboard/answerList.do", method =  RequestMethod.GET)
	public ModelAndView answerList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map amap = answerService.answerList(map);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/freeboard/answerList");
		mv.addObject("amap",amap);
		return mv;
	}
	
	// 댓글등록
	@PostMapping("/freeboard/addAnswer.do")
    public ModelAndView addFboard(@RequestParam(value="freeNo", required = false) String freeNo,
                                  @RequestParam(value="memberNo", required = false) String memberNo,
                                  @RequestParam(value="fbanswerContent", required = false) String fbanswerContent,
                                  MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception {
        
        // 디버깅 로그 추가
        System.out.println("freeNo: " + freeNo);
        System.out.println("memberNo: " + memberNo);
        System.out.println("comment: " + fbanswerContent);
        
        String imageFileName = null;
        mulReq.setCharacterEncoding("utf-8");
        Map<String, Object> amap = new HashMap<>();

        amap.put("memberNo", memberNo);
        amap.put("freeNo", freeNo);
        amap.put("fbanswerContent", fbanswerContent);
        int fbanswerNo = answerService.addAnswer(amap);

		/*
		 * List<String> flist = mulFileUpload(mulReq); List<FreeImageDTO> imageFileList
		 * = new ArrayList<>(); if (flist != null && flist.size() != 0) { for (String
		 * fname : flist) { FreeImageDTO fbimageDTO = new FreeImageDTO();
		 * fbimageDTO.setImageFileName(fname); imageFileList.add(fbimageDTO);
		 * System.out.println(fbimageDTO.getImageFileName() + " 이미지 이름 가져옴"); }
		 * amap.put("imageFileList", imageFileList); }
		 */

        HttpSession session = mulReq.getSession();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        String memberId = memberDTO.getMemberId();
        amap.put("memberId", memberId);

		/*
		 * try { if (imageFileList != null && imageFileList.size() != 0) { for
		 * (FreeImageDTO fbimageDTO : imageFileList) { imageFileName =
		 * fbimageDTO.getImageFileName(); System.out.println(imageFileName +
		 * " 여기까지 옴!!!!!!!!!!!!"); File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\"
		 * + imageFileName); File destDir = new File(ARTICLE_IMG_REPO + "\\answer\\" +
		 * fbanswerNo); FileUtils.moveFileToDirectory(srcFile, destDir, true); } } }
		 * catch (Exception e) { System.out.println("예외 발생: " + e.getMessage());
		 * e.printStackTrace(); if (imageFileList != null && imageFileList.size() != 0)
		 * { for (FreeImageDTO fbimageDTO : imageFileList) { imageFileName =
		 * fbimageDTO.getImageFileName(); File srcFile = new File(ARTICLE_IMG_REPO +
		 * "\\temp\\" + imageFileName); srcFile.delete(); } } }
		 */
        ModelAndView mv = new ModelAndView("redirect:/freeboard/viewfboard.do");
        return mv;
    }
	
	// 댓글 수정
	@RequestMapping(value="/freeboard/modAnswer.do")
	public ModelAndView modFboard(MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception {
		String imageFileName = null;
		mulReq.setCharacterEncoding("utf-8");
		Map<String, Object> fbmap = new HashMap<String,Object>();
		Enumeration enu = mulReq.getParameterNames();
		while(enu.hasMoreElements()) { // 매개변수가 있으면 
			String name = (String)enu.nextElement(); // 요소들
			String value = mulReq.getParameter(name); // 매개변수이름으로 가져온 value값
			fbmap.put(name, value);
		}
		List<String> flist = mulFileUpload(mulReq);
		String freeNo = (String)fbmap.get("freeNo");
		List<FreeImageDTO> imageFileList = new ArrayList<FreeImageDTO>(); // 여러개의 이미지를 담을 리스트를 생성
		int modNum = 0;
		if(flist != null && flist.size() !=0) {
			for(String fname : flist) {
				modNum ++;
				FreeImageDTO fbimageDTO = new FreeImageDTO(); // 리스트를 생성할때마다 인스턴스 객체 생성
				fbimageDTO.setImageFileName(fname);
				fbimageDTO.setImageFileNo(Integer.parseInt((String)fbmap.get("imageFileNo" + modNum))); 
				imageFileList.add(fbimageDTO);
			}
			fbmap.put("imageFileList", imageFileList);
		}
		HttpSession session = mulReq.getSession();
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
	    String memberId = memberDTO.getMemberId();
	    fbmap.put("memberId", memberId);
		try {
			answerService.modAnswer(fbmap);
			if(imageFileList != null && imageFileList.size() !=0) {
				int cnt = 0;
				for(FreeImageDTO imageDTO : imageFileList) {
					cnt++;
					imageFileName = imageDTO.getImageFileName();
					if(imageFileName != null && imageFileName != "") {
					File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
					File destDir = new File(ARTICLE_IMG_REPO + "\\answer\\" + freeNo);
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
					String originalFileName = (String)fbmap.get("originalFileName" + cnt);
					System.out.println("이전이미지 : " + originalFileName);
					File oldFile = new File(ARTICLE_IMG_REPO + "\\answer\\" + freeNo + "\\" + originalFileName);
					oldFile.delete();
					}
				}
			}
		}catch (Exception e){
			// 글 쓰기 수행중 오류
//			if(imageFileList != null && imageFileList.size() !=0) {
//				for(ImageDTO imageDTO : imageFileList) {
//					imageFileName = imageDTO.getImageFileName();
//					File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
//					// 오류 발생시 temp폴더에 있는 이미지를 삭제
//					srcFile.delete();
//				}
//			}
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("redirect:/freeboard/answerList.do");
		
		return mv;
	}
	
	
	
	// 댓글 삭제
	@RequestMapping(value="/freeboard/removeAnswer.do")
	public ModelAndView removeFboard(@RequestParam("fbanswerNo") int fbanswerNo, HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println(fbanswerNo + "번호");
		answerService.removeAnswer(fbanswerNo);
		File imgDir = new File(ARTICLE_IMG_REPO + "\\" + fbanswerNo);
		if(imgDir.exists()) { // 폴더가 있으면
			FileUtils.deleteDirectory(imgDir); // 폴더삭제 메소드
		}
		ModelAndView mv = new ModelAndView("redirect:/freeboard/answerList.do");
		return mv;
	}


	
	
	
	// 여러개의 파일 업로드
	private List<String> mulFileUpload(MultipartHttpServletRequest mulReq) throws Exception{
		List<String> flist = new ArrayList<String>();
		Iterator<String> fnames = mulReq.getFileNames();// 열거형 객체
		while(fnames.hasNext()) {
			String fname = fnames.next();
			MultipartFile mfile = mulReq.getFile(fname);
			String orignalFileName = mfile.getOriginalFilename();
			flist.add(orignalFileName);
			File file = new File(ARTICLE_IMG_REPO + "\\answer\\" +fname);
			if(mfile.getSize() != 0) {
				if(! file.exists()) {
					if(file.getParentFile().mkdir()) {
						file.createNewFile();
					}
				}
				mfile.transferTo(new File(ARTICLE_IMG_REPO + "\\temp\\" + orignalFileName));
			}
		}
		return flist;
	}

}

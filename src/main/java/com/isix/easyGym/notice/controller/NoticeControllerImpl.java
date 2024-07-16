package com.isix.easyGym.notice.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.admin.dto.AdminDTO;
import com.isix.easyGym.notice.dto.NoticeDTO;
import com.isix.easyGym.notice.dto.NoticeImageDTO;
import com.isix.easyGym.notice.service.NoticeService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("noticeController")
public class NoticeControllerImpl implements NoticeController{

	private static String ARTICLE_IMG_REPO ="C:\\kh\\fileupload";
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeDTO noticeDTO;
	
	// 공지사항 조회
	@RequestMapping(value = "/admin/noticeList.do", method = RequestMethod.GET)
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List nlist=noticeService.noticeList();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/noticeList");
		mv.addObject("nlist",nlist);
		
		return mv;
	}
	
	// 공지사항 작성폼
	@Override
	@GetMapping("/admin/noticeForm.do")
	public ModelAndView noticeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/noticeForm");
		return mv;
	}
	
	@Override
	@PostMapping("/admin/addNotice.do")
	public ModelAndView addNotice(MultipartHttpServletRequest mulReq, HttpServletResponse response) throws Exception {
		String imageFileName = null;
		mulReq.setCharacterEncoding("utf-8");
		Map<String, Object> noticeMap=new HashMap<String, Object>();
		Enumeration enu=mulReq.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			String value=mulReq.getParameter(name);  //name = articleForm.html에서 title, content 등의 매개변수 이름
			noticeMap.put(name, value);
			System.out.println(noticeMap.get(name));
		}
		List<String> flist=multiFileUpload(mulReq);  //여러 개의 이미지를 받을거라 리스트로 작성
		List<NoticeImageDTO> imageFileList=new ArrayList<NoticeImageDTO>();
		if(flist != null && flist.size() != 0) {
			for(String fName:flist) {
				NoticeImageDTO nImageDTO=new NoticeImageDTO();
				nImageDTO.setImageFileName(fName);
				System.out.println(fName +" 111");
				imageFileList.add(nImageDTO);
			}
			noticeMap.put("imageFileList", imageFileList);
		}
		HttpSession session=mulReq.getSession();  
		AdminDTO adminDTO = (AdminDTO)session.getAttribute("admin"); 
		String adminId = adminDTO.getAdminId();
		noticeMap.put("adminId", adminId);
		try {
			int noticeNo=noticeService.addNotice(noticeMap);  //addArticle을 articleNo에 담아서 boardService에 가져가기???
			System.out.println(noticeNo +"번호");
			if(imageFileList != null && imageFileList.size() != 0) {  
				for(NoticeImageDTO nImageDTO : imageFileList) {
					imageFileName=nImageDTO.getImageFileName();
					File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);  
					File destDir=new File(ARTICLE_IMG_REPO + "\\" + noticeNo);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}
		}catch (Exception e) {
			//글쓰기 수행 중 오류
			if(imageFileList != null && imageFileList.size() != 0) {  
				for(NoticeImageDTO nImageDTO : imageFileList) {
					imageFileName=nImageDTO.getImageFileName();
					File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);  
					srcFile.delete();
				}
			}
		}
		ModelAndView mv=new ModelAndView("redirect:/admin/noticeList.do");  //글 리스트 추가한걸 리스트목록에서 보여주기
		return mv;
	}
		
	//여러 개의 이미지 상세 글보기
	@Override
	@RequestMapping("/admin/viewNotice.do") //상세 글 보기
	public ModelAndView viewNotice(@RequestParam("noticeNo") int noticeNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map noticeMap=noticeService.viewNotice(noticeNo);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/viewNotice");  
		mv.addObject("noticeMap",noticeMap);  
		return mv;
	}
	//여러 개의 이미지 글 수정하기
	@Override
	@PostMapping("/admin/modNotice.do")
	public ModelAndView modNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		String imageFileName=null;
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> noticeMap=new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);  //name = articleForm.html에서 title, content 등의 매개변수 이름
			noticeMap.put(name, value);
			System.out.println(noticeMap.get(name));// map으로 가져오는것 확인하기
		}
		List<String> fileList=multiFileUpload(multipartRequest);  //여러 개의 이미지를 받을거라 리스트로 작성
		String noticeNo=(String)noticeMap.get("noticeNo");
		List<NoticeImageDTO> imageFileList=new ArrayList<NoticeImageDTO>();
		int modifyNumber=0;  //수정 넘버라는 변수를 만듦 => viewArticle_multi에서 status.count가 있는데 여기는 count가 없어서 아래 for문에서 파일 이름이 돌 때 이름 옆에 숫자를 넣으려고 ~
		if(fileList != null && fileList.size() != 0) {
			for(String fileName:fileList) {
				modifyNumber++;
				NoticeImageDTO imageDTO=new NoticeImageDTO();
				imageDTO.setImageFileName(fileName);
				imageDTO.setImageFileNo(Integer.parseInt((String)noticeMap.get("imageFileNo"+modifyNumber)));
				imageFileList.add(imageDTO);
			}
			noticeMap.put("imageFileList", imageFileList);
		}
		try {
			noticeService.modNotice(noticeMap);  //addArticle을 articleNo에 담아서 boardService에 가져가기???
			if(imageFileList != null && imageFileList.size() != 0) {  //이미지를 첨부했다면 ~
				int cnt=0;
				for(NoticeImageDTO imageDTO : imageFileList) {
					cnt++;
					imageFileName=imageDTO.getImageFileName();
					if(imageFileName != null && imageFileName != "") {
						File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);  
						File destDir=new File(ARTICLE_IMG_REPO + "\\" + noticeNo);
						FileUtils.moveFileToDirectory(srcFile, destDir, true);
						String originalFileName=(String)noticeMap.get("originalFileName" + cnt);
						System.out.println("이전 이미지 : " + originalFileName);
						File oldFile=new File(ARTICLE_IMG_REPO + "\\" + noticeNo + "\\" + originalFileName);  
						oldFile.delete(); 
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView("redirect:/admin/noticeList.do");  
		return mav;
	}

	@Override
	@PostMapping("/admin/removeNotice.do")
	public ModelAndView removeNotice(@RequestParam("noticeNo") int noticeNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		noticeService.removeNotice(noticeNo);
		File imgDir=new File(ARTICLE_IMG_REPO + "\\" + noticeNo);  //객체 정보를 파일 형태로 만듦
		if(imgDir.exists()) { 
			FileUtils.deleteDirectory(imgDir);  
		}
		ModelAndView mv=new ModelAndView("redirect:/admin/noticeList.do");  //글 삭제 후 리스트목록 보여주기
		return mv;
	}
		
	//여러개 이미지 파일 업로드(multiFileUpload) => MySQL - 여러개 이미지 파일 저장 테이블 생성
	private List<String> multiFileUpload(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> fileList=new ArrayList<String>();  //List<제너럴타입>
		Iterator<String> fileNames=multipartRequest.getFileNames();
		while(fileNames.hasNext()) {  //fileNames가 존재하면 while문이 hasNext 다음으로 계속 돔
			System.out.println( "@@@@@@@@파일이름");
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

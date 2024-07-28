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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.freeboard.dao.FreeDAO;
import com.isix.easyGym.freeboard.dto.FreeDTO;
import com.isix.easyGym.freeboard.dto.FreeImageDTO;
import com.isix.easyGym.freeboard.service.AnswerService;
import com.isix.easyGym.freeboard.service.FreeBoardService;
import com.isix.easyGym.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("FreeBoardController")
public class FreeboardControllerImpl implements FreeBoardController {
private static String ARTICLE_IMG_REPO ="C:\\kh\\fileupload";
	
	@Autowired
	private FreeBoardService freeboardservice;
	private AnswerService answerService;
	
	@Autowired
	private FreeDAO freeDAO;
	
	@Autowired
	private FreeDTO freeDTO;

	@RequestMapping(value="/freeboard/fboardList.do")  // 페이징처리 required의 기본값 true  value에 값이 없으면 null을 반환 
	public ModelAndView listFboard(@RequestParam(value ="section" , required = false) String _section, @RequestParam(value = "pageNum" ,  required = false) String _pageNum,  HttpServletRequest req, HttpServletResponse res) throws Exception {
		int section = Integer.parseInt((_section == null) ? "1" : _section);
		int pageNum = Integer.parseInt((_pageNum == null) ? "1" : _pageNum);
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		//List<ArticleDTO> alist = boardService.listArticles(); 기존 페이징 처리 하기 전 
		Map fbmap = freeboardservice.listFboard(pagingMap); // 기존 list 와 page, section을 가져오기 위해 map을 사용  
		fbmap.put("section", section);
		fbmap.put("pageNum", pageNum);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("freeboard/fboardList");
		mv.addObject("fbmap",fbmap);
		return mv;
	}
	
	
	@Override
	@GetMapping("/freeboard/fboardForm.do")
	public ModelAndView fboardForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("freeboard/fboardForm");
		return mv;
	}
	
	// ----------------------------------------------------------------
	
	// 글쓰기에 여러 개의 이미지 추가 
	
	@PostMapping("/freeboard/addFreeboard.do")
	public ModelAndView addFboard(MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception {
	    String imageFileName = null;
	    mulReq.setCharacterEncoding("utf-8");
	    Map<String, Object> fbmap = new HashMap<String, Object>();
	    Enumeration enu = mulReq.getParameterNames();
	    while (enu.hasMoreElements()) {
	        String name = (String) enu.nextElement();
	        String value = mulReq.getParameter(name);
	        fbmap.put(name, value);
	        System.out.println(fbmap.get(name));
	    }

	    List<String> flist = mulFileUpload(mulReq);
	    List<FreeImageDTO> imageFileList = new ArrayList<FreeImageDTO>();
	    if (flist != null && flist.size() != 0) {
	        for (String fname : flist) {
	            FreeImageDTO fbimageDTO = new FreeImageDTO();
	            fbimageDTO.setImageFileName(fname);
	            imageFileList.add(fbimageDTO);
	            System.out.println(fbimageDTO.getImageFileName() + " 이미지 이름 가져옴");
	        }
	        fbmap.put("imageFileList", imageFileList);
	    }

	    HttpSession session = mulReq.getSession();
	    MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
	    String memberId = memberDTO.getMemberId();
	    fbmap.put("memberId", memberId);

	    try {
	        int freeNo = freeboardservice.addFboard(fbmap);
	        if (imageFileList != null && imageFileList.size() != 0) {
	            for (FreeImageDTO fbimageDTO : imageFileList) {
	                imageFileName = fbimageDTO.getImageFileName();
	                System.out.println(imageFileName + " 여기까지 옴!!!!!!!!!!!!");
	                File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
	                File destDir = new File(ARTICLE_IMG_REPO + "\\" + freeNo);
	                FileUtils.moveFileToDirectory(srcFile, destDir, true);
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("예외 발생: " + e.getMessage());
	        e.printStackTrace();
	        if (imageFileList != null && imageFileList.size() != 0) {
	            for (FreeImageDTO fbimageDTO : imageFileList) {
	                imageFileName = fbimageDTO.getImageFileName();
	                File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
	                srcFile.delete();
	            }
	        }
	    }
	    ModelAndView mv = new ModelAndView("redirect:/freeboard/fboardList.do");
	    return mv;
	}
	
	
	
	
	// ----------------------------------------------------------------
	
	// 한개의 이미지 상세보기
//	@Override
//	@RequestMapping(value="/board/viewArticle.do")
//	public ModelAndView viewArticle(@RequestParam("articleNo") int articleNo, HttpServletRequest req, HttpServletResponse res) throws Exception {
//		articleDTO = boardService.viewArticle(articleNo);
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/board/viewArticle");
//		mv.addObject("articleDTO",articleDTO);
//		return mv;
//	}

	// 여러개의 이미지 상세보기
	@RequestMapping(value="/freeboard/viewfboard.do")
	public ModelAndView viewFboard(@RequestParam("freeNo") int freeNo, HttpServletRequest req, HttpServletResponse res) throws Exception {
		//articleDTO = boardService.viewArticle(articleNo);
		Map fbmap = freeboardservice.viewFboard(freeNo); // 두개의 테이블을 거쳐야 하기 때문에 map으로 변경
		List answer = new ArrayList<>();
		HttpSession session= req.getSession();
		ModelAndView mv = new ModelAndView();
		answer = freeDAO.selectAnswer(freeNo); 
	      if(answer != null ) {
	         session.setAttribute("getAnswer", 1);
	         mv.addObject("answer", answer);
	      }else {
	         session.setAttribute("getAnswer", 0);
	      }
		mv.setViewName("freeboard/viewfboard");
		mv.addObject("fbmap",fbmap);
		return mv;
	}
	
	
	// 한개의 이미지 수정
	/*@Override
	@RequestMapping(value="/board/modArticle.do")
	public ModelAndView modArticle(MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception {
		mulReq.setCharacterEncoding("utf-8");
		Map<String, Object> amap = new HashMap<String,Object>();
		Enumeration enu = mulReq.getParameterNames();
		while(enu.hasMoreElements()) { // 매개변수가 있으면 
			String name = (String)enu.nextElement(); // 요소들
			String value = mulReq.getParameter(name); // 매개변수이름으로 가져온 value값
			amap.put(name, value);
		}
		String imageFileName = fileUpload(mulReq);
		String title = (String)amap.get("title");
		String content = (String)amap.get("content");
		String articleNo = (String)amap.get("articleNo"); // 수정할 게시글의 번호를 받아와야 한다.
		String originalFileName = (String)amap.get("originalFileName");
		articleDTO.setTitle(title);
		articleDTO.setContent(content);
		articleDTO.setImageFileName(imageFileName);
		articleDTO.setArticleNo(Integer.parseInt(articleNo));
		articleDTO.setId("chulsu"); // 임의의 아이디 사용 / session의 아이디를 사용
		boardService.modArticle(articleDTO);
		if(imageFileName != null && imageFileName.length() !=0) {
			File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
			File destDir = new File(ARTICLE_IMG_REPO + "\\" + articleNo);
			FileUtils.moveFileToDirectory(srcFile, destDir,true);
			File oldFile = new File(ARTICLE_IMG_REPO + "\\" + articleNo + "\\" + originalFileName);
			oldFile.delete();
		}
		ModelAndView mv = new ModelAndView("redirect:/board/viewArticle.do?articleNo=" + articleNo);
		
		return mv;
	}*/
	
	// 여러개의 이미지 수정
	
	@RequestMapping(value="/freeboard/modFboard.do")
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
		fbmap.put("id", "chulsu"); // 임시 아이디
		try {
			freeboardservice.modFboard(fbmap);
			if(imageFileList != null && imageFileList.size() !=0) {
				int cnt = 0;
				for(FreeImageDTO imageDTO : imageFileList) {
					cnt++;
					imageFileName = imageDTO.getImageFileName();
					if(imageFileName != null && imageFileName != "") {
					File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
					File destDir = new File(ARTICLE_IMG_REPO + "\\" + freeNo);
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
					String originalFileName = (String)fbmap.get("originalFileName" + cnt);
					System.out.println("이전이미지 : " + originalFileName);
					File oldFile = new File(ARTICLE_IMG_REPO + "\\" + freeNo + "\\" + originalFileName);
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
		ModelAndView mv = new ModelAndView("redirect:/freeboard/fboardList.do");
		
		return mv;
	}
	
	
	
	// 한개의 이미지 / 여러개 이미지 삭제 ( ON DELETE CASCADE 한다 )
	@RequestMapping(value="/freeboard/removeFboard.do")
	public ModelAndView removeFboard(@RequestParam("freeNo") int freeNo, HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println(freeNo + "번호");
		freeboardservice.removeFboard(freeNo);
		File imgDir = new File(ARTICLE_IMG_REPO + "\\" + freeNo);
		if(imgDir.exists()) { // 폴더가 있으면
			FileUtils.deleteDirectory(imgDir); // 폴더삭제 메소드
		}
		ModelAndView mv = new ModelAndView("redirect:/freeboard/fboardList.do");
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
			File file = new File(ARTICLE_IMG_REPO + "\\" +fname);
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
	
	// 단일 파일 업로드
	private String fileUpload(MultipartHttpServletRequest mulReq) throws Exception{
		String imageFileName = null;
		Iterator<String> fnames = mulReq.getFileNames();// 열거형 객체
		while(fnames.hasNext()) {
			String fname = fnames.next();
			MultipartFile mfile = mulReq.getFile(fname);
			imageFileName = mfile.getOriginalFilename();
			File file = new File(ARTICLE_IMG_REPO + "\\" +fname);
			if(mfile.getSize() != 0) {
				if(! file.exists()) {
					if(file.getParentFile().mkdir()) {
						file.createNewFile();
					}
				}
				mfile.transferTo(new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName));
			}
		}
		return imageFileName;
	}
}

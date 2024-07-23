package com.isix.easyGym.freeboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isix.easyGym.freeboard.dao.FreeDAO;
import com.isix.easyGym.freeboard.dto.AnswerDTO;
import com.isix.easyGym.freeboard.service.AnswerService;
import com.isix.easyGym.member.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller("AnswerController")
public class AnswerControllerImpl implements AnswerController{

	private static String ARTICLE_IMG_REPO ="C:\\kh\\fileupload";
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerDTO answerDTO;
	
	@Autowired
	private FreeDAO freeDAO;
	
//	// 댓글 리스트
//	@RequestMapping(value= "/freeboard/answerList.do", method =  RequestMethod.GET)
//	public ModelAndView answerList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		Map amap = answerService.answerList(map);
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/freeboard/answerList");
//		mv.addObject("amap",amap);
//		return mv;
//	}
//	
//	// 댓글등록
//	@PostMapping("/freeboard/addAnswer.do")
//    public ModelAndView addFboard(@RequestParam(value="freeNo", required = false) String freeNo,
//                                  @RequestParam(value="memberNo", required = false) String memberNo,
//                                  @RequestParam(value="fbanswerContent", required = false) String fbanswerContent,
//                                  MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception {
//        
//        // 디버깅 로그 추가
//        System.out.println("freeNo: " + freeNo);
//        System.out.println("memberNo: " + memberNo);
//        System.out.println("comment: " + fbanswerContent);
//        
//        String imageFileName = null;
//        mulReq.setCharacterEncoding("utf-8");
//        Map<String, Object> amap = new HashMap<>();
//
//        amap.put("memberNo", memberNo);
//        amap.put("freeNo", freeNo);
//        amap.put("fbanswerContent", fbanswerContent);
//        int fbanswerNo = answerService.addAnswer(amap);
//
//		/*
//		 * List<String> flist = mulFileUpload(mulReq); List<FreeImageDTO> imageFileList
//		 * = new ArrayList<>(); if (flist != null && flist.size() != 0) { for (String
//		 * fname : flist) { FreeImageDTO fbimageDTO = new FreeImageDTO();
//		 * fbimageDTO.setImageFileName(fname); imageFileList.add(fbimageDTO);
//		 * System.out.println(fbimageDTO.getImageFileName() + " 이미지 이름 가져옴"); }
//		 * amap.put("imageFileList", imageFileList); }
//		 */
//
//        HttpSession session = mulReq.getSession();
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        String memberId = memberDTO.getMemberId();
//        amap.put("memberId", memberId);
//
//		/*
//		 * try { if (imageFileList != null && imageFileList.size() != 0) { for
//		 * (FreeImageDTO fbimageDTO : imageFileList) { imageFileName =
//		 * fbimageDTO.getImageFileName(); System.out.println(imageFileName +
//		 * " 여기까지 옴!!!!!!!!!!!!"); File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\"
//		 * + imageFileName); File destDir = new File(ARTICLE_IMG_REPO + "\\answer\\" +
//		 * fbanswerNo); FileUtils.moveFileToDirectory(srcFile, destDir, true); } } }
//		 * catch (Exception e) { System.out.println("예외 발생: " + e.getMessage());
//		 * e.printStackTrace(); if (imageFileList != null && imageFileList.size() != 0)
//		 * { for (FreeImageDTO fbimageDTO : imageFileList) { imageFileName =
//		 * fbimageDTO.getImageFileName(); File srcFile = new File(ARTICLE_IMG_REPO +
//		 * "\\temp\\" + imageFileName); srcFile.delete(); } } }
//		 */
//        ModelAndView mv = new ModelAndView("redirect:/freeboard/viewfboard.do?freeNo=freeNo");
//        return mv;
//    }
//	
//	
//	
//	
//	// 댓글 삭제
//	@RequestMapping(value="/freeboard/removeAnswer.do")
//	public ModelAndView removeFboard(@RequestParam("fbanswerNo") int fbanswerNo, HttpServletRequest req, HttpServletResponse res) throws Exception {
//		System.out.println(fbanswerNo + "번호");
//		answerService.removeAnswer(fbanswerNo);
//		File imgDir = new File(ARTICLE_IMG_REPO + "\\" + fbanswerNo);
//		if(imgDir.exists()) { // 폴더가 있으면
//			FileUtils.deleteDirectory(imgDir); // 폴더삭제 메소드
//		}
//		ModelAndView mv = new ModelAndView("redirect:/freeboard/answerList.do");
//		return mv;
//	}
//
//
//	
//	
//	
//	// 여러개의 파일 업로드
//	private List<String> mulFileUpload(MultipartHttpServletRequest mulReq) throws Exception{
//		List<String> flist = new ArrayList<String>();
//		Iterator<String> fnames = mulReq.getFileNames();// 열거형 객체
//		while(fnames.hasNext()) {
//			String fname = fnames.next();
//			MultipartFile mfile = mulReq.getFile(fname);
//			String orignalFileName = mfile.getOriginalFilename();
//			flist.add(orignalFileName);
//			File file = new File(ARTICLE_IMG_REPO + "\\answer\\" +fname);
//			if(mfile.getSize() != 0) {
//				if(! file.exists()) {
//					if(file.getParentFile().mkdir()) {
//						file.createNewFile();
//					}
//				}
//				mfile.transferTo(new File(ARTICLE_IMG_REPO + "\\temp\\" + orignalFileName));
//			}
//		}
//		return flist;
//	}
	
	
	
	// 댓글 리스트
	@RequestMapping(value = "/freeboard/getAnswerListAjax.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAnswerList(@RequestParam("freeNo") int freeNo) throws Exception {
	    Map<String, Object> map = new HashMap<>();
	    map.put("freeNo", freeNo);
	    Map<String, Object> amap = answerService.answerList(map);

	    Map<String, Object> result = new HashMap<>();
	    result.put("status", "success");
	    result.put("alist", amap.get("alist"));
	    return result;
	}

    // 댓글 등록
    @PostMapping("/freeboard/addAnswerAjax.do")
    @ResponseBody
    public Map<String, Object> addAnswerAjax(@RequestParam(value="freeNo", required = false) Integer freeNo,
                                             @RequestParam(value="memberNo", required = false) Integer memberNo,
                                             @RequestParam(value="fbanswerContent", required = false) String fbanswerContent) throws Exception {
        Map<String, Object> result = new HashMap<>();

        try {
            Map<String, Object> amap = new HashMap<>();
            amap.put("memberNo", memberNo);
            amap.put("freeNo", freeNo);
            amap.put("fbanswerContent", fbanswerContent);

            int fbanswerNo = answerService.addAnswer(amap);
            result.put("status", "success");
            result.put("message", "댓글이 등록되었습니다.");

            // 댓글 정보를 가져옵니다.
            AnswerDTO newAnswer = answerService.getAnswerByNo(fbanswerNo);
            result.put("newAnswer", newAnswer);

        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }

        return result;
    }

    // 댓글 삭제
    @RequestMapping(value = "/freeboard/removeAnswerAjax.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeAnswerAjax(@RequestParam("fbanswerNo") int fbanswerNo, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        // 현재 로그인한 사용자 정보 가져오기
        MemberDTO currentUser = (MemberDTO) session.getAttribute("member");
        
        if (currentUser == null) {
            result.put("status", "fail");
            result.put("message", "로그인이 필요합니다.");
            return result;
        }
        
        try {
            // 댓글 작성자 정보 가져오기
            AnswerDTO answer = freeDAO.getAnswerById(fbanswerNo);
            
            if (answer == null) {
                result.put("status", "fail");
                result.put("message", "존재하지 않는 댓글입니다.");
                return result;
            }
            
            // 댓글 작성자와 현재 사용자 비교
            if (answer.getMemberNo() != currentUser.getMemberNo()) {
                result.put("status", "fail");
                result.put("message", "본인 댓글만 삭제할 수 있습니다.");
                return result;
            }
            
            // 댓글 삭제
            freeDAO.deleteAnswer(fbanswerNo);
            
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "fail");
            result.put("message", "댓글 삭제 중 오류 발생: " + e.getMessage());
        }
        
        return result;
    }


	
	
	
	
	

}

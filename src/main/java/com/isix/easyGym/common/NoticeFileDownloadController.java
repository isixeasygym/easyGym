package com.isix.easyGym.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller("nofileDownload")
public class NoticeFileDownloadController extends HttpServlet {
	private static String ARTICLE_IMG_REPO="C:\\kh\\fileupload";

	@GetMapping("/nodownload.do")
	public void fileDown(@RequestParam("noticeNo") int noticeNo, @RequestParam("imageFileName") String imageFileName,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//읽어오는 것 input / 읽어온것을 클라이언트에 전달하는 것 output
		OutputStream outs = response.getOutputStream();
		String path = ARTICLE_IMG_REPO + "\\" + noticeNo + "\\" + imageFileName;
		File imageFile = new File(path);
		response.setHeader("Cache-Control", "no-cache");
		// 이미지 파일을 다운받는데 필요한 response헤더 정보 설정 
		 String encodedFilename = URLEncoder.encode(imageFileName, "utf-8").replaceAll("\\+", "%20");
	        response.addHeader("Content-disposition", "attachment; fileName=\"" + encodedFilename + "\"");

	        try (FileInputStream fis = new FileInputStream(imageFile)) {
	            byte[] buffer = new byte[1024 * 8];
	            int count;
	            while ((count = fis.read(buffer)) != -1) {
	            	outs.write(buffer, 0, count);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while processing the file");
	        } finally {
	        	outs.close();
	        }
	}
}

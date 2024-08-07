package com.isix.easyGym.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller("fileDownload")
public class FileDownloadController extends HttpServlet {
	private static String ARTICLE_IMG_REPO="C:\\kh\\fileupload";

	@GetMapping("/download.do")
	public void fileDown(@RequestParam("noticeNo") int noticeNo, @RequestParam("imageFileName") String imageFileName,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//읽어오는 것 input / 읽어온것을 클라이언트에 전달하는 것 output
		OutputStream outs = response.getOutputStream();
		String path = ARTICLE_IMG_REPO + "\\" + noticeNo + "\\" + imageFileName;
		File imageFile = new File(path);
		response.setHeader("Cache-Control", "no-cache");
		// 이미지 파일을 다운받는데 필요한 response헤더 정보 설정 
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		FileInputStream inputs = new FileInputStream(imageFile);
		// 이미지를 한번에 클라이언트에게 보낼 수 없어서 buffer에 저장후 보낸다.
		byte[] buffer = new byte[1024*8]; // 버퍼를 이용해서 한번에 8kbyte씩 전송
		while(true) {
			int count = inputs.read(buffer);
			if(count == -1) break;
			outs.write(buffer,0,count);
		}
		inputs.close();
		outs.close();
	}
}

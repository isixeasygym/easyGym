package com.isix.easyGym.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;

@Controller("detailFileDownloadController")
public class DetailFileDownloadController extends HttpServlet {
	
	 	@ResponseBody
	 	@GetMapping("/detailDownload.do")
	    public void fileDown(@RequestParam("detailNo") String detailNo,
	                         @RequestParam("memberNo") String memberNo,
	                         @RequestParam("imageFileName") String imageFileName,
	                         HttpServletRequest request, HttpServletResponse response) throws IOException {
	        
	        // 이미지 파일 경로를 구성합니다
	        String filePath = "/path/to/your/image/directory/" + detailNo + "/" + memberNo + "/" + imageFileName;
	        File file = new File(filePath);
	        System.out.print(memberNo);
	        if (file.exists()) {
	            // Set the content type and headers for the image
	            response.setContentType("image/png");
	            response.setHeader("Content-Disposition", "inline; filename=\"" + imageFileName + "\"");

	            try (OutputStream out = response.getOutputStream()) {
	                // Create thumbnail and write to output stream
	                Thumbnails.of(file)
	                        .size(100, 100) // Resize to 100x100 pixels
	                        .outputFormat("png") // Output format
	                        .toOutputStream(out);
	                out.flush();
	            }
	        } else {
	            // Send a 404 Not Found response if the file does not exist
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
	        }
	    }
	}


package com.isix.easyGym.notice.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;
@Component("noticeImageDTO")
public class NoticeImageDTO {
	
	private int imageFileNo;
	private String imageFileName;
	private Date regDate;
	private int noticeNo;
	
	public int getImageFileNo() {
		return imageFileNo;
	}
	public void setImageFileNo(int imageFileNo) {
		this.imageFileNo = imageFileNo;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	
		
}
package com.isix.easyGym.notice.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("noticeDTO")
public class NoticeDTO {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeWriteDate;
	private int noticeCategory;
	
	public NoticeDTO() {
		
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeWriteDate() {
		return noticeWriteDate;
	}

	public void setNoticeWriteDate(Date noticeWriteDate) {
		this.noticeWriteDate = noticeWriteDate;
	}

	public int getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(int noticeCategory) {
		this.noticeCategory = noticeCategory;
	}
	
	

}

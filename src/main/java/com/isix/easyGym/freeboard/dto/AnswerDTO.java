package com.isix.easyGym.freeboard.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("answerDTO")
public class AnswerDTO {

	private int fbanswerNo;
	private int memberNo;
	private int freeNo;
	private String fbanswerContent;
	private Date fbanswerWriteDate;
	private String imageFileName;
	
	public AnswerDTO() {

	}

	public int getFbanswerNo() {
		return fbanswerNo;
	}

	public void setFbanswerNo(int fbanswerNo) {
		this.fbanswerNo = fbanswerNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getFreeNo() {
		return freeNo;
	}

	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}

	public String getFbanswerContent() {
		return fbanswerContent;
	}

	public void setFbanswerContent(String fbanswerContent) {
		this.fbanswerContent = fbanswerContent;
	}

	public Date getFbanswerWriteDate() {
		return fbanswerWriteDate;
	}

	public void setFbanswerWriteDate(Date fbanswerWriteDate) {
		this.fbanswerWriteDate = fbanswerWriteDate;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	
	
}

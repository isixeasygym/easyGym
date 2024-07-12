package com.isix.easyGym.freeboard.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("freeboardarticleDTO")
public class FreeboardArticleDTO {
	
	private int freePostNo;
	private String freeTitle;
	private String freeContent;
	private int memberNo;
	private Date freeCreatedAt;
	private int freeViewCount;
	private String freeFileName;
	private int fbanswerNo;
	
	public FreeboardArticleDTO() {
		
	}

	public int getFreePostNo() {
		return freePostNo;
	}

	public void setFreePostNo(int freePostNo) {
		this.freePostNo = freePostNo;
	}

	public String getFreeTitle() {
		return freeTitle;
	}

	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}

	public String getFreeContent() {
		return freeContent;
	}

	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public Date getFreeCreatedAt() {
		return freeCreatedAt;
	}

	public void setFreeCreatedAt(Date freeCreatedAt) {
		this.freeCreatedAt = freeCreatedAt;
	}

	public int getFreeViewCount() {
		return freeViewCount;
	}

	public void setFreeViewCount(int freeViewCount) {
		this.freeViewCount = freeViewCount;
	}

	public String getFreeFileName() {
		return freeFileName;
	}

	public void setFreeFileName(String freeFileName) {
		this.freeFileName = freeFileName;
	}

	public int getFbanswerNo() {
		return fbanswerNo;
	}

	public void setFbanswerNo(int fbanswerNo) {
		this.fbanswerNo = fbanswerNo;
	}
	
	

}

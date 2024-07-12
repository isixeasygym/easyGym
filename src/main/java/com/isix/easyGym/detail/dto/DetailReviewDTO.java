package com.isix.easyGym.detail.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("detailreviewDTO")
public class DetailReviewDTO {
	
	private int reviewNo;
	private String reviewContent;
	private String reviewImgName;
	private Date reviewDate;
	private int memberNo;
	
	public DetailReviewDTO() {
		
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewImgName() {
		return reviewImgName;
	}

	public void setReviewImgName(String reviewImgName) {
		this.reviewImgName = reviewImgName;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	

}

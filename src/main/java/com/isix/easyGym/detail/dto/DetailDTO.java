package com.isix.easyGym.detail.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("detailDTO")
public class DetailDTO {
	
	private int detailNo;
	private String detailBusinessName;
	private String detailAddress;
	private Date detailDate;
	private String detailScope;
	private String detailBaseImage;
	private String detailImage;
	private String detailServiceProgram;
	private String detailWhole;
	private int reviewNo;

	public DetailDTO() {
		
	}

	public int getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}

	public String getDetailBusinessName() {
		return detailBusinessName;
	}

	public void setDetailBusinessName(String detailBusinessName) {
		this.detailBusinessName = detailBusinessName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Date getDetailDate() {
		return detailDate;
	}

	public void setDetailDate(Date detailDate) {
		this.detailDate = detailDate;
	}

	public String getDetailScope() {
		return detailScope;
	}

	public void setDetailScope(String detailScope) {
		this.detailScope = detailScope;
	}

	public String getDetailBaseImage() {
		return detailBaseImage;
	}

	public void setDetailBaseImage(String detailBaseImage) {
		this.detailBaseImage = detailBaseImage;
	}

	public String getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
	}

	public String getDetailServiceProgram() {
		return detailServiceProgram;
	}

	public void setDetailServiceProgram(String detailServiceProgram) {
		this.detailServiceProgram = detailServiceProgram;
	}

	public String getDetailWhole() {
		return detailWhole;
	}

	public void setDetailWhole(String detailWhole) {
		this.detailWhole = detailWhole;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	
	
}

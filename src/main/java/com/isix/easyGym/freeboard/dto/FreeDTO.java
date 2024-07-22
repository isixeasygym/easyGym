package com.isix.easyGym.freeboard.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("freeDTO")
public class FreeDTO {
    private int freeNo;
    private String freeTitle;
    private String freeContent;
    private Date freeWriteDate;
    private int freeHit;
    private String imageFileName;
    private int rating;
    private int memberNo;
    
    public FreeDTO() {

    }
    
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
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
	public Date getFreeWriteDate() {
		return freeWriteDate;
	}
	public void setFreeWriteDate(Date freeWriteDate) {
		this.freeWriteDate = freeWriteDate;
	}
	public int getFreeHit() {
		return freeHit;
	}
	public void setFreeHit(int freeHit) {
		this.freeHit = freeHit;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

   
}
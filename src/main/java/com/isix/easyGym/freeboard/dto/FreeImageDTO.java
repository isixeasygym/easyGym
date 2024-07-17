package com.isix.easyGym.freeboard.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("freeImageDTO")
public class FreeImageDTO {
	
	private int imageFileNo;
	private String imageFileName;
	private Date regdate;
	private int freeNo;

	public FreeImageDTO() {

	}

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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getFreeNo() {
		return freeNo;
	}

	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	
	
}

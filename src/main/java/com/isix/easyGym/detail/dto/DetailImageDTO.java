package com.isix.easyGym.detail.dto;

import java.sql.Date;

public class DetailImageDTO {
	private int imageFileNo;
	private String imageFileName;
	private Date regDate;
	private int detailNo;
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
	public int getDetailNo() {
		return detailNo;
	}
	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}
}

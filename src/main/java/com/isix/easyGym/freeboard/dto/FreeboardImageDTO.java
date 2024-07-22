package com.isix.easyGym.freeboard.dto;

import org.springframework.stereotype.Component;

@Component("freeboardimageDTO")
public class FreeboardImageDTO {
	
	private int imageNo;
	
	public FreeboardImageDTO() {
		
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}
	
	

}

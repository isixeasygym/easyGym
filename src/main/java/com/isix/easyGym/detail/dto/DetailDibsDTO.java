package com.isix.easyGym.detail.dto;

import org.springframework.stereotype.Component;

@Component("detaildibsDTO")
public class DetailDibsDTO {

	private int dibsNo;
	private int detailNo;
	private int memberNo;
	
	public DetailDibsDTO() {
		
	}

	public int getDibsNo() {
		return dibsNo;
	}

	public void setDibsNo(int dibsNo) {
		this.dibsNo = dibsNo;
	}

	public int getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	
}

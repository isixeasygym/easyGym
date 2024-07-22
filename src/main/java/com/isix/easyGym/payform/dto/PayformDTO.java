package com.isix.easyGym.payform.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("payformDTO")
public class PayformDTO {
	
	private int payformNo;
	private int memberNo;
	private int detailNo;
	private int payformSub;
	private int payformPrice;
	private Date payformDate;
	private int payformStatus;
	private int payformPayment;
	private String memberName;
	private String memberPhone;
	private String detailBusinessName;

	
	public PayformDTO() {

	}

	public int getPayformNo() {
		return payformNo;
	}

	public void setPayformNo(int payformNo) {
		this.payformNo = payformNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}

	public int getPayformSub() {
		return payformSub;
	}

	public void setPayformSub(int payformSub) {
		this.payformSub = payformSub;
	}

	public int getPayformPrice() {
		return payformPrice;
	}

	public void setPayformPrice(int payformPrice) {
		this.payformPrice = payformPrice;
	}

	public Date getPayformDate() {
		return payformDate;
	}

	public void setPayformDate(Date payformDate) {
		this.payformDate = payformDate;
	}

	public int getPayformStatus() {
		return payformStatus;
	}

	public void setPayformStatus(int payformStatus) {
		this.payformStatus = payformStatus;
	}

	public int getPayformPayment() {
		return payformPayment;
	}

	public void setPayformPayment(int payformPayment) {
		this.payformPayment = payformPayment;
	}


	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getDetailBusinessName() {
		return detailBusinessName;
	}

	public void setDetailBusinessName(String detailBusinessName) {
		this.detailBusinessName = detailBusinessName;
	}
}

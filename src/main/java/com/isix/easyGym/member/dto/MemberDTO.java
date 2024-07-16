package com.isix.easyGym.member.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberDTO")
public class MemberDTO {
	private int memberNo;
	private String memberName;
	private String memberId;
	private String memberPwd;
	private String memberPhone;
	private String memberEmail;
	private String memberAddr;
	private String memberPost;
	private Date memberCreatedAt;
	private int memberState;
	private String memberGender;
	private String memberMarketing;
	
	public MemberDTO() {
		
	}
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberPost() {
		return memberPost;
	}
	public void setMemberPost(String memberPost) {
		this.memberPost = memberPost;
	}
	public Date getMemberCreatedAt() {
		return memberCreatedAt;
	}
	public void setMemberCreatedAt(Date memberCreatedAt) {
		this.memberCreatedAt = memberCreatedAt;
	}
	public int getMemberState() {
		return memberState;
	}
	public void setMemberState(int memberState) {
		this.memberState = memberState;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberMarketing() {
		return memberMarketing;
	}
	public void setMemberMarketing(String memberMarketing) {
		this.memberMarketing = memberMarketing;
	}
	public MemberDTO(int memberNo, String memberName, String memberId, String memberPwd, String memberPhone,
			String memberEmail, String memberAddr, String memberPost, Date memberCreatedAt, int memberState,
			String memberGender, String memberMarketing) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddr = memberAddr;
		this.memberPost = memberPost;
		this.memberCreatedAt = memberCreatedAt;
		this.memberState = memberState;
		this.memberGender = memberGender;
		this.memberMarketing = memberMarketing;
	}
	
	
	
}

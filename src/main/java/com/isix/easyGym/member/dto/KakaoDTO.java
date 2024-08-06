package com.isix.easyGym.member.dto;

import org.springframework.stereotype.Component;

@Component("kakaoDTO")
public class KakaoDTO {
	private String kakaoId;
	private String kakaoEmail;
	private String memberName;
	private String accessToken;
	
	public KakaoDTO() {

	}

	public String getKakaoId() {
		return kakaoId;
	}

	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}

	public String getKakaoEmail() {
		return kakaoEmail;
	}

	public void setKakaoEmail(String kakaoEmail) {
		this.kakaoEmail = kakaoEmail;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}

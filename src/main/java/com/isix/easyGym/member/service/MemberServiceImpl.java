package com.isix.easyGym.member.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dao.MemberDAO;
import com.isix.easyGym.member.dto.KakaoDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

@Service("memberService") // 아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class MemberServiceImpl implements MemberService {
	
	private static final String REST_API_KEY = "3c843cca4013634dd38d454b2948d9de";
	private static final String REDIRECT_URI = "http://localhost:8090/kakao-login";
	private static final String GRANT_TYPE = "authorization_code";
	
	@Autowired
	private MemberDAO memberDAO;

	public void addMember(MemberDTO memberDTO) throws DataAccessException {
		memberDAO.insertMember(memberDTO);
	}

	public MemberDTO findMember(String id) throws DataAccessException {
		MemberDTO memberDTO = memberDAO.selectMemberById(id);
		return memberDTO;
	}

	public void updateMember(MemberDTO memberDTO) throws DataAccessException {
		memberDAO.updateMember(memberDTO);
	}

	public void delMember(String id) throws DataAccessException {
		memberDAO.delMember(id);
	}

	public MemberDTO login(MemberDTO member) throws DataAccessException {
		return memberDAO.login(member);
	}
	
	// 중복체크
	public String checkId(String memberId) throws DataAccessException {
		return memberDAO.checkId(memberId);

	}

	@Override
	public int loginCheck(int memberNo) throws DataAccessException {
		int result = memberDAO.loginChecking(memberNo);
		return result;
	}

	@Override
	public int findMemberNo(int memberNo) throws DataAccessException {
		int memberNum=memberDAO.selectMemberNo(memberNo);
		return memberNum;
	}

	@Override
	public boolean selectId(String memberId) throws DataAccessException {
		// TODO Auto-generated method stub
		return memberDAO.selectId(memberId);
	}
	
	@Override
	public String getKakaoAccessToken(String code) throws Exception {
	    String tokenURL = "https://kauth.kakao.com/oauth/token";
	    String access_token = null;

	    try {
	        HttpResponse<JsonNode> response = Unirest.post(tokenURL)
	                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
	                .field("grant_type", GRANT_TYPE)
	                .field("client_id", REST_API_KEY)
	                .field("redirect_uri", REDIRECT_URI)
	                .field("code", code)
	                .asJson();

	        JSONObject jsonObject = response.getBody().getObject();
	        access_token = jsonObject.getString("access_token");

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Failed to get Kakao access token", e);
	    }

	    return access_token;
	}

	@Override
	public KakaoDTO getKakaoUserInfo(String accessToken) throws Exception {
	    String URL = "https://kapi.kakao.com/v2/user/me";
	    KakaoDTO kakaoDTO = new KakaoDTO();

	    try {
	        HttpResponse<JsonNode> response = Unirest.get(URL)
	                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
	                .header("Authorization", "Bearer " + accessToken)
	                .asJson();

	        JSONObject jsonObject = response.getBody().getObject();
	        long id = jsonObject.getLong("id");
	        String user_id = Long.toString(id);
	        JSONObject kakao_account = jsonObject.getJSONObject("kakao_account");

	        JSONObject profile = kakao_account.getJSONObject("profile");
	        String nickname = profile.getString("nickname");

	        boolean hasEmail = kakao_account.getBoolean("has_email");
	        String email = null;
	        if (hasEmail) {
	            email = kakao_account.getString("email");
	        }
	        kakaoDTO.setKakaoId(user_id);
	        kakaoDTO.setMemberName(nickname);
	        kakaoDTO.setKakaoEmail(email);
	        kakaoDTO.setAccessToken(accessToken);

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Failed to get Kakao user info", e);
	    }

	    return kakaoDTO;
	}

	@Override
	public void kakaoLogin(KakaoDTO kakaoDTO) throws Exception {
	    try {
	        boolean isExist = memberDAO.isExistKakao(kakaoDTO);

	        if (isExist) {
	            memberDAO.kakaoUpdate(kakaoDTO);
	        } else {
	            memberDAO.kakaoInsert(kakaoDTO);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Failed to handle Kakao login", e);
	    }
	}

	

	@Override
	public void kakaoLogout(String kakaoId) throws Exception {
		
		try {
			String access_token =memberDAO.getAccessToken(kakaoId);
			String logoutURL = "https://kapi.kakao.com/v1/user/logout";
			
			HttpResponse<JsonNode> response = Unirest.post(logoutURL)
					.header("Authorization", "Bearer " + access_token)
					.asJson();
			
			memberDAO.delAccessToken(kakaoId);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
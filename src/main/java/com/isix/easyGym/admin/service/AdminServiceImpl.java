package com.isix.easyGym.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.admin.dao.AdminDAO;
import com.isix.easyGym.admin.dto.AdminDTO;

@Service("adminService")  //아래에 내용을 넣지 않더라도 기본적으로 service라는 것을 지정해줘야함
public class AdminServiceImpl implements AdminService {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
	private AdminDAO adminDAO;
	
	@Override
	public void addAdmin(AdminDTO dto) throws DataAccessException {
		adminDAO.joinAd(dto);
	}
	
	
	@Override
	public AdminDTO login(AdminDTO dto) throws DataAccessException {
		return adminDAO.loginCheck(dto);
	}
	
	// 회원 리스트
	@Override
	public List memberList() throws DataAccessException {
		List mlist=adminDAO.selectAll();
		return mlist;
	}
	
	// 탈퇴 회원 리스트
	@Override
	public List withdrawMember() throws DataAccessException{
		List mlist = adminDAO.selectWithMem();
		return mlist;
	}
	
	// 사업자 리스트
	@Override
	public List operList() throws DataAccessException{
		List olist = adminDAO.selectOper();
		return olist;
	}
	
	// 업체 리스트
	@Override
	public List comList() throws DataAccessException{
		List clist = adminDAO.selectCompany();
		return clist;
	}

	// 신고 리스트
	public List reportList() throws DataAccessException{
		List rlist = adminDAO.selectReport();
		return rlist;
	}
}

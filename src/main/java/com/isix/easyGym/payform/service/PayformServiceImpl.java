package com.isix.easyGym.payform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.payform.dao.PayformDAO;

import java.util.Map;

@Service("payformService")
public class PayformServiceImpl implements PayformService {

	@Autowired
	private PayformDAO payformDAO;

	public Map payForm(Map<String, Integer> pagingMap) throws DataAccessException {
		int member = pagingMap.get("member");
		pagingMap.put("member", member);
		Map payForm = payformDAO.selectMemberInfo(member);
		return payForm;
	}
	
}

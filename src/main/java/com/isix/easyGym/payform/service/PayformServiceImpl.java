package com.isix.easyGym.payform.service;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.payform.dao.PayformDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("payformService")
public class PayformServiceImpl implements PayformService {

    @Autowired
    private PayformDAO payformDAO;

    @Override
    public List getPayformData(Map payformData) throws DataAccessException {
        List list = new ArrayList();
        MemberDTO memberDTO = payformDAO.selectMemberByNo((int)payformData.get("member"));
        DetailDTO detailDTO = payformDAO.selectDetailByNo((int)payformData.get("detail"));
        list.add(memberDTO);
        list.add(detailDTO);
        return list;
    }

	@Override
	public int buyCheck(int memberNo) throws DataAccessException {
		int buyNo= payformDAO.checkingBuy(memberNo);
		return buyNo;
	}

    @Override
    public int insertPayform(Map payformMap) throws DataAccessException {
        int payformNo = payformDAO.getNewPayformNo();
        payformMap.put("payformNo", payformNo);
        payformDAO.insertPayform(payformMap);
        return payformNo;
    }

    @Override
    public Map selectPayform(int payformNo) throws DataAccessException {
        Map payformMap = new HashMap();
        PayformDTO payformRes = payformDAO.viewPayform(payformNo);
        payformMap.put("payformDTO", payformRes);
        return payformMap;
    }

}

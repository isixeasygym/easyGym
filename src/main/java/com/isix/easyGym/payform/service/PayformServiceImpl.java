package com.isix.easyGym.payform.service;

import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.payform.dao.PayformDAO;

import java.util.Map;

@Service("payformService")
public class PayformServiceImpl implements PayformService {

    @Autowired
    private PayformDAO payformDAO;

    public PayformDTO payformForm(int memberNo) throws DataAccessException {
        return payformDAO.selectPayform(memberNo);
    }

	@Override
	public PayformDTO buyCheck(int memberNo) throws DataAccessException {
		PayformDTO buyNo= payformDAO.checkingBuy(memberNo);
		return buyNo;
	}
}

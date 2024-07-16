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

    public Map payformForm(Map<String, Integer> pagingMap) throws DataAccessException {
        int member = pagingMap.get("member");
        pagingMap.put("member", member);
        Map payformForm = payformDAO.selectMemberInfo(member);
        return payformForm;
    }

    public Map payformDone(Map<String, Integer> pagingMap) throws DataAccessException {
        int member = pagingMap.get("member");
        pagingMap.put("member", member);
        Map payformForm = payformDAO.selectMemberInfo(member);
        return payformForm;
    }

    public Map payformCancel(Map<String, Integer> pagingMap) throws DataAccessException {
        int member = pagingMap.get("member");
        pagingMap.put("member", member);
        Map payformCancel = payformDAO.selectMemberInfo(member);
        return payformCancel;
    }

    public Map payformRefund(Map<String, Integer> pagingMap) throws DataAccessException {
        int member = pagingMap.get("member");
        pagingMap.put("member", member);
        Map payformRefund = payformDAO.selectMemberInfo(member);
        return payformRefund;
    }

    public Map payformTosspay(Map<String, Integer> pagingMap) throws DataAccessException {
        int member = pagingMap.get("member");
        pagingMap.put("member", member);
        Map payformTosspay = payformDAO.selectMemberInfo(member);
        return payformTosspay;
    }
}

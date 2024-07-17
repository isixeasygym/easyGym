package com.isix.easyGym.payform.service;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface PayformService {
    public PayformDTO payformForm(int memberNo) throws DataAccessException;
    
    public PayformDTO buyCheck(int memberNo) throws DataAccessException;
}
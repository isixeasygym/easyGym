package com.isix.easyGym.payform.service;

import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface PayformService {
    public List getPayformData(Map payformData) throws DataAccessException;
}
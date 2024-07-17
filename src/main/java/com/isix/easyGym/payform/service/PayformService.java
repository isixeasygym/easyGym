package com.isix.easyGym.payform.service;

import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface PayformService {
    public PayformDTO payformForm(int memberNo) throws DataAccessException;
}
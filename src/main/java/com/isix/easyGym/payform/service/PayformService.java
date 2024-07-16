package com.isix.easyGym.payform.service;

import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface PayformService {
    public Map<String, Object> payformForm(Map<String, Integer> pagingMap) throws Exception;

    public Map<String, Object> payformDone(Map<String, Integer> pagingMap) throws DataAccessException;

    public Map<String, Object> payformCancel(Map<String, Integer> pagingMap) throws DataAccessException;

    public Map<String, Object> payformRefund(Map<String, Integer> pagingMap) throws DataAccessException;

    public Map<String, Object> payformTosspay(Map<String, Integer> pagingMap) throws DataAccessException;
}
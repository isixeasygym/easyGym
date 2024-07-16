package com.isix.easyGym.payform.service;

import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface PayformService {
    Map payformForm(Map<String, Integer> pagingMap) throws DataAccessException;
    Map payformDone(Map<String, Integer> pagingMap) throws DataAccessException;
    Map payformCancel(Map<String, Integer> pagingMap) throws DataAccessException;
    Map payformRefund(Map<String, Integer> pagingMap) throws DataAccessException;
    Map payformTosspay(Map<String, Integer> pagingMap) throws DataAccessException;
}
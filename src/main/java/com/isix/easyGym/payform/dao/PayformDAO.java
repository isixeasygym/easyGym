package com.isix.easyGym.payform.dao;

import com.isix.easyGym.payform.dto.PayformDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository("payformDAO")
public interface PayformDAO {

        PayformDTO selectPayform(@Param("memberNo") int memberNo) throws DataAccessException;

        void insertPayform(@Param("payformDTO") PayformDTO payformDTO) throws DataAccessException;

        void deletePayform(@Param("payformNo") int payformNo) throws DataAccessException;
    }
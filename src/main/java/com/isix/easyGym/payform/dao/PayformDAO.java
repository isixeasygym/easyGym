package com.isix.easyGym.payform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository("payformDAO")
public interface PayformDAO {
    public Map<String, Object> selectMemberInfo(@Param("member") int member) throws DataAccessException;
}
package com.isix.easyGym.payform.dao;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.payform.dto.PayformDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("payformDAO")

public interface PayformDAO {

    public MemberDTO selectMemberByNo(int memberNo) throws DataAccessException;
    public DetailDTO selectDetailByNo(int detailNo) throws DataAccessException;


        void insertPayform(@Param("payformDTO") PayformDTO payformDTO) throws DataAccessException;

        void deletePayform(@Param("payformNo") int payformNo) throws DataAccessException;
        
        public int checkingBuy(int memberNo) throws DataAccessException;
    }
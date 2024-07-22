package com.isix.easyGym.payform.dao;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.member.dto.MemberDTO;
import com.isix.easyGym.payform.dto.PayformDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository("payformDAO")

public interface PayformDAO {

    public MemberDTO selectMemberByNo(int memberNo) throws DataAccessException;

    public DetailDTO selectDetailByNo(int detailNo) throws DataAccessException;

    public int getNewPayformNo() throws DataAccessException;

    public void insertPayform(Map payformMap) throws DataAccessException;

    public PayformDTO viewPayform(int payformNo) throws DataAccessException;

    public int cancelPayform(int payformNo) throws DataAccessException;

    public PayformDTO checkingBuy(int memberNo) throws DataAccessException;

}

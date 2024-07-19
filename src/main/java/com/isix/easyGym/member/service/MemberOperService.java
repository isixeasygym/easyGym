package com.isix.easyGym.member.service;

import org.springframework.dao.DataAccessException;

import com.isix.easyGym.member.dto.MemberOperDTO;

public interface MemberOperService {


   public void addOperator(MemberOperDTO memberOperDTO) throws DataAccessException;
         
   public void delOperMember(String id) throws DataAccessException;
   
   public MemberOperDTO login(MemberOperDTO memberDTO) throws DataAccessException;

   public boolean checkId(String memberOperId) throws DataAccessException;
}
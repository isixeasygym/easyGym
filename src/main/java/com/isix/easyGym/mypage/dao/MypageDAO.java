package com.isix.easyGym.mypage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.isix.easyGym.detail.dto.DetailDTO;
import com.isix.easyGym.detail.dto.DetailDibsDTO;

@Mapper
@Repository("mypageDAO")
public interface MypageDAO {

	//1-2)찜 목록
	public List<DetailDTO> selectAllDetail(@Param("count") int count) throws DataAccessException;

	public int selectToDibs() throws DataAccessException;

	public int getNewDibsNo() throws DataAccessException;
	

}

package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;

public interface IOthersDAO {
	public List<Map<String,Object>> selectLprodList();
	
	public List<BuyerVO> selectBuyerList(@Param("prod_lgu") String prod_lgu); //이름은 없고 value만 잇어서 강제로 이름 정해야해 =>@Param("prod_lgu")
	
}

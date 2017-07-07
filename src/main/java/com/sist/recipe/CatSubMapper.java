package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CatSubVO;
import com.sist.vo.CatTopVO;

public interface CatSubMapper {
	
	
	@Select("Select id, name from Cat_sub Where cat_top_id=#{cat_top_id}")
	public List<CatSubVO> selectList(int cat_top_id);
	
	@Select("Select id,name from CAT_TOP")
	public List<CatTopVO> selectTopList();
	
	@Select("Select name from cat_sub where id=#{id}")
	public String selectCatSubName(int id);
}

package com.sist.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CatSubVO;

public interface CatSubMapper {
	
	@Select("Select id, name from Cat_sub Where cat_top_id=#{cat_top_id}")
	public List<CatSubVO> selectList(int cat_top_id);
	
}

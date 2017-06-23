package com.sist.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.Cat_subVO;

public interface Cat_subMapper {
	
	@Select("Select id, name from Cat_sub Where cat_top_id=#{cat_top_id}")
	public List<Cat_subVO> select_list(int cat_top_id);
	
}

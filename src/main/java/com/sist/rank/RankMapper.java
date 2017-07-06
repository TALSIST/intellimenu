package com.sist.rank;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RankMapper {
	
	@Select("SELECT * FROM recipe order by hit desc")
	public List<RecipeVO> recipeRank();
}

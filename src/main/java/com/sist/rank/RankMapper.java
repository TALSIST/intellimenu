package com.sist.rank;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RankMapper {
	
	@Select("SELECT * FROM recipe WHERE regdate BETWEEN '200001'||'01' AND (SELECT  TO_CHAR(LAST_DAY(SYSDATE),'YYYYMMDD') MONTH_LAST_DAY FROM DUAL) order by hit desc")
	public List<RecipeVO> recipeRank();
	
	//@Select("SELECT * FROM recipe WHERE regdate ''")
	//public List<RecipeVO> recipeDate(int year,int month);
}
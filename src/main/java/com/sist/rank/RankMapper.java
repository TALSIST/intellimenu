package com.sist.rank;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RankMapper {
	
	//@Select("SELECT * FROM recipe WHERE regdate BETWEEN #{ydate}||#{mdate}||'01' AND (SELECT  TO_CHAR(LAST_DAY(#{ydate}||#{mdate}||'01'),'YYYYMMDD') MONTH_LAST_DAY FROM DUAL) order by hit desc")
	@Select("SELECT * FROM recipe WHERE regdate BETWEEN '20170701' AND '20170731'")
	public List<RecipeVO> recipeRank(String ydate,String mdate);
	
	//@Select("SELECT * FROM recipe WHERE regdate ''")
	//public List<RecipeVO> recipeDate(int year,int month);
	
	@Select("SELECT * FROM recipe order by hit desc")
	public List<RecipeVO> recipeList();
	
}
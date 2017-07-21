package com.sist.rank;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RankMapper {
	
	//@Select("SELECT * FROM recipe WHERE regdate BETWEEN to_date(#{Fday},'YYYYMMDD') AND to_date(#{Lday},'YYYYMMDD') order by hit desc")
	//@Select("SELECT * FROM recipe WHERE regdate BETWEEN '20170701' AND '20170731'")
	@Select("SELECT * FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT id,user_id,title,hit,img_ori,img_new"
					+ " FROM recipe"
					+ " WHERE regdate >= TO_DATE(${Firstday}, 'YYYYMMDD')"
					+ " AND regdate <  TO_DATE(${lastdate}, 'YYYYMMDD')"
					+ " ORDER BY hit DESC) X)"
			+ "WHERE num BETWEEN 1 AND 10")
	//@Select("SELECT * FROM recipe WHERE regdate>=#{Firstday}")
	public List<RecipeVO> recipeRank(Map<String, String> map);
	
	@Select("SELECT TO_CHAR(LAST_DAY(#{Dday}||'01'),'YYYYMMDD') MONTH_LAST_DAY FROM DUAL")
	public String LastDay(String Dday);
	
	
	//@Select("SELECT * FROM recipe WHERE regdate ''")
	//public List<RecipeVO> recipeDate(int year,int month);
	
	@Select("SELECT X.*, rownum FROM ("
				+ " SELECT id,user_id,title,hit,img_ori,img_new"
				+ " FROM recipe"
				+ " ORDER BY hit DESC) X"
			+ " WHERE rownum BETWEEN 1 AND 10")
	public List<RecipeVO> recipeList();
	
}
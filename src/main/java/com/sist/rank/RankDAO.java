package com.sist.rank;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.RecipeVO;

@Repository
public class RankDAO {
	
	@Autowired
	private RankMapper rankMapper;
	
	public List<RecipeVO> recipeList(Map map) {
		List<RecipeVO> list=rankMapper.recipeRank(map);
		return list;
	}
	public List<RecipeVO> recipeR() {
		return rankMapper.recipeList();
	}
	public String Lastday(String Dday){
		return rankMapper.LastDay(Dday);
	}
}

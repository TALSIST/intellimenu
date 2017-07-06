package com.sist.rank;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.RecipeVO;

@Repository
public class RankDAO {
	
	@Autowired
	private RankMapper rankMapper;
	
	public List<RecipeVO> recipeList(String ydate,String mdate) {

		return rankMapper.recipeRank(ydate,mdate);
	}
	public List<RecipeVO> recipeR() {
		return rankMapper.recipeList();
	}
}

package com.sist.rank;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sist.vo.RecipeVO;

public class RankDAO {
	
	@Autowired
	private RankMapper rankMapper;
	
	public List<RecipeVO> recipeList() {
		return rankMapper.recipeRank();
	}
}

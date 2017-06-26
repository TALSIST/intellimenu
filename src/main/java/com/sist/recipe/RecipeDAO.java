package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.Recipe;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	public List<Recipe> CatSubRecipeListData(Map map){
		
		return recipeMapper.CatSubRecipeListData(map);
	};
	
}

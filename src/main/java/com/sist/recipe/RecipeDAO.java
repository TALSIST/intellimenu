package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.Recipe;
import com.sist.vo.RecipeContent;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	public List<Recipe> catSubRecipeListData(Map map){
		
		return recipeMapper.catSubRecipeListData(map);
	};
	
	public int catSubRecipeListTotalPage(int cat_sub_id){
		
		return recipeMapper.catSubRecipeListTotalPage(cat_sub_id);
	};
	
	public Recipe recipeDetail(int id){
		
		return recipeMapper.recipeDetail(id);
	};
	
	public List<RecipeContent> recipeDetailContent(int recipe_id){
		
		return recipeMapper.recipeDetailContent(recipe_id);
	};


	
}

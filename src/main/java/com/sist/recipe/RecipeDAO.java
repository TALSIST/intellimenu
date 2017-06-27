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
	
	public List<Recipe> catSubRecipeListData(Map map){
		
		return recipeMapper.catSubRecipeListData(map);
	};
	
<<<<<<< HEAD
=======
	public int catSubRecipeListTotalPage(int cat_sub_id){
		
		return recipeMapper.catSubRecipeListTotalPage(cat_sub_id);
	};
	
	public Recipe recipeDetail(int id){
		
		return recipeMapper.recipeDetail(id);
	};

>>>>>>> 1f0923ea10a13554334b17a59842bd59fb3ff01b
	
}

package com.sist.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.CatSubVO;
import com.sist.vo.IngrRecipeVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeUpdateDAO {
	@Autowired
	private RecipeInsertMapper rim;
	
	public RecipeVO selectRecipe(int id){
		return rim.selectRecipe(id);
	}

	public CatSubVO selectCatsub(int id){
		return rim.selectCatsub(id);
	}
	public List<IngrRecipeVO> selectIngRecipe(int id){
		return rim.selectIngRecipe(id);
	}
	public List<RecipeContentVO> selectStesCon(int id){
		return rim.selectStesCon(id);
	}
	public List<String> selectRTag(int id){
		return rim.selectRTag(id);
	}
	public void updateRecipe(RecipeVO recipeVO){
		rim.updateRecipe(recipeVO);
	}
	public void deleteIngrR(int id){
		rim.deleteIngrR(id);
	}
	public void updateStep(RecipeContentVO vo){
		rim.updateStep(vo);
	}
	public void deleteStep(int id){
		rim.deleteStep(id);
	}
	public void deleteTag(int recipe_id){
		rim.deleteTag(recipe_id);
	}
	
}

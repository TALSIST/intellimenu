package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.IngrRecipeVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeTagVO;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	
	
	/*******************************insert********************************************/
	
	//pk 가져오기
	public int recipeMId(){
		return recipeMapper.recipeMId();
	}
	
	
	
	
	public int recipeTotal() {
		return recipeMapper.recipeTotal();
	}
	
	public List<RecipeVO> recipeList(Map map) {
		return recipeMapper.recipeList(map);
	}
	
	
	
	
	/************************** cat_sub_id로 레시피리스트가져오기  ********************************/	
	public int recipeSubCatTotal(int cat_sub_id) {
		return recipeMapper.recipeCatSubTotal(cat_sub_id);
	}
	
	public List<RecipeVO> catSubRecipeListData(Map map) {
		return recipeMapper.catSubRecipeListData(map);
	};

	public int catSubRecipeListTotalPage(int cat_sub_id) {
		return recipeMapper.catSubRecipeListTotalPage(cat_sub_id);
	};
	
		
	
	/************************** cat_sub_id로 레시피리스트가져오기  ********************************/	
	public RecipeVO recipeDetail(int id) {
		return recipeMapper.recipeDetail(id);
	};

	public List<RecipeContentVO> recipeDetailContent(int recipe_id) {
		return recipeMapper.recipeDetailContent(recipe_id);
	};
	
	public List<IngredientVO> IngrRecipeJoin(int recipe_id) {
		return recipeMapper.IngrRecipeJoin(recipe_id);
	};
	
	public List<RecipeTagVO> recipeTagSelectListByRecipeId(int recipe_id) {
		return recipeMapper.recipeTagSelectListByRecipeId(recipe_id);
	};

	
	
	/*************** 레시피 메인 페이지에 태그이름으로 3개의 추천 레시피리스트가져오기  **********************/	
	public List<RecipeTagVO> recipeTagSelectList3ByName(String name) {
		return recipeMapper.recipeTagSelectList3ByName(name);
	};

	
	
	/************************** 태그이름으로 레시피리스트가져오기  ********************************/	
	public int recipeTagListTotalPage(String tagName){
		
		return recipeMapper.recipeTagListTotalPage(tagName);
	};	
	public List<RecipeVO> recipeTagListByTagName(Map map){
		
		return recipeMapper.recipeTagListByTagName(map);
	};

	
	
	/************************** 재료이름으로 레시피리스트가져오기  ********************************/
	public int recipeIngrListTotal(String ingrName){
		
		return recipeMapper.recipeIngrListTotal(ingrName);
	};
	
	public List<RecipeVO> recipeIngrListByIngrName(Map map){
		
		return recipeMapper.recipeIngrListByIngrName(map);
	};
	public void insertRecipe(RecipeVO vo){
		recipeMapper.insertRecipe(vo);
		
	}


	/************************  검색  ************************/
	public List<RecipeVO> searchRecipeIngrListByIngrName(Map map){
		
		return recipeMapper.searchRecipeIngrListByIngrName(map);
	};
	
	public int searchRecipeListTotal(String searchKeyword){
		
		return recipeMapper.searchRecipeListTotal(searchKeyword);
	};
	
	public int searchRecipeListByRecipeTitle(Map map){
		
		return recipeMapper.searchRecipeListByRecipeTitle(map);
	};
	
	public List<RecipeVO> searchRecipeTagListByTagName(Map map){
		
		return recipeMapper.searchRecipeTagListByTagName(map);
	};
	
	
	
}






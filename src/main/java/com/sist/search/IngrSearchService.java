package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.recipe.RecipeDAO;

@Service
public class IngrSearchService implements SearchService{

	@Autowired
	private RecipeDAO recipeDAO=new RecipeDAO();
	
	public List keywordSearch(Map map) {
		System.out.println("keywordSearch IngrSearchService");
		
		return recipeDAO.searchRecipeIngrListByIngrName(map);
	}

	public int getKeywordSearchTotal(String searchKeyword) {
		
		return recipeDAO.searchRecipeIngrListTotal(searchKeyword);
	}

}

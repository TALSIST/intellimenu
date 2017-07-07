package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.recipe.RecipeDAO;
import com.sist.vo.RecipeVO;

@Service
public class TitleSearchService implements SearchService{

	@Autowired
	private RecipeDAO recipeDAO=new RecipeDAO();
	
	public List<RecipeVO> keywordSearch(Map map) {
		System.out.println("keywordSearch TitleSearchService");
		
		return recipeDAO.searchRecipeListByRecipeTitle(map);
	}

	public int getKeywordSearchTotal(String searchKeyword) {
		return recipeDAO.searchRecipeListTotal(searchKeyword);
	}

}

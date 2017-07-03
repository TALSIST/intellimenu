package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sist.recipe.RecipeDAO;
import com.sist.vo.RecipeVO;

@Service //id를 따로 안주면 앞글자가 소문자인게 id가 된다.
public class TagSearchService implements SearchService{
	
	private RecipeDAO recipeDAO=new RecipeDAO();

	public List<RecipeVO> keywordSearch(Map map) {
		System.out.println("keywordSearch TagSearchService");
		System.out.println(map.get("searchKeyword"));
		System.out.println(map.get("start"));
		System.out.println(map.get("end"));
		
		
		System.out.println();
		return recipeDAO.searchRecipeTagListByTagName(map);
	}

	public int getKeywordSearchTotal(String searchKeyword) {
		return 0;
	}

}

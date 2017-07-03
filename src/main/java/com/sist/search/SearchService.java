package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.recipe.RecipeDAO;
import com.sist.vo.RecipeVO;

@Service
public interface SearchService {
	
	
	public List<RecipeVO> keywordSearch(Map map);
	
	public int getKeywordSearchTotal(String searchKeyword);
	
}

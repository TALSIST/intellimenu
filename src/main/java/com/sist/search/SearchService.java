package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.vo.RecipeVO;


public interface SearchService {
	
	
	public List<RecipeVO> keywordSearch(Map map);
	
	public int getKeywordSearchTotal(String searchKeyword);
	
}

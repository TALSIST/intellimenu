package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.vo.RecipeVO;

@Service
public class TotalSearchService implements SearchService{

	public List<RecipeVO> keywordSearch(Map map) {
		System.out.println("keywordSearch TotalSearchService");
		
		return null;
	}

	public int getKeywordSearchTotal(String searchKeyword) {
		return 0;
	}

}

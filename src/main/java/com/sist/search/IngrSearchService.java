package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class IngrSearchService implements SearchService{

	public List keywordSearch(Map map) {
		System.out.println("keywordSearch IngrSearchService");
		
		return null;
	}

	public int getKeywordSearchTotal(String searchKeyword) {
		
		return 0;
	}

}

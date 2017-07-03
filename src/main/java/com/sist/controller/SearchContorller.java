package com.sist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.search.SearchService;

@Controller
public class SearchContorller {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("search/search_result")
	public String recipeSearchResult(String searchParam, String searchKeyword,  Model model){
		System.out.println(searchParam+" "+searchKeyword);
		
		
		
		
		return "search/search_result";
	}
	
}

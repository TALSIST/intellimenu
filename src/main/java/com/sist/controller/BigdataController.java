package com.sist.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.analysis.IngredientAnalyzer;
import com.sist.analysis.IngredientCrawler;
import com.sist.bigdata.BigDataService;
import com.sist.recipe.IngredientMapper;

@Controller
public class BigdataController {
	
	@Autowired
	private BigDataService BigDataService;
//	@Autowired
//	private IngredientAnalyzer analyzer;
//	@Autowired
//	private IngredientCrawler crawler;
	
	@RequestMapping("/bigdata/bigdata_main")
	public String bigdata_main(Model model){
		
		BigDataService.helloExample(model);//안녕출력
		BigDataService.getWeather(model);//날씨 출력
		BigDataService.getMart(model);;//마트랭킹 출력
		
		return "/bigdata/bigdata_main";
	}
	
	@RequestMapping("/recipe/recipe_ingr")
	public @ResponseBody String recipeIngredientAnalysis() {
//		try {
//			for (int i=0; i<10; i++) {
//				crawler.searchIngredient("돼지고기 고추장찌개", i);
//			}
//			analyzer.execute("");
//			FileReader fr = new FileReader("./ouput/");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		return "";
	}
	
	
}

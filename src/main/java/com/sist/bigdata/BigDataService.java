package com.sist.bigdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sist.naver.Item;
import com.sist.naver.NaverManager;
import com.sist.recipe.RecipeDAO;
import com.sist.search.IngrSearchService;
import com.sist.search.SearchDAO;
import com.sist.search.TitleSearchService;
import com.sist.spark.IngrRankDAO;
import com.sist.spark.IngrRankVO;
import com.sist.users.UsersService;
import com.sist.vo.IngredientVO;
import com.sist.vo.LogSearch;
import com.sist.vo.RecipeVO;
import com.sist.vo.UsersVO;
import com.sist.weather.WeatherManager;

@Service
public class BigDataService {
	@Autowired
	private WeatherManager weatherManager;
	
	@Autowired
	private NaverManager naverManager;
	
	@Autowired
	private IngrRankDAO ingrRankDAO;
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Autowired
	private IngrSearchService ingrSearchService;
	
	@Autowired
	private UsersService usersService;
	
	public void helloExample(Model model){
		
		model.addAttribute("hello", "안녕");
	}
	
	public void getWeather(Model model){
		
		String weather=weatherManager.getWeather();
		
		ingrRankDAO.setCollection(weather);
		
		List<IngrRankVO> weatherIngrlist=ingrRankDAO.ingr3Data();
		
		System.out.println("날씨추천 재료크기"+weatherIngrlist.size());
		
		for (IngrRankVO vo : weatherIngrlist) {
			System.out.println("날씨추천 재료"+vo.getName());
			
		}
		Map search =new HashMap();
		
		search.put("start", 1);
		search.put("end", 3);
		search.put("searchKeyword", weatherIngrlist.get(0).getName());
		List<RecipeVO> RecipeListOnWeahter=ingrSearchService.keywordSearch(search);
		for (RecipeVO vo : RecipeListOnWeahter) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}
		
		model.addAttribute("RecipeListOnWeahter", RecipeListOnWeahter);
		model.addAttribute("weatherIngrlist", weatherIngrlist);
		model.addAttribute("weather", weather);
	}
	
	
	
	
	
}

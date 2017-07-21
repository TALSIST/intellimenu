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
import com.sist.recipe.IngredientDAO;
import com.sist.recipe.RecipeDAO;
import com.sist.search.IngrSearchService;
import com.sist.search.SearchDAO;
import com.sist.search.TitleSearchService;
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
	
	public void helloExample(Model model){
		
		model.addAttribute("hello", "안녕");
	}
	
	public void getWeather(Model model){
		
		String weather=weatherManager.getWeather();
		
		
		
		model.addAttribute("weather", weather);
	}
	
	
	
	
	
	
	
	
	
	
}

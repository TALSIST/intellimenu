package com.sist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.recipe.CatSubDAO;
import com.sist.recipe.RecipeDAO;
import com.sist.restaurant.RestaurantDAO;
import com.sist.util.PagingManager;
import com.sist.vo.CatSubVO;
import com.sist.vo.RestaurantVO;

@Controller
public class AdminController {
	@Autowired
	private RecipeDAO recipeDAO;
	@Autowired
	private CatSubDAO catDAO;
	@Autowired
	private RestaurantDAO restDAO;
	
	@RequestMapping("/admin")
	public String admin() {
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/admin/main")
	public String adminMain() {
		
		return "admin/content";
	}
	
	@RequestMapping("/admin/recipe_list")
	public String adminRecipeList(PagingManager page, String cat, Model model) {
		List list = null;
		page.setRowSize(20);
		if (cat==null || cat.equals("0")) {
			cat = "0";
			// 전체 목록 페이지 처리
			int total = recipeDAO.recipeTotal();
			Map map = page.calcPage(total);
			list = recipeDAO.recipeList(map);
		} else {
			// sublist 데이터 처리
			int total = recipeDAO.recipeSubCatTotal(Integer.parseInt(cat));
			Map map = page.calcPage(total);
			map.put("cat_sub_id", Integer.parseInt(cat));
			list = recipeDAO.catSubRecipeListData(map);
		}
		// sublist 처리
		List<CatSubVO> subList= catDAO.selectList(1);//종류별 리스트 가져오기
		
		model.addAttribute("cat", cat);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		model.addAttribute("subList", subList);
		return "admin/recipe_list";
	}
	
	@RequestMapping("/admin/restaurant_list")
	public String adminRestaurantList(PagingManager page, Model model) {
		page.setRowSize(20);
		int total = restDAO.restaurantTotal();
		Map map = page.calcPage(total);
		List<RestaurantVO> list = restDAO.restaurantList(map);

		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/restaurant_list";
	}
	
}

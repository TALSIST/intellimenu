package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.recipe.CatSubDAO;
import com.sist.recipe.RecipeDAO;
import com.sist.recipe.RecipeService;
import com.sist.restaurant.RestaurantDAO;
import com.sist.util.PagingManager;
import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.ReligionVO;
import com.sist.vo.RestaurantVO;

@Controller
public class AdminController {
	@Autowired
	private RecipeService recipeSVC;
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
	
	@RequestMapping("/admin/recipe/list")
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
		String catname = null;
		if (cat.equals("0")) {
			catname = "전체목록";
		} else {
			catname = subList.get(Integer.parseInt(cat)-1).getName();
		}
		
		model.addAttribute("cat", cat);
		model.addAttribute("catname", catname);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		model.addAttribute("subList", subList);
		return "admin/recipe_list";
	}
	
	@RequestMapping("admin/recipe/list/detail")
	public String adminRecipeDetail() {
		// TODO: recipectrlr에 있는 기능 재활용여부
		return "admin/recipe/detail";
	}
	
	@RequestMapping("/admin/ingredient/list")
	public String adminIngrList(PagingManager page, String attr, Model model) {
		page.setRowSize(20);
		if (attr==null) { attr = "y"; };
		Map map = page.calcPage(recipeSVC.selectIngrTotal());
		List<IngredientVO> list = recipeSVC.selectIngrList(map);
		model.addAttribute("pmgr", page);
		model.addAttribute("attr", attr);
		model.addAttribute("list", list);
		return "admin/ingredient_list";
	}
	
	@RequestMapping("/admin/ingredient/add")
	public @ResponseBody Map<String,String> adminIngrAdd(IngredientVO vo, String page) {
		Map map = new HashMap();
		try {
			recipeSVC.insertIngr(vo);
			map.put("result", "y");
		} catch (Exception e) {
			map.put("result", "n");
		}
		return map;
	}
	
	@RequestMapping("/admin/ingredient/category")
	public String adminIngrCat(String cat, Model model) {
		if (cat==null) { cat = "religion"; }
		Map map = new HashMap();
		map.put("tablename", cat);
		List<ReligionVO> list = recipeSVC.selectCatInfo(map);
		model.addAttribute("cat", cat);
		model.addAttribute("list", list);
		return "admin/ingredient_cat";
	}
	
	@RequestMapping("/admin/ingredient/category/mod")
	public @ResponseBody Map<String, String> adminIngrCatModify(String cat, String insert, String delete) {
		// TODO: 프론트에서 특수문자 사용 못하게 / 서버에서도 검사
		Map<String, String> result = new HashMap();
		try {
			String[] insertArr = insert.replace("[", "").replace("]", "").replace("\"", "").split(",");
			String[] deleteArr = delete.replace("[", "").replace("]", "").replace("\"", "").split(",");
			recipeSVC.modifyCatInfo(cat, insertArr, deleteArr);
			result.put("result", "y");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "n");
		}
		return result;
	}
	
	@RequestMapping("/admin/restaurant/list")
	public String adminRestaurantList(PagingManager page, Model model) {
		page.setRowSize(20);
		int total = restDAO.restaurantTotal();
		Map map = page.calcPage(total);
		List<RestaurantVO> list = restDAO.restaurantAdminList(map);

		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/restaurant_list";
	}
	
	@RequestMapping("/admin/users/list")
	public String adminUsersList() {
		return "";
	}
	
	@RequestMapping("/admin/users/regist")
	public String adminUsersRegist() {
		return "admin/users/regist";
	}
	@RequestMapping("admin/restaurant/insert")
	public String restaurantInsert(){
		return "admin/restaurant_insert";
	}
}

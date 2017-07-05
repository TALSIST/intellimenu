package com.sist.controller;

import java.util.ArrayList;
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
import com.sist.users.UsersService;
import com.sist.util.PagingManager;
import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.ReligionVO;
import com.sist.vo.RestaurantVO;
import com.sist.vo.UsersVO;

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
	@Autowired
	private UsersService userSVC;
	
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
	
	@RequestMapping("/admin/recipe/list/detail")
	public String adminRecipeDetail() {
		// TODO: recipectrlr에 있는 기능 재활용여부
		return "admin/recipe/detail";
	}
	
	@RequestMapping("/admin/recipe/insert")
	public String adminRecipeInsert() {
		return "admin/recipe/insert";
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
	
	// 상위 카테고리에 대응하는 하위카테고리 정보를 반환
	@RequestMapping("/admin/ingredient/catdata")
	public @ResponseBody CatSubVO adminIngrCatData() {
		
		return null;
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
	public @ResponseBody Map<String, String> adminIngrCatModify(@RequestBody Map<String, Object> data) {
		Map<String, String> result = new HashMap();
		try {
			String cat = (String) data.get("cat");
			List<Map<String, String>> list = (List<Map<String, String>>) data.get("list");
			List<String> insert = new ArrayList();
			List<Integer> delete = new ArrayList();
			for (Map<String, String> map : list) {
				String id = map.get("id").trim();
				String name = map.get("name").trim();
				if (id.isEmpty()) {
					insert.add(name);
				} else if(name.isEmpty()) {
					delete.add(Integer.parseInt(id));
				}
			}
			recipeSVC.modifyCatInfo(cat, insert, delete);
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
	
	@RequestMapping("/admin/restaurant/list/detail")
	public String adminRestaurantDetail() {
		return "admin/restaurant/detail";
	}
	
	@RequestMapping("/admin/users/list")
	public String adminUsersList(PagingManager page, Model model) {
		page.setRowSize(20);
		Map map = page.calcPage(userSVC.selectUserTotal());
		List<UsersVO> list = userSVC.selectUserList(map);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/users_list";
	}
	
	@RequestMapping("/admin/users/signup")
	public @ResponseBody Map<String, String> adminUsersRegist() {
		Map map = new HashMap();
		
		return map;
	}
	
	@RequestMapping("/admin/users/delete")
	public String adminUsersDelete(int[] chk) {
		try {
			userSVC.deleteUsers(chk);
		} catch (Exception e) {
			System.out.println("회원 삭제 실패");
		}
		return "redirect:/admin/users/list";
	}
	
	@RequestMapping("/admin/tag/list")
	public String adminTagsList(PagingManager page, Model model) {

		page.calcPage(100);
		model.addAttribute("pmgr", page);
		return "admin/tag_list";
	}
	
	@RequestMapping("/admin/log/search")
	public String adminLogSearch(PagingManager page, Model model) {
		
		page.calcPage(100);
		model.addAttribute("pmgr", page);
		return "admin/log_search";
	}
	
	@RequestMapping("/admin/log/login")
	public String adminLogLogin(PagingManager page, Model model) {
		
		page.calcPage(100);
		model.addAttribute("pmgr", page);
		return "admin/log_login";
	}
	
}

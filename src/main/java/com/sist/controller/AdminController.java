package com.sist.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sist.recipe.CatSubDAO;
import com.sist.recipe.RecipeDAO;
import com.sist.recipe.RecipeService;
import com.sist.restaurant.RestaurantDAO;
import com.sist.restaurant.RestaurantService;
import com.sist.search.SearchDAO;
import com.sist.users.UsersService;
import com.sist.util.PagingManager;
import com.sist.util.SearchManager;
import com.sist.vo.AddressVO;
import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.LogLoginVO;
import com.sist.vo.LogSearch;
import com.sist.vo.RecipeTagVO;
import com.sist.vo.RecipeVO;
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
	@Autowired
	private RestaurantService restaurantSVC;
	@Autowired
	private SearchDAO searchDAO;
	
	@RequestMapping("/admin/main")
	public String adminMain(Model model) {
		// 등록된 레시피 수
		model.addAttribute("recipe", recipeDAO.recipeTotal());
		// 등록된 태그 수
		model.addAttribute("tag", recipeSVC.recipeTagTotal());
		// 등록된 재료 수
		model.addAttribute("ingredient", recipeSVC.selectIngrTotal());
		// 등록된 음식점 수
		model.addAttribute("restaurant", restDAO.restaurantTotal());
		// 등록된 회원 수
		model.addAttribute("users", userSVC.selectUserTotal());
		return "admin/content";
	}

	//============================== 레시피 목록 출력 ==============================//
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
	public String adminRecipeDetail(int id, Model model) {
		RecipeVO vo = recipeSVC.recipeDetail(id);
		
		model.addAttribute("id", id);
		model.addAttribute("recipe", vo);
		return "admin/recipe/detail";
	}
	
	@RequestMapping("/admin/recipe/insert")
	public String adminRecipeInsert() {
		return "admin/recipe/insert";
	}
	
	//============================== 재료 목록 출력 ==============================//
	@RequestMapping("/admin/ingredient/list")
	public String adminIngrList(SearchManager page, String attr, String top, String sub, Model model) {
		if (attr==null) { attr = "y"; }
		if (top==null) { top = ""; }
		if (sub==null) { sub = ""; }
		page.setRowSize(20);
		
		String keyword = (page.getKeyword()==null)? "": page.getKeyword();
		String subName = null;
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("attr", attr);
		map.put("sub", sub);
		map.put("tablename", "ingr_"+top);
		List<IngredientVO> list = null;
		
		if (sub.isEmpty()) {
			list = recipeSVC.selectIngrList(page, map);
			
		} else {
			for (CatSubVO vo : recipeSVC.getingrCatData(top)) {
				if (vo.getId()==Integer.parseInt(sub)) { subName = vo.getName(); }
			}
			map.put("idname", (top.equals("season"))? "month": top+"_id");
			list = recipeSVC.selectIngrListWithCondition(page, map);
		}
		
		model.addAttribute("subcatname", subName);
		model.addAttribute("pmgr", page);
		model.addAttribute("attr", attr);
		model.addAttribute("top", top);
		model.addAttribute("sub", sub);
		model.addAttribute("list", list);
		return "admin/ingredient_list";
	}
	
	//============================== 재료 삭제 추가 ==============================//
	@RequestMapping("/admin/ingredient/add")
	public @ResponseBody Map<String,String> adminIngrAdd(IngredientVO vo, String page) {
		Map<String, String> map = new HashMap<>();
		try {
			recipeSVC.insertIngr(vo);
			map.put("result", "y");
		} catch (Exception e) {
			map.put("result", "n");
		}
		return map;
	}
	
	@RequestMapping("/admin/ingredient/remove")
	public @ResponseBody Map<String,String> adminIngrRm(String list) {
		Map<String,String> result = new HashMap<>();
		List<String> rmList = new ArrayList<>(Arrays.asList(list.split(",")));
		try {
			recipeSVC.deleteIngredient(rmList);
			result.put("result", "y");
		} catch (Exception e) {
			result.put("result", "n");
		}
		return result;
	}
	
	//============================== 속성 추가 && 삭제 ==============================//
	@RequestMapping("/admin/ingredient/addattr")
	public @ResponseBody Map<String,String> adminIngrAddAttr(String list, String top, String sub) {
		Map<String,String> result = new HashMap<>();
		try {
			Map map = attributeDataRefine(list, top, sub);
			recipeSVC.insertIngrAttribute(map);
			result.put("result", "y");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류발생");
			result.put("result", "n");
		}
		return result;
	}
	
	@RequestMapping("/admin/ingredient/rmattr")
	public @ResponseBody Map<String,String> adminIngrRemoveAttr(String list, String top, String sub) {
		Map<String,String> result = new HashMap<>();
		try {
			Map map = attributeDataRefine(list, top, sub);
			recipeSVC.deleteIngrAttribute(map);
			result.put("result", "y");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류발생");
			result.put("result", "n");
		}
		return result;
	}
	
	private Map attributeDataRefine(String list, String top, String sub) {
		Map map = new HashMap();
		List<String> idList = new ArrayList<>(Arrays.asList(list.split(",")));
		map.put("list", idList);
		map.put("top", top);
		map.put("id", Integer.parseInt(sub));
		return map;
		
	}
	
	// 상위 카테고리에 대응하는 하위카테고리 정보를 반환
	@RequestMapping("/admin/ingredient/catdata")
	public @ResponseBody List<CatSubVO> adminIngrCatData(String cat) {
		return recipeSVC.getingrCatData(cat);
	}
	
	
	//============================== 카테고리 추가 && 삭제 ==============================//
	@RequestMapping("/admin/ingredient/category")
	public String adminIngrCat(String cat, Model model) {
		if (cat==null) { cat = "religion"; }
		Map<String, String> map = new HashMap<>();
		map.put("tablename", cat);
		List<CatSubVO> list = recipeSVC.selectCatInfo(map);
		model.addAttribute("cat", cat);
		model.addAttribute("list", list);
		return "admin/ingredient_cat";
	}
	
	@RequestMapping("/admin/ingredient/category/mod")
	public @ResponseBody Map<String, String> adminIngrCatModify(@RequestBody Map<String, Object> data) {
		Map<String, String> result = new HashMap<>();
		try {
			String cat = (String) data.get("cat");
			List<Map<String, String>> list = (List<Map<String, String>>) data.get("list");
			List<String> insert = new ArrayList<>();
			List<Integer> delete = new ArrayList<>();
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
	
	//============================== 식당 목록 ==============================//
	@RequestMapping("/admin/restaurant/list")
	public String adminRestaurantList(PagingManager page, Model model) {
		page.setRowSize(20);
		int total = restDAO.restaurantTotal();
		Map<String, Integer> map = page.calcPage(total);
		List<RestaurantVO> list = restDAO.restaurantAdminList(map);

		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/restaurant_list";
	}
	
	@RequestMapping("/admin/restaurant/detail")
	public String adminRestaurantDetail(int id,Model model){
		RestaurantVO vo=restDAO.restaurantDetail(id);
		String sigun=restDAO.restaurantsigun(id);
		vo.setSigun(sigun);
		model.addAttribute("vo", vo);
		return "restaurant/restaurant_detail";
	}
	
	@RequestMapping("/admin/restaurant/addrfind")
	public String restaurantFindAddress(String address,Model model){
		List<AddressVO> vo=restDAO.restaurantsigunAll(address);
		int count=vo.size();
		model.addAttribute("vo",vo);
		model.addAttribute("count", count);
		return "findaddr";
	}

	@RequestMapping("/admin/restaurant/insert_ok")
	public String restaurantInsertOk(RestaurantVO vo,MultipartFile mainFile,HttpSession session){

		restaurantSVC.restaurantInsert(vo, mainFile, session);
		
		return "redirect:/admin/restaurant/list";
	}
	@RequestMapping("/admin/restaurant/insert")
	public String restaurantInsert(){
		return "admin/restaurant_insert";
	}
	@RequestMapping("/admin/restaurant/delete")
	public String restaurantDelete(int[] chk){
		try{
			restaurantSVC.restaurantDelete(chk);
		}catch(Exception e){
			System.out.println("삭제 실패");
		}
		return "redirect:/admin/restaurant/list";
	}
	//============================== 회원 목록 ==============================//
	@RequestMapping("/admin/users/list")
	public String adminUsersList(PagingManager page, Model model) {
		page.setRowSize(20);
		Map<String, Integer> map = page.calcPage(userSVC.selectUserTotal());
		List<UsersVO> list = userSVC.selectUserList(map);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/users_list";
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
	
	//============================== 부가정보 출력 ==============================//
	@RequestMapping("/admin/tag/list")
	public String adminTagsList(PagingManager page, Model model) {
		page.setRowSize(100);
		Map<String, Integer> map = page.calcPage(recipeSVC.recipeTagTotal());
		List<RecipeTagVO> list = recipeSVC.recipeTagList(map);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/tag_list";
	}
	
	@RequestMapping("/admin/log/login")
	public String adminLogLogin(PagingManager page, Model model) {
		page.setRowSize(100);
		int total = userSVC.selectLogLoginTotal();
		Map<String, Integer> map = page.calcPage(total);
		List<LogLoginVO> list = userSVC.selectLogLoginList(map);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/log_login";
	}
		
	@RequestMapping("/admin/log/search")
	public String adminLogSearch(PagingManager page, Model model) {
		page.setRowSize(100);
		Map<String, Integer> map= page.calcPage(searchDAO.logSearchTotal());
		List<LogSearch> list = searchDAO.logSearchList(map);
		model.addAttribute("pmgr", page);
		model.addAttribute("list", list);
		return "admin/log_search";
	}
	
}

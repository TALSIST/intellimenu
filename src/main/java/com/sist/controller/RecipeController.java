package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.TestVO;
import com.sist.recipe.CatSubDAO;
import com.sist.recipe.RecipeDAO;
import com.sist.vo.CatSubVO;
import com.sist.vo.Recipe;

@Controller
public class RecipeController {
	
	@Autowired
	private CatSubDAO catSubDAO;	
	@Autowired
	private RecipeDAO recipeDAO;
	
	
	@RequestMapping("recipe/recipe_insert")
	 public String recipe_insert(Model model){
	
		
		
		return "recipe/recipe_insert";
	 }
	@RequestMapping("recipe/recipie_test")
	public String test(String recipe_title, TestVO vo){	
		List <MultipartFile> list=vo.getUpload();
		System.out.println("cut");
		
		System.out.println(list.size());
		for(MultipartFile ls: list){
			System.out.println("cut");
			System.out.println(ls.getName());
		}
		
		
		
		System.out.println(recipe_title);
		return "recipe/recipe_insert";
	}
	
	
	
	@RequestMapping("recipe/recipe_detail")
	public String recipeDetail(int id, Model model){
		
		model.addAttribute("id", id);
		return "recipe/recipe_detail";
	}
	
	@RequestMapping("recipe/recipe_main")
	public String recipeList(Model model){
		
		List<CatSubVO> list1= catSubDAO.selectList(1);//종류별 리스트 가져오기
		List<CatSubVO> list2= catSubDAO.selectList(2);//상황별 리스트 가져오기
				
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		
		System.out.println("리스트 사이즈 "+list1.size());
		
		return "recipe/recipe_main";
	}
	
	
	
	/**
	 * @param page
	 * @param id
	 * @param name
	 * @param model
	 * @return
	 * 
	 * 레시피 홈에서 상황별, 종류별 카테고리를 선택했을때 
	 * cat_sub_id를 통해서 해당 id를 가진 recipe목록을 가져온다.
	 */
	@RequestMapping("recipe/recipe_sublist")
	public String recipeSubList(int id, String name, Model model){
		/*if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		
		//mybatis mappter에 사용할 map
		Map map=new HashMap();
		map.put("cat_sub_id", id);
		
		int rowSize=9;		
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);*/
		//List<Recipe> list=service.CatSubRecipeListData(map);
		
		
		//model.addAttribute("page", page);
		model.addAttribute("name", name);
		model.addAttribute("id", id);
		
		return "recipe/recipe_sublist";
	}
	
	
}

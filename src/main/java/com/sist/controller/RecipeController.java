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
import com.sist.util.TagsManager;
import com.sist.vo.CatSubVO;
import com.sist.vo.Recipe;
import com.sist.vo.RecipeContent;

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
	public String test(String recipe_title, TestVO vo,String tags){	
		List <MultipartFile> list=vo.getStepsFile();
		//System.out.println("cut");
		
	/*	System.out.println(list.size());
		for(MultipartFile ls: list){
			System.out.println("cut");
			System.out.println(ls.isEmpty());
		}*/
		
		
		for(String v:TagsManager.TagsAllData(tags)){
			System.out.println(v);
		}
		
		
		System.out.println(recipe_title);
		return "recipe/recipe_insert";
	}
	
	
	
	@RequestMapping("recipe/recipe_main")
	public String recipeList(Model model){
		
		List<CatSubVO> list= catSubDAO.selectList(1);//종류별 리스트 가져오기
				
		model.addAttribute("list", list);
		
		
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
	public String recipeSubList(String page, int cat_sub_id, String name, Model model){
		//page= 인채로 보내면 안된다. ""으로 인식?
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		
		//mybatis mappter에 사용할 map
		Map map=new HashMap();
		map.put("cat_sub_id", cat_sub_id);
		
		int rowSize=9;		
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<Recipe> list=recipeDAO.catSubRecipeListData(map);
		for (Recipe vo : list) {
			System.out.println(vo.getImg_ori());
			
			//사용자가 올린 이미지가 아니라 웹에서 가져온 이미지면 oriname을 사용한다.
			if (vo.getImg_new().equals("imgfromweb")) {
				vo.setImg(vo.getImg_ori());
			}else{
				vo.setImg(vo.getImg_new());				
			}
			
		}
		
		//totalpage 구하기
		int totalpage=recipeDAO.catSubRecipeListTotalPage(cat_sub_id);
		
		model.addAttribute("list", list);		
		model.addAttribute("page", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("name", name);
		model.addAttribute("cat_sub_id", cat_sub_id);
		
		return "recipe/recipe_sublist";
	}
	
	@RequestMapping("recipe/recipe_detail")
	public String recipeDetail(int id, Model model){
		
		Recipe recipe=recipeDAO.recipeDetail(id);
		
		//사용자가 올린 이미지가 아니라 웹에서 가져온 이미지면 oriname을 사용한다.
		if (recipe.getImg_new().equals("imgfromweb")) {
			recipe.setImg(recipe.getImg_ori());
		}else{
			recipe.setImg(recipe.getImg_new());				
		}
		
		
		//조리순서 가져오기
		List<RecipeContent> contentList=recipeDAO.recipeDetailContent(id);
		System.out.println("contentlistsize는 "+contentList.size());
		for (RecipeContent vo : contentList) {
			System.out.println(vo.getImg_ori());
			
			//사용자가 올린 이미지가 아니라 웹에서 가져온 이미지면 oriname을 사용한다.
			if (vo.getImg_new().equals("imgfromweb")) {
				vo.setImg(vo.getImg_ori());
			}else{
				vo.setImg(vo.getImg_new());				
			}
			
		}
		
		
		model.addAttribute("id", id);
		model.addAttribute("recipe", recipe);
		model.addAttribute("contentList", contentList);
		return "recipe/recipe_detail";
	}
	
	@RequestMapping("recipe/recipe_main_test")
	public String recipeMainTest(){
		
		return "recipe/recipe_main_test";
	}
	
	
}

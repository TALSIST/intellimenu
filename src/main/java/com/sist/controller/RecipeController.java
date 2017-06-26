package com.sist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.TestVO;
import com.sist.recipe.CatSubDAO;
import com.sist.vo.CatSubVO;

@Controller
public class RecipeController {
	
	@Autowired
	private CatSubDAO catSubDAO;
	
	
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
	
	
	
	@RequestMapping("recipe/recipeDetail")
	public String recipeDetail(int id, Model model){
		
		model.addAttribute("id", id);
		return "recipe/recipeDetail";
	}
	
	@RequestMapping("recipe/recipeList")
	public String recipeList(Model model){
		
		List<CatSubVO> list1= catSubDAO.selectList(1);//종류별 리스트 가져오기
		List<CatSubVO> list2= catSubDAO.selectList(2);//상황별 리스트 가져오기
				
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		
		System.out.println("리스트 사이즈 "+list1.size());
		
		return "recipe/recipeList";
	}
	
	@RequestMapping("recipe/recipeSubList")
	public String recipeSubList(int id, Model model){
		
		model.addAttribute("id", id);
		
		return "recipe/recipeSubList";
	}
	
	
}

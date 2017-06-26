package com.sist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.TestVO;
import com.sist.recipe.Cat_subDAO;
import com.sist.vo.Cat_subVO;

@Controller
public class RecipeController {
	
	@Autowired
	private Cat_subDAO cat_subDAO;
	
	
	@RequestMapping("recipe/recipe_insert")
	 public String recipe_insert(Model model){
	
		
		
		return "recipe/recipe_insert";
	 }
	
	@RequestMapping("recipe/recipie_test")
	public String test(String recipe_title, TestVO vo){	
		List <MultipartFile> list=vo.getStepsFile();
		System.out.println("cut");
		
		System.out.println(list.size());
		for(MultipartFile ls: list){
			System.out.println("cut");
			System.out.println(ls.isEmpty());
		}
		
		
		
		System.out.println(recipe_title);
		return "recipe/recipe_insert";
	}
	
	
	@RequestMapping("recipe/recipe_detail")
	public String recipe_detail(int id, Model model){
		
		model.addAttribute("id", id);
		return "recipe/recipe_detail";
	}
	
	@RequestMapping("recipe/recipe_list")
	public String recipe_list(Model model){
		
		List<Cat_subVO> list1= cat_subDAO.select_list(1);//종류별 리스트 가져오기
		List<Cat_subVO> list2= cat_subDAO.select_list(2);//상황별 리스트 가져오기
				
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		
		System.out.println("리스트 사이즈 "+list1.size());
		
		return "recipe/recipe_list";
	}
	
	@RequestMapping("recipe/recipe_sub_list")
	public String recipe_sub_list(int id, Model model){
		
		model.addAttribute("id", id);
		
		return "recipe/recipe_sub_list";
	}
	
	
}

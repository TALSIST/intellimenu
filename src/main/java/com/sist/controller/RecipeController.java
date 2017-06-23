package com.sist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.TestVO;

@Controller
public class RecipeController {
 
	@RequestMapping("recipe/recipe_insert")
	 public String recipe_insert(Model model){
	
		
		
		return "recipe/recipe_insert";
	 }
	@RequestMapping("recipe/recipie_test")
	public String test(String recipe_title,TestVO vo){	
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
	public String recipe_detail(int id, Model model){
		
		model.addAttribute("id", id);
		return "recipe/recipe_detail";
	}
}

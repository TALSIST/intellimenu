package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
 
	@RequestMapping("recipe_insert")
	 public String recipe_insert(Model model){
		
		
		model.addAttribute("page","recipe-insert.jsp");
		 return "defalut";
	 }
}

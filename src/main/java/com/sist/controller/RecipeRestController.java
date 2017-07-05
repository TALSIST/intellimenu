package com.sist.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.recipe.CatSubDAO;
import com.sist.recipe.RecipeInsertDAO;
import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;

@RestController
public class RecipeRestController {
	@Autowired
	CatSubDAO catsubDAO;
	@Autowired
	RecipeInsertDAO recipeInsertDAO;
	
	@RequestMapping("/recipe/getsubcategory")
	public  List <CatSubVO> getSubCategory(int id){
		System.out.println("등장");
		List <CatSubVO> list=catsubDAO.selectList(id);
			
																	

		return list;
		
		}
	@RequestMapping("/recipe/geting")
	public List<IngredientVO> getIng(String value){
		List<IngredientVO> list=recipeInsertDAO.selectIngr(value);
		
		return list;
	}
	@RequestMapping("/recipe/ingck")
	public int ingck(String value){
		int ck=recipeInsertDAO.selectIngCk(value);
		System.out.println(ck);
	
		
		
		return ck;
	}
		
	
 

		
		
	
	
}

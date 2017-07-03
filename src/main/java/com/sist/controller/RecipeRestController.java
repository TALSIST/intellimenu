package com.sist.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.recipe.CatSubDAO;
import com.sist.vo.CatSubVO;

@RestController
public class RecipeRestController {
	@Autowired
	CatSubDAO catsubDAO;
	@RequestMapping("/recipe/getSubCategory")
	public  List <CatSubVO> getSubCategory(int id){
		System.out.println("등장");
		List <CatSubVO> list=catsubDAO.selectList(id);
			
																	

		return list;
		
		}


		
		
	
	
}

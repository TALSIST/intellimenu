package com.sist.controller;

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
	@RequestMapping("recipe/getSubCategory")
	public String getSubCategory(int id){
		System.out.println("등장");
		List <CatSubVO> list=catsubDAO.selectList(id);
		JSONObject obj = new JSONObject();
		
		JSONArray jArray = new JSONArray();//
		for (int i = 0; i < list.size(); i++)//배열
		{
		JSONObject sj = new JSONObject();//배열 내에 들어갈 json
		sj.put("id",list.get(i).getId());
		sj.put("name", list.get(i).getName());
		jArray.add(sj);
		}
		obj.put("data",jArray);
		

		return obj.toString();
		
		}


		
		
	
	
}

package com.sist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.rank.RankDAO;
import com.sist.vo.CatTopVO;
import com.sist.vo.RecipeVO;

public class RankController {
	
	@Autowired
	private RankDAO rankDAO;
	
	@RequestMapping("rank/ranking_hit")
	 public String ranking(Model model){
	
		List<RecipeVO>list =rankDAO.recipeList();
		model.addAttribute("list", list);
		return "rank/ranking_hit";
	 }
}

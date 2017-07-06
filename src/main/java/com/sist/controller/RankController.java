package com.sist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.rank.RankDAO;
import com.sist.vo.CatTopVO;
import com.sist.vo.RecipeVO;

@Controller
public class RankController {
	
	@Autowired
	private RankDAO rankDAO;

	@RequestMapping("ranking/ranking_hit")
	 public String ranking(Model model){
	
		List<RecipeVO>list =rankDAO.recipeList();
		model.addAttribute("list", list);
		return "ranking/ranking_hit";
	 }

	@RequestMapping("ranking/rank_test")
	 public String rank_test(Model model){
	
		List<RecipeVO>list =rankDAO.recipeList();
		model.addAttribute("list", list);
		return "ranking/rank_test";
	 }
}

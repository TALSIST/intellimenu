package com.sist.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.rank.RankDAO;
import com.sist.rank.RankYM;
import com.sist.vo.CatTopVO;
import com.sist.vo.RecipeVO;

@Controller
public class RankController {
	
	@Autowired
	private RankDAO rankDAO;
/*
	@RequestMapping("ranking/ranking_hit")
	 public String ranking(Model model){
		List<RecipeVO>list =rankDAO.recipeList();
		model.addAttribute("list", list);
		return "ranking/ranking_hit";
	 }
*/
	@RequestMapping(value="/ranking/rank_test")
	 public String rank_test(Model model){
		List<RecipeVO>list =rankDAO.recipeR();
		model.addAttribute("list", list);
		return "/ranking/rank_test";
	 }
	@RequestMapping(value="/ranking/rank_test",method=RequestMethod.POST)
	 public String rank_test(Model model,RankYM ym,HttpServletRequest request){
		String ydate=request.getParameter("ydate");
		String mdate=request.getParameter("mdate");
		if(mdate.length()==1)
		{
			mdate="0"+mdate;
		}
		List<RecipeVO>list =rankDAO.recipeList(ydate,mdate);
		model.addAttribute("list", list);
		return "/ranking/rank_test";
	 }
}

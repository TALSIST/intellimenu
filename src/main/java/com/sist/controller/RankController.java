package com.sist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.rank.RankDAO;
import com.sist.rank.RankYM;
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
		String Dday=ydate+mdate;
		
		String lastdate=rankDAO.Lastday(Dday);
		String Firstday=Dday+"01";
		Map<String,String> map=new HashMap<String,String>();
		map.put("Firstday", Firstday);
		map.put("lastdate", lastdate);
		System.out.println(map.get("Firstday"));
		System.out.println(map.get("lastday"));
		List<RecipeVO>list =rankDAO.recipeList(map);
		
		model.addAttribute("list", list);
		return "/ranking/rank_test";
	 }
}

package com.sist.controller;

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

	@RequestMapping("/ranking/ranking_hit")
	 public String ranking(RankYM ym, Model model){
		String ydate = ym.getYdate();
		String mdate = ym.getMdate();
		List<RecipeVO> list = null;
		
		if (ydate == null || mdate == null) {
			list =rankDAO.recipeR();
			
		} else {
			if(mdate.length()==1) { mdate="0"+mdate; }
			String Dday=ydate+mdate;
			String lastdate=rankDAO.Lastday(Dday);
			String Firstday=Dday+"01";
			Map<String,String> map=new HashMap<String,String>();
			map.put("Firstday", Firstday);
			map.put("lastdate", lastdate);
			list =rankDAO.recipeList(map);
			model.addAttribute("ydate",ydate);
			model.addAttribute("mdate",mdate);
		}
		
		model.addAttribute("list", list);
		return "/ranking/ranking_hit";
	 }
	
}

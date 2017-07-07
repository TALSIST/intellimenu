package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.recipe.RecipeDAO;
import com.sist.util.PagingManager;
import com.sist.vo.FavoriteVO;
import com.sist.vo.RecipeVO;
import com.sist.vo.UsersVO;

@Controller
public class FavoriteController {

	@Autowired
	private RecipeDAO dao;
	
	@RequestMapping("favorite/favorite_test")
	public ModelAndView favoriteList(ModelAndView mav,HttpSession session,PagingManager page){
		
		int user_id=((UsersVO)session.getAttribute("user")).getId();
		
		page.setRowSize(9);
		Map<String, Integer> pageCal = page.calcPage(550);
		
		//페이지 리스트 뽑기
		Map map=new HashMap();
		map.put("start", pageCal.get("start"));
		map.put("end", pageCal.get("end"));
		map.put("user_id", user_id);
		List<RecipeVO> favoriteList=dao.favoriteList(map);
		
		//총페이지 구하기
		int totalpage=dao.totalFavoritepage(user_id);
		
		Map<String, Object> map1=new HashMap<String,Object>();
		
		//화면 출력하기	
		map1.put("favoriteList", favoriteList);
		map1.put("count",favoriteList.size());
		map1.put("totalpage",totalpage);
		map1.put("page",page.getPage());
		map1.put("user_id", user_id);
		mav.addObject("map",map1);
		mav.setViewName("recipe/favorite");
		
		return mav;
	}
	
	@RequestMapping("favorite/favorite_delete")
	public String favoriteDelete(@RequestBody Map map){
		
	
		
	String [] check=map.get("check").toString().split(",");
	
	for(int i=0;i<check.length;i++){
		
		int id=Integer.parseInt(check[i]);
		dao.favoriteDelete(id);
		
	}
	
	return "redirect:/favorite/favorite_test";
	
	}
	
}

package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.main.MainService;
import com.sist.util.PagingManager;
import com.sist.vo.RecipeVO;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping("/")
	public String home(Model model) {
		Map map=new HashMap();	
		
		Map result=mainService.homeMain(map);
		
		model.addAttribute("result", result);		
		return "default";
	}
		
	
	@RequestMapping("/board")
	public String paging_test(PagingManager page) {
		// 얻어온 페이지 출력
		System.out.println(page.getPage());
		// 계산한 페이지 출력
		Map<String, Integer> map = page.calcPage(550);
		System.out.println("시작"+map.get("start"));
		System.out.println("끝"+map.get("end"));
		System.out.println("block시작"+page.getStartBlock());
		System.out.println("block끝"+page.getEndBlock());
		System.out.println("이전버튼"+page.getPrevBtn());
		System.out.println("다음버튼"+page.getNextBtn());
		return "default";
	}
	
}

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
	
}

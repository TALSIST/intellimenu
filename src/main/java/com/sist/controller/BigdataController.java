package com.sist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.bigdata.BigDataService;

@Controller
public class BigdataController {
	
	@Autowired
	private BigDataService BigDataService;
	
	@RequestMapping("/bigdata/bigdata_main")
	public String bigdata_main(Model model){
		
		BigDataService.helloExample(model);//안녕출력
		BigDataService.getWeather(model);//날씨 출력
		
		return "/bigdata/bigdata_main";
	}
	
	
}

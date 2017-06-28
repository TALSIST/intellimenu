package com.sist.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.util.PagingManager;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {
		return "default";
	}
	
	@RequestMapping("/search")
	public String search_main() {
		
		return "";
	}
	
	@RequestMapping("/board")
	public String paging_test(PagingManager page) {
		// 얻어온 페이지 출력
		System.out.println(page.getPage());
		// 계산한 페이지 출력
		Map<String, Integer> map = page.calcPage(550);
		System.out.println("시작"+map.get("start"));
		System.out.println("끝"+map.get("end"));
		System.out.println("block시작"+map.get("blockstart"));
		System.out.println("block끝"+map.get("blockend"));
		System.out.println("이전버튼"+map.get("prevbtn"));
		System.out.println("다음버튼"+map.get("nextbtn"));
		return "default";
	}
	
}

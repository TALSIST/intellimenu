package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.main.MainService;
import com.sist.spark.IngrRankDAO;
import com.sist.spark.IngrRankVO;
import com.sist.util.PagingManager;
import com.sist.vo.RecipeVO;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private IngrRankDAO dao;
	
	@RequestMapping("/")
	public String home(Model model) {
		Map map=new HashMap();	
		
		Map result=mainService.homeMain(map);
		
		model.addAttribute("result", result);		
		return "default";
	}
	
	
	//@RestController 쓰면 @RequestMapping만 써도 된다.
	@RequestMapping("/main/graph")
	@ResponseBody
	public String main_graph(Model model){
		System.out.println("graph에 들어오나?");
		
		//hadoopFileRead();
		
		
		String[] color={"#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01"
						, "#B0DE09", "#04D215", "#0D8ECF", "#0D52D1", "#8A0CCF"};
		List<IngrRankVO> list=dao.ingrAllData();
		JSONArray arr=new JSONArray();
		
		int i=0;
		for (IngrRankVO vo : list) {
			JSONObject obj=new JSONObject();
			obj.put("country", vo.getName());
			obj.put("visits", vo.getCount());
			obj.put("color", color[i]);
			arr.add(obj);
			i++;
			
		}
		
		
		return arr.toJSONString();
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

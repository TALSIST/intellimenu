package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sist.search.MappingJsonParser;
import com.sist.search.SearchService;
import com.sist.util.PagingManager;
import com.sist.vo.RecipeVO;

@Controller
public class SearchContorller {
	
	@Autowired
	private ServletContext servletContext;
	
	//WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	//여기서는 인식을 못하네?
	
	@Autowired
	private MappingJsonParser mappingJsonParser;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("search/search_result")
	public String recipeSearchResult(PagingManager page, String searchParam, String searchKeyword,  Model model){
		/*String searchServiceName="com.sist.search."+mappingJsonParser.jsonParse(searchParam);			
		try {
			Class searchServiceClass=Class.forName(searchServiceName);
			searchService=(SearchService) searchServiceClass.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}//이렇게 하면 스프링을 이용할 수 없게 된다.*/
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		String searchServiceName=mappingJsonParser.jsonParse(searchParam);	//id를 따로 안주면 앞글자가 소문자인게 id가 된다.
		System.out.println(searchServiceName);
		searchService=(SearchService) webApplicationContext.getBean(searchServiceName);
		
		int total=searchService.getKeywordSearchTotal(searchKeyword);
		page.setRowSize(9);
		Map pageCal=page.calcPage(total);		
		Map map=new HashMap();
		map.put("searchKeyword", searchKeyword);
		map.put("start", pageCal.get("start"));
		map.put("end", pageCal.get("end"));
		
		
		List<RecipeVO> recipeList=searchService.keywordSearch(map);
		for (RecipeVO vo : recipeList) {
			if (vo.getImg_new().equals("imgfromweb")) {
				vo.setImg(vo.getImg_ori());
			}else{
				vo.setImg(vo.getImg_new());				
			}
			
		}
		//System.out.println("recipeList크기는"+recipeList.size());
		
		
		model.addAttribute("recipeList", recipeList);
		model.addAttribute("totalPage", page.getTotalPage());
		model.addAttribute("page", page.getPage());
		model.addAttribute("searchParam", searchParam);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "search/search_result";
	}
	
}

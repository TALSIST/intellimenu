package com.sist.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.users.UsersService;
import com.sist.vo.RecipeVO;

@Service
public class TotalSearchService {

	@Autowired
	private IngrSearchService ingrSearchService;
	
	@Autowired
	private TagSearchService tagSearchService;
	
	@Autowired
	private TitleSearchService titleSearchService;
	
	@Autowired
	private UsersService usersService;
	
	
	public Map<String, List<RecipeVO>> totalKeywordSearch(Map map) {
		System.out.println("검색들어왔어");
		System.out.println(map.get("searchKeyword"));
		
		
		Map<String, List<RecipeVO>> result=new HashMap<String, List<RecipeVO>>();
		List<RecipeVO> titleSearchResult=titleSearchService.keywordSearch(map);
		for (RecipeVO vo : titleSearchResult) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}		
		List<RecipeVO> ingrSearchResult=ingrSearchService.keywordSearch(map);
		for (RecipeVO vo : ingrSearchResult) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}		
		List<RecipeVO> tagSearchResult=tagSearchService.keywordSearch(map);
		for (RecipeVO vo : tagSearchResult) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}
		
		System.out.println(titleSearchResult.size());
		
		result.put("titleSearchResult", titleSearchResult);
		result.put("ingrSearchResult", ingrSearchResult);
		result.put("tagSearchResult", tagSearchResult);
				
		return result;
	}
	
	
	

}

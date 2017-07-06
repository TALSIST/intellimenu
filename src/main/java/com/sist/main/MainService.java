package com.sist.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.search.SearchDAO;
import com.sist.search.TitleSearchService;
import com.sist.users.UsersService;
import com.sist.vo.LogSearch;
import com.sist.vo.RecipeVO;

@Service
public class MainService {
	
	@Autowired
	private SearchDAO searchDAO;
	
	@Autowired
	private TitleSearchService titleSearchService;
	
	@Autowired
	private UsersService usersService;
	
	public Map<String, List<RecipeVO>> homeMain(Map map){
		Map result=new HashMap();
		
		LogSearch first=new LogSearch();
		List<LogSearch> logSearchRankList=searchDAO.getLogSearchRankList();
		for (LogSearch l : logSearchRankList) {
			if (l.getNum()==1) {
				first=l;
			}
			
		}		
		Map search =new HashMap();
		search.put("searchKeyword", first.getKeyword());
		search.put("start", 1);
		search.put("end", 3);		
		List<RecipeVO> logSearchRankRecipeList=titleSearchService.keywordSearch(search);
		for (RecipeVO recipeVO : logSearchRankRecipeList) {
			recipeVO.setImgAuto();
			recipeVO.setNickname(usersService.selectNickName(recipeVO.getUser_id()));
		}
		
		result.put("logSearchRankList", logSearchRankList);
		result.put("logSearchRankRecipeList", logSearchRankRecipeList);
		return result;
	}
	
}

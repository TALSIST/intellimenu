package com.sist.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.search.SearchDAO;
import com.sist.vo.LogSearchVO;
import com.sist.vo.RecipeVO;

@Service
public class MainService {
	
	@Autowired
	private SearchDAO searchDAO;
	
	public Map<String, List<RecipeVO>> homeMain(Map map){
		Map result=new HashMap();
		
		List<LogSearchVO> logSearchRankList=searchDAO.getLogSearchRankList();
		
		
		
		result.put("logSearchRankList", logSearchRankList);		
		return result;
	}
	
}

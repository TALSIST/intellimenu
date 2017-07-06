package com.sist.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.LogSearch;

@Repository
public class SearchDAO {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public int logSearchTotal() {
		return searchMapper.logSearchTotal();
	}
	
	public List<LogSearch> logSearchList(Map map) {
		return searchMapper.logSearchList(map);
	}
	
	public void logSearchInsert(String keyword){
		
		searchMapper.logSearchInsert(keyword);
	};

	public List<LogSearch> getLogSearchRankList(){
		
		return searchMapper.getLogSearchRankList();
	};

	
}

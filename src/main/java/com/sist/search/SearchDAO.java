package com.sist.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.LogSearch;

@Repository
public class SearchDAO {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public void logSearchInsert(String keyword){
		
		searchMapper.logSearchInsert(keyword);
	};

	public List<LogSearch> getLogSearchRankList(){
		
		return searchMapper.getLogSearchRankList();
	};

	
}

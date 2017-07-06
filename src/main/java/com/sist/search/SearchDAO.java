package com.sist.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAO {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public void logSearchInsert(String keyword){
		
		searchMapper.logSearchInsert(keyword);
	};

	
	
}

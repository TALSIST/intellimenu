package com.sist.search;

import org.apache.ibatis.annotations.Insert;

public interface SearchMapper {

	@Insert("Insert Into log_search(keyword) Values(#{keyword})")
	public void logSearchInsert(String keyword);
	
}

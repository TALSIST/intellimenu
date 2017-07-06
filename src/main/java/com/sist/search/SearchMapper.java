package com.sist.search;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.LogSearch;

public interface SearchMapper {

	@Insert("Insert Into log_search(keyword) Values(#{keyword})")
	public void logSearchInsert(String keyword);
	
	@Select("Select * FROM SEARCHKEYWORDRANK")
	public List<LogSearch> getLogSearchRankList();
}

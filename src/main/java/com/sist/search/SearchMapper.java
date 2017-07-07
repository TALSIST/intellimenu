package com.sist.search;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.LogSearch;

public interface SearchMapper {
	@Select("SELECT COUNT(*) FROM log_search")
	public int logSearchTotal();
	
	@Select("SELECT Y.*, num FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT id, keyword, regdate"
					+ " FROM log_search"
					+ " ORDER BY regdate DESC) X) Y"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<LogSearch> logSearchList(Map map);
	
	@Insert("Insert Into log_search(keyword) Values(#{keyword})")
	public void logSearchInsert(String keyword);
	
	@Select("Select * FROM SEARCHKEYWORDRANK")
	public List<LogSearch> getLogSearchRankList();
}

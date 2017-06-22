package com.sist.util;

import java.util.HashMap;
import java.util.Map;

public class PagingManager {
	
	// 현재 페이지로 게시물 시작과 끝 그리고 block을 구한다
	public Map<String, Integer> calcPage(String page) {
		// 페이지, 게시물번호 처리
		if (page == null) {
			page = "1";
		}
		int currpage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 10;
		int end = rowSize * currpage;
		int start = end - rowSize + 1;
		map.put("end", end);
		map.put("start", start);
		return map;
	}
	
}

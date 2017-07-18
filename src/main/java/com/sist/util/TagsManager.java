package com.sist.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


public  class TagsManager {
	
	
	public static List<String>TagsAllData(String tags){
		List<String> list=new ArrayList<String>();
		String[] a=tags.split(",");
		for (String s :a) {
			list.add(s);
		}
		return list;
		
	}

}

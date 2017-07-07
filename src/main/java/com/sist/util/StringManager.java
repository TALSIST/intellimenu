package com.sist.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

public class StringManager {
	
	public static List<String> stringToList(String words) {
		List<String> list = new ArrayList<String>();
		String[] temp = words.split(",");
		for (String str : temp) {
			list.add(str);
		}
		return list;
	}
	
	public static String listToString(String[] words) {
		return concatString(words);
	}
	
	public static String listToString(List<String> words) {
		return concatString(words.toArray(new String[words.size()]));
	}
	
	private static String concatString(String[] words) {
		String result = "";
		for (String str : words) {
			result += str;
			result += ",";
		}
		result = result.substring(0,result.lastIndexOf(","));
		return result;
	}
	
	
}

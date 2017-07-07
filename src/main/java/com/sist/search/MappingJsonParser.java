package com.sist.search;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.io.*;

@Component
public class MappingJsonParser {
	@Autowired
	private ServletContext servletContext;
	
	public String jsonParse(String searchParam){
		String searchServiceName="";
		
		//System.out.println(servletContext.getRealPath("/WEB-INF/configuration/searchParamMapping.json"));
		String realPath=servletContext.getRealPath("/WEB-INF/configuration/searchParamMapping.json");
		
		FileInputStream fis = null;//응용이기때문에 경로를 정확히 명시해줘야 한다.
		InputStreamReader is = null;
		BufferedReader buffr =null; //업그레이드해서 한줄씩 읽자
		JSONObject jsonObject =null;
		
		try {
			fis=new FileInputStream(new File(realPath));
			is=new InputStreamReader(fis);
			buffr=new BufferedReader(is);
			
			String str;
			StringBuffer sb=new StringBuffer();
			while (true) {
				str=buffr.readLine();
				if (str==null)break;
				sb.append(str);
			}
			System.out.println("json설정파일 내용은"+sb.toString());
			//sb에 json문자열이 모두 누적되어 있으므로, 이 데이터를 대상으로 parsing하자!!
			
			JSONParser jsonParser=new JSONParser();
			jsonObject=(JSONObject) jsonParser.parse(sb.toString());
			searchServiceName=(String) jsonObject.get(searchParam);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (buffr!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (is!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fis!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return searchServiceName;
	}
	
}

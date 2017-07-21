package com.sist.collertor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.io.FileWriter;
import java.util.*;
@Component
public class Lotte {	
	
	
	
	
	
	public static void lotteFile(Configuration  conf,String path){
		try{
		FileSystem fs=FileSystem.get(conf);
		
		
	  
		FileWriter fw=new FileWriter(path+"/food_data/lotte_vegi.txt");
		fw.write(vegiList());
		fw.close();
		FileWriter fw2=new FileWriter(path+"/food_data/lotte_fish.txt");
		fw2.write(fishList());
		fw2.close();
		
	
		fs.copyFromLocalFile(new Path(path+"/food_data/lotte_vegi.txt"),new Path("/food_data/lotte_vegi"));
		fs.copyFromLocalFile(new Path(path+"/food_data/lotte_fish.txt"),new Path("/food_data/lotte_fish"));
		
		fs.close();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String fishList(){
		String lasts="";
		try {
			
			System.out.println("-----------해산물순위------------------");
			Document doc3=Jsoup.connect("http://www.lottemart.com/best/bestMain.do?tabIndex=02&categoryId=C0010015&custom=").get();
			Elements seafood=doc3.select("p.prod-name strong a");
			for(int i=0;i<30;i++){
				Element item=seafood.get(i);
				String s=item.text();
				s=s.replaceAll("\\((.*?)\\)\\)", ""); //노르웨이 왕 고등어자반(1손(2마리))
				s=s.replaceAll(" \\((.*?)\\)", "");
				s=s.replaceAll("\\((.*?)\\)", "");
				s=s.replaceAll(" 볶음", "");
				s=s.replaceAll(" 기획", "");
				s=s.replaceAll(" 필렛", "");
				s=s.replaceAll(" 중", "");
				s=s.replaceAll(" 특대", "");
				s=s.replaceAll(" 슬라이스", "");
				s=s.replaceAll("국산손질", "");
				s=s.replaceAll("\\((.*?)\\)", "");
				String last=s.substring(s.lastIndexOf(" ")+1);
				lasts+=last+"\n";
				
			
			}
			lasts.substring(0,lasts.lastIndexOf("\n"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return lasts;
	}
	
	public static String vegiList(){
		
		String data="";
		try {
			Document doc1=Jsoup.connect("http://www.lottemart.com/best/bestMain.do?tabIndex=02&categoryId=C0010012&custom=").get();
			Elements veg=doc1.select("p.prod-name strong a");
			
			for(int i=0;i<30;i++){
				Element item=veg.get(i);
				String s=item.text();
				s=s.replaceAll(" \\((.*?)\\)", "");
				s=s.replaceAll("\\((.*?)\\)", "");
				s=s.replaceAll("CJ ", "");
				s=s.replaceAll("Soga", "");
				String last=s.substring(s.lastIndexOf(" ")+1);
				data+=last+"\n";
				//System.out.println(last);
				
				
			}
			data.substring(0,data.lastIndexOf("\n"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
		
	}
	
	

}

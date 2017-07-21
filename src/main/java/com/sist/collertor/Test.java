package com.sist.collertor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {
	  public static void main(String[] args) {
		//System.out.println(new SimpleDateFormat("dd MMM yyyy", new Locale("en", "US")).format(new Date()));
	
		  String[] fish={"emart_fish","homplus_fish.txt","lotte_fish"};
			String[] veig={"emart_vegi","homplus_vegi.txt","lotte_vegi"};
			String[] dump=fish;
			String[] date=new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()).split("-");
			for (int i = 0; i < date.length; i++) {
				System.out.println(date[i]);
				
			}
			
			
	  
	  }
}

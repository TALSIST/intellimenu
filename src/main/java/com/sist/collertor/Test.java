package com.sist.collertor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class Test {
	@Scheduled(fixedRate=1000)
	public void baigDate(){
		System.out.println("gd");
	}
	
	  public static void main(String[] args) {
		//System.out.println(new SimpleDateFormat("dd MMM yyyy", new Locale("en", "US")).format(new Date()));
		  String path="/home/sist/bigDateSpring/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/intellimenu/food_data/";
		File a=new File(path+"fish");
		if(a.exists()){
			for(File s:a.listFiles()){
			s.delete();
			}
			a.delete();
			System.out.println(a.exists());
		}
				
	  }
			
			
	  
	  
}

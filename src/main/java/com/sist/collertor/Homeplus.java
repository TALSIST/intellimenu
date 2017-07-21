package com.sist.collertor;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Homeplus {
	@Autowired
	private static Configuration conf;
	
	/*
	public static void main(String[] args) {
		Homeplus hp=new Homeplus();
		
		
		hp.homeplusFile(0);
		
	}*/
	
	public static void homeplusFileup(Configuration  conf,String path){
		try {
			
			
			
			
			FileSystem fs=FileSystem.get(conf);
			
			
			FileWriter fw=new FileWriter(path+"/homplus_vegi.txt");
			fw.write(homeplusFile(0));
			fw.close();
			FileWriter fw2=new FileWriter(path+"/homplus_fish.txt");
			fw2.write(homeplusFile(1));
			fw2.close();
			
		
			fs.copyFromLocalFile(new Path(path+"/homplus_vegi.txt"),new Path("/food_data/homplus_vegi2.txt"));
			fs.copyFromLocalFile(new Path(path+"/homplus_fish.txt"),new Path("/food_data/homplus_fish2.txt"));
			
			fs.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public static String homeplusFile(int cate){
		 String data="";
		String ur="";
		  //채소 0
		 String a="http://www.homeplus.co.kr/app.exhibition.category.Category.ghs?comm=category.list&cid=40009";    
		  //생선
        String b="http://www.homeplus.co.kr/app.exhibition.category.Category.ghs?comm=category.list&cid=40012";
	   
         
         
         if(cate==0){
        	 ur=a;
           }
         else if(cate==1){
        	 ur=b;
         }
         
         else{
        	 return "";
          }
         
		Document  doc;
          
              try {
							doc = Jsoup.connect(ur).get();
							Elements title=doc.select("div.exh-comtxt a.name");
						
							 for (int i = 0; i < title.size(); i++) {
	                                String s=title.get(i).text().trim();
	                                s=s.replaceAll(" ","");
	                                s=s.replaceAll("[0-9]{1,2}마리", "");  
	                                s=s.replaceAll("[0-9]{1,2}손", "");
	                                s=s.replaceAll("[0-9]{1,2}봉", "");
	                                s=s.replaceAll("[0-9]{1,2}개입", "");
	                                s=s.replaceAll("[0-9]{1,2}매", "");
	                               // s=s.replaceAll("\\((.*./?)\\)", "");
	                                s=s.replaceAll("\\[(.*?)\\]", "");
	                                s=s.replaceAll("\\((.*?)\\)", "");
	                                s=s.replace(")", "");
	                                s=s.replaceAll("[0-9a-zA-Z]", "");
	                                s=s.replaceAll("국산","");
	                                s=s.replaceAll("국내산","");
	                                s=s.replace("실속형", "");
	                                s=s.replace("프리미엄", "");
	                                s=s.replace("아삭이", "");
	                                s=s.replace("./망", "");
	                                s=s.replace("/박스", "");
	                                s=s.replace("콩용투컵", "");
	                                s=s.replace("깊은향남도", "");
	                                s=s.replace("유기농", "");
	                                s=s.replace("./", "");
	                                s=s.replace("의령", "");
	                                s=s.replace("순한조미","");
	                                s=s.replaceAll("_","");
	                                s=s.replace("노르웨이","");
	                                s=s.replace("굿다리","");
	                                s=s.replace("실채","");
	                                s=s.replace("손질 봉","");
	                                s=s.replace("손질생","");
	                                s=s.replace("손질","");
	                                s=s.replace("생제주","");
	                                s=s.replace("원양","");
	                                s=s.replace("해물","");
	                                s=s.replace("깐","");
	                                s=s.replace("제주","");
	                                s=s.replace("안옥남맛","");
	                                s=s.replace("다시팩","");
	                                s=s.replace("슬라이스","");	
	                                s=s.replace("우리재래","");
	                                s=s.replace("간편한","");
	                                s=s.replace("남해안","");
	                                s=s.replace("고흥","");
	                                s=s.replace("찌개","");
	                                s=s.replace("예냉","");
	                                s=s.replace("웰빙모둠","");
	                                s=s.replace("종가집","");	
	                                s=s.replace("취청","");	
	                                s=s.replace("콩찌개용투컵","");	
	                                s=s.replace("파주장단","");	
	                                s=s.replace("무농약전주","");
	                                s=s.replace("풀무원네컵","");
	                                s=s.replace("미니","");
	                                s=changeword(s,"김밥용");
	                                s=changeword(s,"도시락");
	                                s=changeword(s,"자연산");
	                                s=changeword(s,"구운");
	                                s=changeword(s,"행복한");
	                                s=changeword(s,"먹는");
	                               s=changeword(s,"부드러운완도");	
	                               s=changeword(s,"반반");	
	                               s=changeword(s,"맛있는");	
	                               s=changeword(s,"조림용");
	                               s=changeword(s,"부드러운");
	                               s=changeword(s,"뿌리없는");	
	                               s=changeword(s,"도시락");	 
	                               s=changeword(s,"기획");	 
	                               s=changeword(s,"구워지는");
	                               s=changeword(s,"깨끗한");	
	                               s=s.replace("콩용투컵","");
	                               s=s.replace("생새우살","새우");
	                               s=s.replace("생칵테일새우","새우");
	                               s=s.replace("백진미","");
	                               s=s.replace("생바지락살","바지락");
	                                   
	                               s=s.replaceAll("씨제이","");
	                               s=s.replaceAll("햇","");
	                               s=s.replaceAll("컷팅","");
	                               s=s.replaceAll("파피","");
	                               s=s.replaceAll("모닝","");
	                               s=s.replaceAll("통","");
	                               s=s.replaceAll("단단","");
	                               s=s.replaceAll("강원","");
	                               s=s.replaceAll("새벽","");
	                               s=s.replaceAll("천년의유산","");
	                               s=s.replaceAll("깊은향을낼수있는","");
	                               s=s.replaceAll("뿌리","");
	                               s=s.replaceAll("농협","");
	                               s=s.replaceAll("패밀리모듬","");
	                               s=s.replaceAll("패밀리","");
	                               s=s.replaceAll("사보이","");
	                               s=s.replaceAll("^[가-힣]*마늘","마늘");
	                               s=s.replaceAll("^[가-힣]*감자","감자");
	                               s=s.replaceAll("^[가-힣]*삼계[가-힣]*\\*","닭고기");
	                               s=s.replaceAll("^[가-힣]*삼계[가-힣]*","닭고기");
	                               s=s.replaceAll("^[가-힣]*표고[가-힣]*","표고버섯");
	                               s=s.replaceAll("^[가-힣]*연근[가-힣]*","연근");
	                               s=s.replaceAll("^[가-힣]*쑥[가-힣]*","쑥");
	                               s=s.replaceAll("^[가-힣]*오이[가-힣]*","오이");
	                               s=s.replaceAll("^[가-힣]*양파[가-힣]*","양파");
	                               s=s.replaceAll("^[가-힣]*우엉[가-힣]*","우엉");
	                               s=s.replaceAll("^[가-힣]*버섯[가-힣]*","버섯");
	                               s=s.replaceAll("^[가-힣]*당근[가-힣]*","당근");
	                               s=s.replaceAll("^[가-힣]*브로콜리[가-힣]*","브로콜리");
	                               s=s.replaceAll("^[가-힣]*파프리카[가-힣]*","파프리카");
	                               		
	                               		
	                               
	                               if(ur.equals(a)){
	                            	   s=s.replaceAll("개","");
	                            	   s=s.replaceAll("입","");
	                            	   s=s.replaceAll("단","");       	   
	                               }
	                               
	                               /////////////////////////////////////////////////////////////
	                               s=s.replaceAll("해동","");
	                               s=s.replaceAll("살삼다","");
	                               s=s.replaceAll("마리","");
	                               s=s.replaceAll("신선","");
	                               s=s.replaceAll("영광","");
	                               s=s.replaceAll("비호","");
	                               s=s.replaceAll("동원","");
	                               s=s.replaceAll("생물","");
	                               s=s.replaceAll("\\/","");
	                               s=s.replaceAll("\\.","");
	                               s=s.replaceAll("활","");
	                               s=s.replaceAll("한입","");
	                               s=s.replaceAll("간편식","");
	                               s=s.replaceAll("서해","");
	                               s=s.replaceAll("살","");
	                               
	                               s=s.replaceAll("^[가-힣]*연어[가-힣]*","연어");
	                               s=s.replaceAll("^[가-힣]*우렁[가-힣]*","우렁");
	                               s=s.replaceAll("^[가-힣]*참치[가-힣]*","참치");
	                               s=s.replaceAll("^[가-힣]*새우[가-힣]*","새우");
	                               s=s.replaceAll("^[가-힣]*고등어[가-힣]*","고등어");
	                               s=s.replaceAll("^[가-힣]*장어[가-힣]*","장어");
	                               s=s.replaceAll("^[가-힣]*명태[가-힣]*","명태");
	                               s=s.replaceAll("^[가-힣]*초밥[가-힣]*","초밥");
	                               s=s.replaceAll("^[가-힣]*가자미[가-힣]*","가자미");
	                               s=s.replaceAll("^[가-힣]*홍어[가-힣]*","홍어");
	                               s=s.replaceAll("^[가-힣]*조기[가-힣]*","조기");
	                               s=s.replaceAll("^[가-힣]*꼬막[가-힣]*","꼬막");
	                              
	                               
	                               
	                               
	                               
	                             /*  for(int k=0;k<m.length;k++){
	                            	     	m[k]=p[k].matcher(s);
	                            	     	if(m[k].find()){
	                            	     		
	                            	     	   }
	                               		 }*/
	                               System.out.println(s);
	                             if(!s.isEmpty()){
	                                data+=s+"\n";
	                                    }
	                        
	                                
	                        }
							data.substring(0,data.lastIndexOf("\n"));
							
							 
						} catch (IOException e) {
						
							e.printStackTrace();
						}
                        //http://homeplus.ssg.com/best/sub.ssg?categoryZoneType=food&selectedCategoryLevel=L&selectedLargeCategoryId=0006150000&selectedMiddleCategoryId=
                     return data;
              
                 }
	
		
	
	
	public static String changeword(String data,String ch){

        int t=data.length();
        data=data.replace(ch,")");
        int z=data.length();
        	
        if (t!=z){
        
         		
        	data=data.substring(data.lastIndexOf(")")+1, data.length());
          
          }
        return data;
        	
		
	}
}

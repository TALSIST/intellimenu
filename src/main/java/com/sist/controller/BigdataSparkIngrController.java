package com.sist.controller;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.sql.types.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.bigdata.MartVO;
import com.sist.collertor.Emart;
import com.sist.collertor.Homeplus;
import com.sist.collertor.Lotte;
import com.sist.collertor.MongoMartDAO;
import com.sist.collertor.MongoMartVO;
import com.sist.spark.SparkRank;



@Controller
public class BigdataSparkIngrController {
	@Autowired
	Homeplus homple;
	
	@Autowired
	Lotte lot;
	@Autowired
	Emart em;
	@Autowired
	Homeplus ho;
	@Autowired
	Configuration conf;
	@Autowired 
	SparkRank spr;
	@Autowired
	ServletContext ctx;
	@Autowired
	MongoMartDAO  dao;
	
	
	//@Scheduled(cron="0 0 10 * * *") //10hour strart
	@RequestMapping("/bigdata/script")
	public String collector(){
		//String path=req.getSession().getServletContext().getContext("/").getRealPath("") ;
		String path=ctx.getRealPath("/food_data/");
		ho.homeplusFileup(conf, path);
		lot.lotteFile(conf, path);
		em.emartFileup(conf, path);
		System.out.println(path);
		sparkrunpage();
		return "bigdata/bigdata_main";
	}
	
	public void sparkrunpage(){
		String path=ctx.getRealPath("/food_data/");
		String[] date=new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()).split("-");
		int year=Integer.parseInt(date[0]);
		int mouth=Integer.parseInt(date[1]);
		int day=Integer.parseInt(date[2]);
		spr.sparkRun("fish", conf, path);
		List<MongoMartVO> flist=spr.poodfileReader(path, "fish");
		spr.sparkRun("vegi", conf, path);
		List<MongoMartVO> vlist=spr.poodfileReader(path, "vegi");
		
		System.out.println(dao.todayCount(year, mouth, day));
	   if(dao.todayCount(year, mouth, day)==0){
		   insertMart(flist,"fish");
		   insertMart(vlist,"vegi");
		}
	   System.out.println("today date forword update !!");
	   
		
		
	}
	public void insertMart(List<MongoMartVO> list,String cate){
		int catecode=99;
		if(cate.equals("fish")){
			catecode=1;
		}else if(cate.equals("vegi")){
			catecode=0;
		}
		if(catecode==99){
			return;
		}
		for(MongoMartVO vo:list){
			vo.setCate(catecode);
			dao.foodInsert(vo);   
		 }
		
	}
	
}

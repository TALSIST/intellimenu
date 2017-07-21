package com.sist.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.collertor.Homeplus;
import com.sist.collertor.Lotte;
import com.sist.spark.sparkRank;

@Controller
public class BigdataTestController {
	@Autowired
	Homeplus homple;
	
	@Autowired
	Lotte lot;
	@Autowired
	Configuration conf;
	@Autowired 
	sparkRank spr;
	
	@RequestMapping("/bigdata/22")
	public String baigDate(HttpServletRequest req){
		String path=req.getSession().getServletContext().getContext("/").getRealPath("") ;
		spr.sparkRun("fish", conf);

		System.out.println(path);

		//lot.lotteFile(conf,path);
		
		homple.homeplusFileup(conf,path);

	  System.out.println("됨?");
	  return "bigdata/bigdata_main";
  }
}

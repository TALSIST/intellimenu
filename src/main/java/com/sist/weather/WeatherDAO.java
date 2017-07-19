package com.sist.weather;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;

@Repository
public class WeatherDAO {
	//@Resource(name="mt")
	//@Autowired
	private MongoTemplate mt;
	
	//sMongoClient mongoClient=new MongoClient();
	
	public String weahterFind(){
		Query query=new Query();
		
		List<WeatherVO> list=mt.find(query, WeatherVO.class, "weather");
		//흐린 날
		return list.get(0).getWeather();
	}
	
	
}

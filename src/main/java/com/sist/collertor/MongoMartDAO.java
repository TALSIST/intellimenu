package com.sist.collertor;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
//0채소
@Component
public class MongoMartDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	public MongoMartDAO() {
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.123",27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("mart");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public  void foodInsert(MongoMartVO vo){
		try {
			   String[] date=new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()).split("-");
			  
			   BasicDBObject obj=new BasicDBObject();
				obj.put("item", vo.getItem());
				obj.put("hit", vo.getHit());
				obj.put("cate", vo.getCate());
				obj.put("year", Integer.parseInt(date[0]));
				obj.put("month",Integer.parseInt( date[1]));
				obj.put("day", Integer.parseInt( date[2]));
				dbc.insert(obj);
					
				
			
				
			}
		 catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public long todayCount(int year,int mouth,int day){
		
		//BasicQuery query=new BasicQuery("{\"year\":2017,\"month\":07,\"day\":23}");
		//Query query=new Query();
		BasicDBObject query=new BasicDBObject();
		query.put("year", year);
		query.put("month", mouth);
		query.put("day", day);
		return dbc.count(query);
		//System.out.println(dbc.count(query));
	}
	public List<MongoMartVO> foodAlldata(){
		List<MongoMartVO > list=new ArrayList<MongoMartVO>();
		try {
			DBCursor cursor=dbc.find();
			
			while (cursor.hasNext()) {
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MongoMartVO vo=new MongoMartVO();
				vo.setItem(obj.getString("item"));
				vo.setHit(obj.getInt("hit"));
				vo.setCate(obj.getInt("cate"));
				vo.setDay(obj.getInt("day"));
				vo.setMonth(obj.getInt("month"));
				vo.setYear(obj.getInt("year"));
				list.add(vo);
				
			}
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		return list;
	}
	public static void main(String[] args) {
		//Hadoop hd=new Hadoop();
		//MongoMartDAO da=new MongoMartDAO("food");
		//List<MongoMartVO> list=hd.poodfileReader();
		//for (MongoMartVO vo:list) {
		//	da.musicInsert(vo);
		//}
	}
}

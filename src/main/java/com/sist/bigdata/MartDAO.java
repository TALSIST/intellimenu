package com.sist.bigdata;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Repository
public class MartDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	
	public MartDAO() {
		try {
			mc = new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.123", 27017)));
			db = mc.getDB("mydb");
			dbc = db.getCollection("mart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MartVO> selectItem(String item) {
		List<MartVO> list = new ArrayList<>();
		try {
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("item",item);
			DBCursor cursor = dbc.find(whereQuery).sort(new BasicDBObject("day", 1));
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				MartVO vo = new MartVO();
				vo.setItem(obj.getString("item"));
				vo.setHit(obj.getInt("hit"));
				vo.setCate(obj.getInt("cate"));
				vo.setDay(obj.getInt("day"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<MartVO> selectItem(int day, int cate) {
		List<MartVO> list = new ArrayList<>();
		try {
			BasicDBObject andQuery = new BasicDBObject();
			List<BasicDBObject> and=new ArrayList<BasicDBObject>();
			and.add(new BasicDBObject("day",day));
			and.add(new BasicDBObject("cate",cate));
			andQuery.put("$and", and);
			DBCursor cursor = dbc.find(andQuery).sort(new BasicDBObject("hit", -1));
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				MartVO vo = new MartVO();
				vo.setItem(obj.getString("item"));
				vo.setHit(obj.getInt("hit"));
				vo.setCate(obj.getInt("cate"));
				vo.setDay(obj.getInt("day"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<MartVO> selectItem(int cate) {
		List<MartVO> list = new ArrayList<>();
		try {
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("cate", cate);
			DBCursor cursor = dbc.find(whereQuery).sort(new BasicDBObject("hit", -1));
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				MartVO vo = new MartVO();
				vo.setItem(obj.getString("item"));
				vo.setHit(obj.getInt("hit"));
				vo.setCate(obj.getInt("cate"));
				vo.setDay(obj.getInt("day"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> selectDays(String item){
		List<String> list=new ArrayList();
		try {
			BasicDBObject whereQuery=new BasicDBObject();
			whereQuery.put("item", item);
			DBCursor cursor=dbc.find(whereQuery).sort(new BasicDBObject("day",1));
			while(cursor.hasNext()){
				BasicDBObject obj=(BasicDBObject) cursor.next();
				String day=obj.getString("day");
				String month=obj.getString("month");
				list.add(month+"월 "+day+"일");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<MartVO> martAllData() {
		List<MartVO> list = new ArrayList<MartVO>();
		try {
			DBCursor cursor = dbc.find().sort(new BasicDBObject("count", -1));
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				MartVO vo = new MartVO();
				vo.setItem(obj.getString("item"));
				vo.setHit(obj.getInt("hit"));
				vo.setCate(obj.getInt("cate"));
				vo.setDay(obj.getInt("day"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

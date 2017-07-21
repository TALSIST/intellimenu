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
/*	public static void main(String[] args) {
		MartDAO md = new MartDAO();
		md.martInsert();
	}
	public void martInsert() {
		String[] str = { "두부", "애호박", "숙주나물", "콩나물", "토마토", };
		Integer[] ints = { 10, 21, 33, 35, 10 };
		try {
			for (int i = 0; i < str.length; i++) {
				BasicDBObject obj = new BasicDBObject();
				obj.put("item", str[i]);
				obj.put("hit", ints[i]);
				obj.put("day", 21);
				dbc.insert(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public List<MartVO> selectItem(String item) {
		List<MartVO> list = new ArrayList<>();
		try {
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("item", item);
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
	
	public List<MartVO> selectHitItem(String item, int hit) {
		List<MartVO> list = new ArrayList<>();
		try {
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("item", item);
			DBCursor cursor = dbc.find(whereQuery).sort(new BasicDBObject("day", 1));
			while (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				if(obj.getInt("hit")>=hit){
					MartVO vo = new MartVO();
					vo.setItem(obj.getString("item"));
					vo.setHit(obj.getInt("hit"));
					vo.setCate(obj.getInt("cate"));
					vo.setDay(obj.getInt("day"));
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<MartVO> selectDay(int day) {
		List<MartVO> list = new ArrayList<>();
		try {
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("day", day);
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

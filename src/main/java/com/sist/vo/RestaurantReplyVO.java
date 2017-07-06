package com.sist.vo;
import java.util.*;

//식당_댓글 
public class RestaurantReplyVO {

	 // PK 
	 private int id;
	
	 // 회원id 
	 private int user_id;
	
	 // 식당id 
	 private int restaurant_id;
	
	 // 댓글 
	 private String reply;
	
	 // 점수 
	 private int score;
	
	 // 등록일 
	 private Date regdate;
	
	 // 원래파일이름 
	 private String img_ori;
	
	 // 저장파일이름 
	 private String img_new;

	 private int report;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getImg_ori() {
		return img_ori;
	}

	public void setImg_ori(String img_ori) {
		this.img_ori = img_ori;
	}

	public String getImg_new() {
		return img_new;
	}

	public void setImg_new(String img_new) {
		this.img_new = img_new;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

}
package com.sist.vo;
import java.util.*;

//식당_댓글 
public class RestaurantReplyVO {
	// PK 
  private int id;

  // 회원id 
  private int userid;

  // 식당id 
  private int restaurantid;

  // 댓글 
  private String reply;

  // 점수 
  private int score;

  // 등록일 
  private Date regdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
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
}
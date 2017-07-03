package com.sist.vo;
import java.util.*;

//식당_댓글 
public class RestaurantReplyVO {

 // PK 
 private int id;

 // 회원id 
 private int userId;

 // 식당id 
 private int restaurantId;

 // 댓글 
 private String reply;

 // 점수 
 private int score;

 // 등록일 
 private Date regdate;

 // 원래파일이름 
 private String imgOri;

 // 저장파일이름 
 private String imgNew;
 
 private String test1;
 
 private String test2;
 
  
 public String getTest1() {
	return test1;
}

public void setTest1(String test1) {
	this.test1 = test1;
}

public String getTest2() {
	return test2;
}

public void setTest2(String test2) {
	this.test2 = test2;
}

public int getId() {
     return id;
 }

 public void setId(Integer id) {
     this.id = id;
 }

 public Integer getUserId() {
     return userId;
 }

 public void setUserId(Integer userId) {
     this.userId = userId;
 }

 public Integer getRestaurantId() {
     return restaurantId;
 }

 public void setRestaurantId(Integer restaurantId) {
     this.restaurantId = restaurantId;
 }

 public String getReply() {
     return reply;
 }

 public void setReply(String reply) {
     this.reply = reply;
 }

 public Integer getScore() {
     return score;
 }

 public void setScore(Integer score) {
     this.score = score;
 }

 public Date getRegdate() {
     return regdate;
 }

 public void setRegdate(Date regdate) {
     this.regdate = regdate;
 }

 public String getImgOri() {
     return imgOri;
 }

 public void setImgOri(String imgOri) {
     this.imgOri = imgOri;
 }

 public String getImgNew() {
     return imgNew;
 }

 public void setImgNew(String imgNew) {
     this.imgNew = imgNew;
 }

 // RestaurantReply 모델 복사
 public void CopyData(RestaurantReplyVO param)
 {
     this.id = param.getId();
     this.userId = param.getUserId();
     this.restaurantId = param.getRestaurantId();
     this.reply = param.getReply();
     this.score = param.getScore();
     this.regdate = param.getRegdate();
     this.imgOri = param.getImgOri();
     this.imgNew = param.getImgNew();
 }
}
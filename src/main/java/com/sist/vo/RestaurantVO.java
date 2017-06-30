package com.sist.vo;

import java.util.Date;

public class RestaurantVO {

	 // PK 
	 private int id;
	 // 회원id 
	 private int user_id;
	 private String category;
	 // 시군주소 
	 private int address1;
	 // 추가입력주소 
	 private String address2;
	 // 식당이름 
	 private String name;
	 // 평점 
	 private Double score;
	 // 등록일 
	 private Date regdate;
	 // 전화번호 
	 private String tel;
	 // 가격대 
	 private String price;
	 // 내용 
	 private String content;
	 // 클릭수 
	 private int hit;
	 // 주차정보 
	 private String parking;
	 // 휴무정보 
	 private String holiday;
	 // 영업시간정보 
	 private String busihour;
	 // 원래파일이름 
	 private String img_ori;
	 // 저장파일이름 
	 private String img_new;
	 
	 private String sigun;
	public String getSigun() {
		return sigun;
	}
	public void setSigun(String sigun) {
		this.sigun = sigun;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getAddress1() {
		return address1;
	}
	public void setAddress1(int address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getBusihour() {
		return busihour;
	}
	public void setBusihour(String busihour) {
		this.busihour = busihour;
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

}

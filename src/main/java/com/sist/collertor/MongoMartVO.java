package com.sist.collertor;

public class MongoMartVO {
	 private String item;
	  private int hit;
	  private int cate;
	  private int month;
	  private int day;
	  private int year;
	  public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getCate() {
		return cate;
	}
	public void setCate(int cate) {
		this.cate = cate;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}

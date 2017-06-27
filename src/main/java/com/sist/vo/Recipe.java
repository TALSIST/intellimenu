package com.sist.vo;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

public class Recipe {
	private int id;
	private int user_id;
	private int cat_sub_id;
	private Date regdate;
	private int hit;
	private String title;
	private String summary;
	private int reqMember;
	private String time;
	private String lvl;
	private String img_ori;
	private String img_new;
	private String img;//img_ori와 new중 사용할 이미지 
	private List<MultipartFile> stepsFile;					//파일배열
	private List<String> content;								//순서내용
	private List<String> ingrv;									//재료목록
	private  List<String> ingrg;								//재료중량
	
	public List<MultipartFile> getStepsFile() {
		return stepsFile;
	}
	public void setStepsFile(List<MultipartFile> stepsFile) {
		this.stepsFile = stepsFile;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public List<String> getIngrv() {
		return ingrv;
	}
	public void setIngrv(List<String> ingrv) {
		this.ingrv = ingrv;
	}
	public List<String> getIngrg() {
		return ingrg;
	}
	public void setIngrg(List<String> ingrg) {
		this.ingrg = ingrg;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public int getCat_sub_id() {
		return cat_sub_id;
	}
	public void setCat_sub_id(int cat_sub_id) {
		this.cat_sub_id = cat_sub_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getReqMember() {
		return reqMember;
	}
	public void setReqMember(int reqMember) {
		this.reqMember = reqMember;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
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

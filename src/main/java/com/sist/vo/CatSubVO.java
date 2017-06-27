package com.sist.vo;

public class CatSubVO {

	// PK
	private int id;

	// 대분류id
	private int cat_top_id;

	// 소분류이름
	private String name;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCat_top_id() {
		return cat_top_id;
	}



	public void setCat_top_id(int cat_top_id) {
		this.cat_top_id = cat_top_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	// CatSub 모델 복사
	public void CopyData(CatSubVO param) {
		this.id = param.getId();
		this.cat_top_id = param.getCat_top_id();
		this.name = param.getName();
	}
}

package com.sist.vo;

public class RecipeContentVO {
	private int id;
	private int recipe_id;
	private int step;
	private String content;
	private String img_ori;
	private String img_new;
	private String img; //ori와 new중 하나를 선택하기 위해사용
	
	
	
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
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

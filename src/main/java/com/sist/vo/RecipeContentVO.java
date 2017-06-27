package com.sist.vo;

public class RecipeContentVO {
	private int id;
	private int recipe_id;
	private int step;
	private String content;
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
	public String getImgori() {
		return imgori;
	}
	public void setImgori(String imgori) {
		this.imgori = imgori;
	}
	public String getImgnew() {
		return imgnew;
	}
	public void setImgnew(String imgnew) {
		this.imgnew = imgnew;
	}
	private String imgori;
	private String imgnew;
	
}

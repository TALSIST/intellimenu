package com.sist.vo;

public class Ingredient {
	private int id;
	private String name;
	private int cal;
	private String quantity;//ingr_recipe와 조인하면서 사용할 변수
		
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}
	
	
}

package com.sist.vo;

import java.util.List;

public class IngredientVO {
	private int id;
	private String name;
	private int cal;
	// ingr_recipe와 조인하면서 사용 할 변수
	private String quantity;
	// 재료현황 출력시 Join해서 사용 할 변수들
	private List<ReligionVO> religion;
	private List<VegeterianVO> vegeterian;
	// 제철 처리
	private List<Integer> season;

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
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public List<ReligionVO> getReligion() {
		return religion;
	}
	public void setReligion(List<ReligionVO> religion) {
		this.religion = religion;
	}
	public List<VegeterianVO> getVegeterian() {
		return vegeterian;
	}
	public void setVegeterian(List<VegeterianVO> vegeterian) {
		this.vegeterian = vegeterian;
	}
	public List<Integer> getSeason() {
		return season;
	}
	public void setSeason(List<Integer> season) {
		this.season = season;
	}
	
}

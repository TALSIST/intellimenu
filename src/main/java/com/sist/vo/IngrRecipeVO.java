package com.sist.vo;

public class IngrRecipeVO {
	private int ingredient_id;
	private int recipe_id;
	private String quantity;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIngredient_id() {
		return ingredient_id;
	}
	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
}

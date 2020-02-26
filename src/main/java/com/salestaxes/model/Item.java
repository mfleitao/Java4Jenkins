package com.salestaxes.model;

import java.math.BigDecimal;

import com.salestaxes.enums.ProductCategoryEnum;

public class Item {

	private String name;

	private ProductCategoryEnum category;

	private BigDecimal price;

	private int amount;

	public Item() {
	}

	public Item(String name, ProductCategoryEnum category, BigDecimal price, int amount) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductCategoryEnum getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryEnum category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", category=" + category + ", price=" + price + ", amount=" + amount + "]";
	}

}

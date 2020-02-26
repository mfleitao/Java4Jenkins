package com.salestaxes.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

	private BigDecimal salesTaxes;
	
	private BigDecimal total;
	
	private List<Item> items;
	
	public Receipt() {
		salesTaxes = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
		items = new ArrayList<Item>();
	}

	public BigDecimal getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(BigDecimal totalSalesTaxes) {
		this.salesTaxes = totalSalesTaxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}

package com.salestaxes.enums;

import com.salestaxes.exception.SalesTaxesException;

public enum ProductCategoryEnum {

	BOOK(1, "Book", true),
	FOOD(2, "Food", true),
	MEDICAL(3, "Medical Products", true),
	COSMETIC(4, "Cosmetic", false),
	MUSIC_CD(5, "Music CD", false),
	DECORATION(6, "Decoration", false),
	KITCHEN_SUPPLY(7, "Kitchen Supply", false),
	OFFICE_SUPPLY(8, "Office Supply", false);
	
	private int code;
	private String value;
	private boolean exempt;
	
	private ProductCategoryEnum(int code, String value, boolean exempt) {
		this.code = code;
		this.value = value;
		this.exempt = exempt;
	}
	
	public static ProductCategoryEnum getCategoryByCode(int code) throws SalesTaxesException {
		for (ProductCategoryEnum e : ProductCategoryEnum.values()) {
			if (e.getCode() == code)
				return e;
		}
		throw new SalesTaxesException("Category Not Found");
	}
	
	public int getCode() {
		return code;
	}
	
	public String getValue() {
		return value;
	}
	
	public boolean isExempt() {
		return exempt;
	}
	
}

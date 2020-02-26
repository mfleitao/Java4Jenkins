package com.salestaxes.validator;

import java.math.BigDecimal;

import com.salestaxes.exception.SalesTaxesException;

public class ItemValidator {

	private static ItemValidator instance;
	
	public void validateName(String name) throws SalesTaxesException {
		if (name == null)
			throw new SalesTaxesException("Name cannot be Null");
		
		if (name.isEmpty())
			throw new SalesTaxesException("Name cannot be Empty");
	}
	
	public void validateAmount(int amount) throws SalesTaxesException {
		if (amount <= 0)
			throw new SalesTaxesException("Amount cannot be zero or a negative number");
	}
	
	public void validatePrice(BigDecimal price) throws SalesTaxesException {
		if (price.signum() == 0)
			throw new SalesTaxesException("Price cannot be zero");
		
		if (price.signum() == -1)
			throw new SalesTaxesException("Price cannot be a negative number");
	}
	
	private ItemValidator() {}
	
	public static ItemValidator getInstance() {
		if (null == instance)
			instance = new ItemValidator();
		return instance;
	}
	
}

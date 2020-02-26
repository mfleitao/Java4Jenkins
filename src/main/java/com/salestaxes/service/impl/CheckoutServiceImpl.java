package com.salestaxes.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.salestaxes.exception.SalesTaxesException;
import com.salestaxes.model.Item;
import com.salestaxes.model.Receipt;
import com.salestaxes.service.CheckoutService;

public class CheckoutServiceImpl implements CheckoutService {

	private static CheckoutServiceImpl instance;
	
	private static final BigDecimal IMPORTED_EXEMPT_PRODUCT_RATE = BigDecimal.valueOf(0.05);
	
	private static final BigDecimal REGULAR_PRODUCT_RATE = BigDecimal.valueOf(0.10);
	
	private static final BigDecimal IMPORTED_PRODUCT_RATE = BigDecimal.valueOf(0.15);
	

	public Receipt getReceipt(List<Item> basket) throws SalesTaxesException  {
		
		if (basket.size() == 0)
			throw new SalesTaxesException("Basket must have at least one item");
		
		Receipt receipt = new Receipt();
		BigDecimal tax = BigDecimal.ZERO;
		
		for (Item item : basket) {
			tax = getTax(item);
			BigDecimal pricePlusTax = (tax.equals(BigDecimal.ZERO)) ? item.getPrice() : round(item.getPrice().add(tax));
//			BigDecimal pricePlusTax = (tax.equals(BigDecimal.ZERO)) ? item.getPrice() : item.getPrice().add(tax);
			item.setPrice(pricePlusTax.multiply(BigDecimal.valueOf(item.getAmount())));
			receipt.setSalesTaxes(receipt.getSalesTaxes().add(tax));
			receipt.setTotal(receipt.getTotal().add(item.getPrice()));
			receipt.getItems().add(item);
			tax = BigDecimal.ZERO;
		}
		receipt.setSalesTaxes(round(receipt.getSalesTaxes()));

		return receipt;
	}
	
	private BigDecimal getTax(Item item) {
		
		BigDecimal tax = BigDecimal.ZERO;
		if (item.getName().toUpperCase().contains("IMPORTED")) {
			tax = (item.getCategory().isExempt()) ? item.getPrice().multiply(IMPORTED_EXEMPT_PRODUCT_RATE) 
					: item.getPrice().multiply(IMPORTED_PRODUCT_RATE);
		}
		else {
			if (!item.getCategory().isExempt())
				tax = item.getPrice().multiply(REGULAR_PRODUCT_RATE);
		}
		return tax;
	}
	
	private BigDecimal round(final BigDecimal value) {
		BigDecimal twenty = new BigDecimal(20);
		return value.setScale(2, RoundingMode.FLOOR).multiply(twenty).add(new BigDecimal("0.5"))
				.setScale(0, RoundingMode.FLOOR).divide(twenty).setScale(2, RoundingMode.FLOOR);
	}

	private CheckoutServiceImpl() {}
	
	public static CheckoutServiceImpl getInstance() {
		if (null == instance)
			instance = new CheckoutServiceImpl();
		return instance;
	}
	
}

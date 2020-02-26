package com.salestaxes.service;

import java.util.List;

import com.salestaxes.exception.SalesTaxesException;
import com.salestaxes.model.Item;
import com.salestaxes.model.Receipt;

public interface CheckoutService {

	public Receipt getReceipt(List<Item> items) throws SalesTaxesException;
	
}

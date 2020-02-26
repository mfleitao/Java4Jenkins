package com.salestaxes.helper;

import java.util.Date;

import com.salestaxes.model.Item;
import com.salestaxes.model.Receipt;
import com.salestaxes.utilities.Utils;

public class CheckoutHelper {

	public static void displayReceipt(Receipt receipt) {
		
		System.out.println("\n CHECKOUT");
		System.out.println("-----------------------------------------");
		
		System.out.println("\n************* R E C E I P T *************");
		System.out.println("Date: "+ Utils.getFormattedDate(new Date()));
		System.out.println("-----------------------------------------");
		
		for (Item i : receipt.getItems())
			System.out.printf("%-2d %-30s %1s %05.2f\n", i.getAmount(), i.getName(), "$", i.getPrice());
		
		System.out.println("-----------------------------------------");
		System.out.printf("%s %22s %05.2f\n", "Sales Taxes:", "$", receipt.getSalesTaxes());
		System.out.printf("%s %28s %05.2f\n", "Total:", "$", receipt.getTotal());
		System.out.println("=========================================");
	}
	
}

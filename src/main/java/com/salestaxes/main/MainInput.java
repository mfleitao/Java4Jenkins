package com.salestaxes.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.salestaxes.exception.SalesTaxesException;
import com.salestaxes.helper.CheckoutHelper;
import com.salestaxes.helper.ShoppingBasketHelper;
import com.salestaxes.model.Item;
import com.salestaxes.model.Receipt;
import com.salestaxes.service.CheckoutService;
import com.salestaxes.service.impl.CheckoutServiceImpl;

public class MainInput {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<Item> basket = new ArrayList<Item>();
		
		System.out.println("\n******** WELCOME TO POINT$ $TORE ********");
		boolean isProgramDone = false;
		
		while (!isProgramDone) {
			try {
				ShoppingBasketHelper.displayMenu();
				System.out.print(" Enter a menu option (number): ");
				int option = scanner.nextInt();
				
				switch (option) {
					case 1 : // Display Items Categories
						ShoppingBasketHelper.displayCategories();
						break;
					
					case 2 : // Add Items to Shopping Basket
						ShoppingBasketHelper.displayAddItems(scanner, basket);
						break;
					
					case 3 : // Checkout: Display Receipt
						CheckoutService service = CheckoutServiceImpl.getInstance();
						Receipt receipt = service.getReceipt(basket);
						CheckoutHelper.displayReceipt(receipt);
						basket.clear();
						break;	
					case 4 : // Exit Program
						ShoppingBasketHelper.displayGoodbye();
						isProgramDone = true;
						break;
				}
			
			} catch (InputMismatchException e) {
				System.out.println(" Error: Only numbers are allowed for this field.");
				scanner.nextLine();
				
			} catch (SalesTaxesException e) {
				System.out.println(" Error: "+ e.getMessage());
			}
		}
		
		scanner.close();
	}
	
}

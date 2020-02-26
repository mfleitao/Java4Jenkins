package com.salestaxes.helper;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.salestaxes.enums.ProductCategoryEnum;
import com.salestaxes.exception.SalesTaxesException;
import com.salestaxes.model.Item;
import com.salestaxes.validator.ItemValidator;

public class ShoppingBasketHelper {
	
	public static void displayMenu() {
		
		System.out.println("\n MENU");
		System.out.println("-----------------------------------------");
		System.out.println(" 1 - Display Items' Category");
		System.out.println(" 2 - Add Items in the Shopping Basket");
		System.out.println(" 3 - Checkout: Display Receipt");
		System.out.println(" 4 - Exit Program");
		System.out.println("-----------------------------------------");
	}

	public static void displayAddItems(Scanner scanner, List<Item> basket) {

		boolean isDone = false;
		System.out.println("\n SHOPPING BASKET");
		System.out.println("-----------------------------------------");
		
		ItemValidator validator = ItemValidator.getInstance();
		
		do {
			try {
				Item item = new Item();
				scanner.nextLine();

				// Item Name -----------------------------------------------------------------------------
				System.out.printf("%10s", "Product: ");
				String itemName = scanner.nextLine();
				validator.validateName(itemName);
				item.setName(itemName);
				
				// Item Category -------------------------------------------------------------------------
				System.out.printf("%10s", "Category: ");
				ProductCategoryEnum category = ProductCategoryEnum.getCategoryByCode(scanner.nextInt());
				item.setCategory(category);

				// Item Amount ---------------------------------------------------------------------------
				System.out.printf("%10s", "Amount: ");
				int itemAmount = scanner.nextInt();
				validator.validateAmount(itemAmount);
				item.setAmount(itemAmount);

				// Item Price ----------------------------------------------------------------------------
				System.out.printf("%10s", "Price: ");
				BigDecimal itemPrice = scanner.nextBigDecimal();
				validator.validatePrice(itemPrice);
				item.setPrice(itemPrice);
				
				basket.add(item);

				System.out.printf("\n%10s", "Add a new item? (Y or N): ");
				String addNewItem = scanner.next().toUpperCase();
				if ("N".equals(addNewItem))
					isDone = true;

			} catch (InputMismatchException e) {
				System.out.println(" Error: Only numbers are allowed in this field.");
				scanner.nextLine();
			
			} catch (SalesTaxesException e) {
				System.out.println(" Error: "+ e.getMessage());
			}

		} while (!isDone);
	}

	public static void displayCategories() {

		System.out.println("\n ITEMS' CATEGORIES");
		System.out.println("-----------------------------------------");
		System.out.printf("%5s %s %s\n", "Code", " ", "Category");
		
		for (ProductCategoryEnum e : ProductCategoryEnum.values())
			System.out.printf("%5d %s %s\n", e.getCode(), "|", e.getValue());
	}
	
	public static void displayGoodbye() {
		
		System.out.println("\n EXIT PROGRAM");
		System.out.println("-----------------------------------------");
		System.out.println(" Have a nice day!!!");
		System.out.println("=========================================");
	};
	
}

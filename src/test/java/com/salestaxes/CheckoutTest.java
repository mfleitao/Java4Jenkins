package com.salestaxes;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.salestaxes.enums.ProductCategoryEnum;
import com.salestaxes.exception.SalesTaxesException;
import com.salestaxes.model.Item;
import com.salestaxes.model.Receipt;
import com.salestaxes.service.CheckoutService;
import com.salestaxes.service.impl.CheckoutServiceImpl;

public class CheckoutTest {
	
	private static final int ZERO = 0;
	
	private CheckoutService service = CheckoutServiceImpl.getInstance();

	@Test
	public void testReceiptObjectAndFields() throws SalesTaxesException {
		
		List<Item> basket = new ArrayList<Item>();
		basket.add(new Item("Clean Code", ProductCategoryEnum.BOOK, new BigDecimal(51.36), 1));
		basket.add(new Item("Stapler", ProductCategoryEnum.OFFICE_SUPPLY, new BigDecimal(3.50), 1));
		
		Receipt receipt = service.getReceipt(basket);
		assertThat(receipt, is(IsNull.notNullValue()));
		assertThat(receipt, not(sameInstance(new Object())));
		assertThat(receipt, is(instanceOf(Receipt.class)));
		
		assertThat(receipt.getItems(), is(IsNull.notNullValue()));
		
		for (Item item : receipt.getItems()) {
			assertThat(item.getName(), is(IsNull.notNullValue()));
			assertThat(item.getCategory(), is(IsNull.notNullValue()));
			assertThat(item.getCategory().getValue(), is(IsNull.notNullValue()));
			assertThat(item.getCategory().getCode(), not(ZERO));
			assertThat(item.getPrice(), not(BigDecimal.ZERO));
			assertThat(item.getAmount(), not(ZERO));
		}
		
		assertThat(receipt.getSalesTaxes(), is(IsNull.notNullValue()));
		assertThat(receipt.getSalesTaxes(), not(BigDecimal.ZERO));
		assertThat(receipt.getTotal(), is(IsNull.notNullValue()));
		assertThat(receipt.getTotal(), not(BigDecimal.ZERO));
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testReceiptWithEmptyBasket() throws SalesTaxesException {
		
		@SuppressWarnings("unused")
		Receipt receipt = service.getReceipt(new ArrayList<Item>());
	}
	
	@Test
	public void testReceiptWithValidBasket_Input01() throws SalesTaxesException {
		
		List<Item> basket = new ArrayList<Item>();
		basket.add(new Item("Book", ProductCategoryEnum.BOOK, new BigDecimal(12.49), 1));
		basket.add(new Item("Music CD", ProductCategoryEnum.MUSIC_CD, new BigDecimal(14.99), 1));
		basket.add(new Item("Chocolate Bar", ProductCategoryEnum.FOOD, new BigDecimal(0.85), 1));
		
		Receipt receipt = service.getReceipt(basket);
		
		assertThat(receipt.getItems().get(0).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(12.49)));
		assertThat(receipt.getItems().get(1).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(16.50).setScale(2)));
		assertThat(receipt.getItems().get(2).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(0.85)));
		assertThat(receipt.getSalesTaxes().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(1.50).setScale(2)));
		assertThat(receipt.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(29.84)));
	}

	@Test
	public void testReceiptWithValidBasket_Input02() throws SalesTaxesException {
		
		List<Item> basket = new ArrayList<Item>();
		basket.add(new Item("Imported Box of Chocolates", ProductCategoryEnum.FOOD, new BigDecimal(10.00), 1));
		basket.add(new Item("Imported Bottle of Perfume", ProductCategoryEnum.COSMETIC, new BigDecimal(47.50), 1));
		
		Receipt receipt = service.getReceipt(basket);
		
		assertThat(receipt.getItems().get(0).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(10.50).setScale(2)));
		assertThat(receipt.getItems().get(1).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(54.60).setScale(2)));
		assertThat(receipt.getSalesTaxes().setScale(2, BigDecimal.ROUND_CEILING), is(BigDecimal.valueOf(07.63)));
		assertThat(receipt.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(65.10).setScale(2)));
	}

	@Test
	public void testReceiptWithValidBasket_Input03() throws SalesTaxesException {
		
		List<Item> basket = new ArrayList<Item>();
		basket.add(new Item("Imported Bottle of Perfume", ProductCategoryEnum.COSMETIC, new BigDecimal(27.99), 1));
		basket.add(new Item("Bottle of Perfume", ProductCategoryEnum.COSMETIC, new BigDecimal(18.99), 1));
		basket.add(new Item("Packet of Headache Pill", ProductCategoryEnum.MEDICAL, new BigDecimal(9.75), 1));
		basket.add(new Item("Imported Box of Chocolates", ProductCategoryEnum.FOOD, new BigDecimal(11.25), 1));
		
		Receipt receipt = service.getReceipt(basket);
		
		assertThat(receipt.getItems().get(0).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(32.20).setScale(2)));
		assertThat(receipt.getItems().get(1).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(20.90).setScale(2)));
		assertThat(receipt.getItems().get(2).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(09.75)));
		assertThat(receipt.getItems().get(3).getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(11.80).setScale(2)));
		assertThat(receipt.getSalesTaxes().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(6.66)));
		assertThat(receipt.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN), is(BigDecimal.valueOf(74.65)));
	}
	
}

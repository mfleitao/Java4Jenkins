package com.salestaxes;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.salestaxes.enums.ProductCategoryEnum;
import com.salestaxes.exception.SalesTaxesException;
import com.salestaxes.model.Item;
import com.salestaxes.validator.ItemValidator;

public class ItemTest {

	private ItemValidator validator = ItemValidator.getInstance();
	
	private static final int ZERO = 0;
	
	@Test
	public void testNewItemWithValidData() {
		
		Item item = new Item("Imported Chocolate Box", ProductCategoryEnum.FOOD, new BigDecimal(22.90), 2);
		Item medical = new Item("Tylenol", ProductCategoryEnum.MEDICAL, new BigDecimal(11.85), 1);
		
		assertThat(item, is(IsNull.notNullValue()));
		assertThat(item, not(sameInstance(new Object())));
		assertThat(item, is(instanceOf(Item.class)));
		assertThat(item, not(medical));
		assertThat(item.getName(), is(IsNull.notNullValue()));
		assertThat(item.getName(), containsString("Imported"));
		assertThat(item.getCategory(), is(IsNull.notNullValue()));
		assertThat(item.getCategory(), is(ProductCategoryEnum.FOOD));
		assertThat(item.getCategory().getValue(), is(IsNull.notNullValue()));
		assertThat(item.getCategory().getValue(), is("Food"));
		assertThat(item.getCategory().getCode(), not(ZERO));
		assertThat(item.getCategory().isExempt(), is(Boolean.TRUE));
		assertThat(item.getPrice(), not(BigDecimal.ZERO));
		assertThat(item.getAmount(), not(ZERO));
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testNewItemWithEmptyName() throws SalesTaxesException {
		
		Item item = new Item("", ProductCategoryEnum.BOOK, BigDecimal.ZERO, 0);
		validator.validateName(item.getName());
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testNewItemWithInvalidCategory() throws SalesTaxesException {
		
		new Item("", ProductCategoryEnum.getCategoryByCode(11), BigDecimal.ZERO, 0);
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testNewItemWithZeroAmount() throws SalesTaxesException {
		
		Item item = new Item("", ProductCategoryEnum.BOOK, BigDecimal.ZERO, 0);
		validator.validateAmount(item.getAmount());
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testNewItemWithNegativeAmount() throws SalesTaxesException {
		
		Item item = new Item("", ProductCategoryEnum.BOOK, BigDecimal.ZERO, -3);
		validator.validateAmount(item.getAmount());
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testNewItemWithZeroPrice() throws SalesTaxesException {
		
		Item item = new Item("", ProductCategoryEnum.BOOK, BigDecimal.ZERO, 0);
		validator.validatePrice(item.getPrice());
	}
	
	@Test(expected = SalesTaxesException.class)
	public void testNewItemWithNegativePrice() throws SalesTaxesException {
		
		Item item = new Item("", ProductCategoryEnum.BOOK, new BigDecimal(-5), 0);
		validator.validatePrice(item.getPrice());
	}

}

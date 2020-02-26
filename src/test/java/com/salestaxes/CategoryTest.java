package com.salestaxes;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.salestaxes.enums.ProductCategoryEnum;
import com.salestaxes.exception.SalesTaxesException;

public class CategoryTest {
	
	private static final int ZERO = 0;
	
	private static final int MEDICAL_CODE = 3;
	
	@Test
	public void testGetEachCategoryByCode() throws SalesTaxesException {
		
		for (int i = 0; i < ProductCategoryEnum.values().length; i++) {
			ProductCategoryEnum category = ProductCategoryEnum.getCategoryByCode(i + 1);
			assertThat(category, is(IsNull.notNullValue()));
			assertThat(category, is(instanceOf(ProductCategoryEnum.class)));
			assertThat(category.getCode(), not(ZERO));
			assertThat(category.getValue(), is(IsNull.notNullValue()));
			assertThat(category.isExempt(), is(IsNull.notNullValue()));
		}
	}
	
	@Test
	public void testGetSingleCategoryByCode() throws SalesTaxesException  {
		
		ProductCategoryEnum category = ProductCategoryEnum.getCategoryByCode(MEDICAL_CODE);
		assertThat(category, is(IsNull.notNullValue()));
		assertThat(category, is(instanceOf(ProductCategoryEnum.class)));
		assertThat(category, is(ProductCategoryEnum.MEDICAL));
		assertThat(category.getCode(), not(ZERO));
		assertThat(category.getCode(), is(MEDICAL_CODE));
		assertThat(category.getValue(), is(IsNull.notNullValue()));
		assertThat(category.getValue(), is("Medical Products"));
		assertThat(category.isExempt(), is(IsNull.notNullValue()));
		assertThat(category.isExempt(), is(Boolean.TRUE));
	}

	@Test(expected = SalesTaxesException.class)
	public void testGetOneWrongCategoryByCode() throws SalesTaxesException  {
		
		@SuppressWarnings("unused")
		ProductCategoryEnum category = ProductCategoryEnum.getCategoryByCode(11);
	}
	
}

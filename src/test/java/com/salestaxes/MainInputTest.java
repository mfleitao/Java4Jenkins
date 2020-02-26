package com.salestaxes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

import com.salestaxes.main.MainInput;

public class MainInputTest {

	private static final String PATH = "src\\test\\resources\\";
	
	/**
	 * This one goes to Shopping Basket then adds three items and
	 * then calls Checkout to create the Receipt 
	 */
	@Test
	public void testInput_01() throws IOException {
		
	    System.setIn(new FileInputStream(new File(PATH + "input_01.txt")));
	    MainInput.main(new String[]{});
	    System.setIn(System.in);
	}
	
	/**
	 * This one has an exception in its input file which tries to create a receipt
	 * even before any basket has been created.
	 * Exception Message: 'Error: Basket must have at least one item'
	 */
	@Test
	public void testInput_02() throws IOException {
		
	    System.setIn(new FileInputStream(new File(PATH + "input_02.txt")));
	    MainInput.main(new String[]{});
	    System.setIn(System.in);
	}
	
	/**
	 * This one adds three items then goes out of Shopping Basket. After that, 
	 * it displays the Categories list then goes back to Shopping Basket section 
	 * to add one more item. Then, calls Checkout to create the Receipt 
	 */
	@Test
	public void testInput_03() throws IOException {
		
	    System.setIn(new FileInputStream(new File(PATH + "input_03.txt")));
	    MainInput.main(new String[]{});
	    System.setIn(System.in);
	}
	
}

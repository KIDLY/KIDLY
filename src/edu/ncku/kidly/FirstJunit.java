package edu.ncku.kidly;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FirstJunit {
	private CountChart mCountChart;
	private final static String expStr = "12345678\r\n87654321\r\n";
	@Before
	public void setUp(){
//		fail();
		mCountChart = new CountChart();
	}
	@After
	public void tearDown(){
		mCountChart = null;
	}
	@Test
	public void testreadFile(){
		int answer = 5;
		String result = mCountChart.readFile("test1.txt", "Big5");
		assertEquals(expStr, result);
	}
	@Test
	public void testcutLine(){
		String expResult[][] = {{"12345678\r"},{"87654321\r"}};
		String funResult[][] = mCountChart.cutLine(expStr);
		assertArrayEquals(expResult, funResult);
	}
	
	

}

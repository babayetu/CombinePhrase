package com.karlliu.combinephrase;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PhraseSplitTest {
	private String testStr1 = "Please try this phrase to verify!";
	private String testStr2 = "This phrase,seperated by commons,OK?";
	
	@Before
	public void setUp() {
	}

	@Test
	public void testSplitStr1() {
		WordBlock[] wb = PhraseSplit.split(testStr1);
		assertEquals(wb[0].getContent(), "Please");
		assertEquals(wb[1].getContent(), "try");
		assertEquals(wb[2].getContent(), "this");
		assertEquals(wb[3].getContent(), "phrase");
		assertEquals(wb[4].getContent(), "to");
		assertEquals(wb[5].getContent(), "verify!");
		
		for (int i =0; i<wb.length;i++) {
			System.out.println(wb[i].getContent());
		}
	}	
	
	@Test
	public void testSplitStr2() {
		WordBlock[] wb = PhraseSplit.split(testStr2);
		assertEquals(wb[0].getContent(), "This");
		assertEquals(wb[1].getContent(), "phrase");
		assertEquals(wb[2].getContent(), "seperated");
		assertEquals(wb[3].getContent(), "by");
		assertEquals(wb[4].getContent(), "commons");
		assertEquals(wb[5].getContent(), "OK?");	
		
		for (int i =0; i<wb.length;i++) {
			System.out.println(wb[i].getContent());
		}		
	}	
}
package ch.hszt.MiniPowerPC.helper;

import junit.framework.TestCase;
import org.junit.Test;

public class HelperTest extends TestCase {

	@Test
	public void testPositiveIntToBinaryCharArray(){
		int i = 1337;
		String ref = "0000010100111001";
		String conv = String.valueOf(Helper.intToBinaryCharArray(i, 16));
		assertEquals(conv, ref);
	}
	
	@Test
	public void testNegativeIntToBinaryCharArray(){
		int i = -1337;
		String ref = "1111101011000111";
		String conv = String.valueOf(Helper.intToBinaryCharArray(i, 16));
		assertEquals(conv, ref);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIntToBinaryCharArrayOverflow(){
		int i = 50000;
		String.valueOf(Helper.intToBinaryCharArray(i, 16));
	}
	
	@Test
	public void testBinaryCharArrayToIntPositiveNo(){
		int i = 1337;
		String ref = "0000010100111001";
		
		assertTrue(Helper.binaryCharArrayToInt(ref.toCharArray()) == i);
	}
	@Test
	public void testBinaryCharArrayToIntNegativeNo(){
		int i = -1337;
		String ref = "1111101011000111";
		
		assertTrue(Helper.binaryCharArrayToInt(ref.toCharArray()) == i);
	}
}

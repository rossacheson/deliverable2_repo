import static org.junit.Assert.*;

import org.junit.Test;


public class CoffeeMakerTest {
	
	//each test needs an assert or a verify
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	//each test needs an assert or a verify
	@Test
	public void testRunArgs() {
		CoffeeMaker cm = new CoffeeMaker();
		int returnVal = cm.runArgs("Hi");
		assertEquals(returnVal, 0);
	}

}

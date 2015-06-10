import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mockito;

public class Tests {
	/* this was to test the junit and mockito stuff
	@Test
	public void foo(){
		Mockito.mock(Room.class);
	}
	*/
	
	//CoffeeMaker class
	
	//runArgs should always return zero
	@Test
	public void testRunArgs(){
		CoffeeMaker cm = new CoffeeMaker();
		int returnVal = cm.runArgs("Hi");
		assertEquals(returnVal,0);
	}
	
	//Game class
	
	//doSomething should return 0 unless 
	@Test
	public void testDoSomething(){
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(false);
		int returnVal = g.doSomething("D");
		assertEquals(returnVal, -1);
	}
	
	@Test
	public void testDoSomething2(){
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(true);
		int returnVal = g.doSomething("D");
		assertEquals(returnVal, 1);
	}
	

}

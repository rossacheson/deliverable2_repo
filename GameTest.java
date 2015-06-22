import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;


public class GameTest {

	@Test
	public void testMoveNorth() {
		//preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p,h);
		
		//execution step
		int result = g.doSomething("N");
		
		//assertions
		verify(h).moveNorth(); // OR verify(h, times(1)).moveNorth();
		verify(h, never()).moveSouth();
		assertEquals(result,0);
	}
	
	@Test
	public void testMoveSouth() {
		//preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p,h);
		
		//execution step
		int result = g.doSomething("S");
		
		//assertions
		verify(h).moveSouth(); // OR verify(h, times(1)).moveNorth();
		verify(h, never()).moveNorth();
		assertEquals(result,0);
	}
	
	@Test
	public void testLook() {
		//preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p,h);
		
		//execution step
		int result = g.doSomething("L");
		
		//assertions
		verify(h).look(p,null);
		assertEquals(result,0);
	}
	
	@Test
	public void testInventory() {
		//preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p,h);
		
		//execution step
		int result = g.doSomething("I");
		
		//assertions
		verify(p).showInventory();
		assertEquals(result,0);
	}
	
	//doSomething should return 0 unless 
		@Test
		public void testDoLoseGame(){
			House mockHouse = Mockito.mock(House.class);
			Player mockPlayer = Mockito.mock(Player.class);
			Game g = new Game(mockPlayer, mockHouse);
			Mockito.when(mockPlayer.drink()).thenReturn(false);
			int returnVal = g.doSomething("D");
			assertEquals(returnVal, -1);
		}
		
		@Test
		public void testDoWinGame(){
			House mockHouse = Mockito.mock(House.class);
			Player mockPlayer = Mockito.mock(Player.class);
			Game g = new Game(mockPlayer, mockHouse);
			Mockito.when(mockPlayer.drink()).thenReturn(true);
			int returnVal = g.doSomething("D");
			assertEquals(returnVal, 1);
		}
		
		//run
		//
		@Test
		public void testRunLoseGame(){ 
			House mockHouse = Mockito.mock(House.class);
			Player mockPlayer = Mockito.mock(Player.class);
			Game g = new Game(mockPlayer, mockHouse);
			Mockito.when(mockPlayer.drink()).thenReturn(false);
			int returnVal = g.run();
			g.doSomething("D");
			assertEquals(returnVal, 1);
		}
		
		@Test
		public void testRunWinGame(){ //change method content
			House mockHouse = Mockito.mock(House.class);
			Player mockPlayer = Mockito.mock(Player.class);
			Game g = new Game(mockPlayer, mockHouse);
			Mockito.when(mockPlayer.drink()).thenReturn(true);
			int returnVal = g.run();
			g.doSomething("D");
			assertEquals(returnVal, 0);
		}
		

}

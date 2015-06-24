import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.mockito.Mockito;

import junit.framework.TestCase;

public class Tests {

	// **** CoffeeMaker class test ****

	// runArgs should always return zero
	@Test
	public void testRunArgs() {
		CoffeeMaker cm = new CoffeeMaker();
		int returnVal = cm.runArgs("Hi"); // argument doesn't matter, any string
											// value will do
		assertEquals(returnVal, 0);
	}

	// **** Game class tests ****

	// the next 6 tests test the doSomething() method

	// make sure moveNorth is called and 0 is returned so the game continues
	@Test
	public void testMoveNorth() {
		// preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p, h);
		// execution step
		int result = g.doSomething("N");
		// assertions
		verify(h).moveNorth(); // OR verify(h, times(1)).moveNorth();
		verify(h, never()).moveSouth();
		assertEquals(result, 0);
	}

	@Test
	public void testMoveSouth() {
		// preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p, h);
		// execution step
		int result = g.doSomething("S");
		// assertions
		verify(h).moveSouth(); // OR verify(h, times(1)).moveNorth();
		verify(h, never()).moveNorth();
		assertEquals(result, 0);
	}

	@Test
	public void testLook() {
		// preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p, h);
		// execution step
		int result = g.doSomething("L");
		// assertions
		verify(h).look(p, null);
		assertEquals(result, 0);
	}

	@Test
	public void testInventory() {
		// preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p, h);

		// execution step
		int result = g.doSomething("I");

		// assertions
		verify(p).showInventory();
		assertEquals(result, 0);
	}

	// add explanatory comment
	@Test
	public void testDoLoseGame() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(false);
		int returnVal = g.doSomething("D");
		assertEquals(returnVal, -1);
	}

	@Test
	public void testDoWinGame() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(true);
		int returnVal = g.doSomething("D");
		assertEquals(returnVal, 1);
	}

	// the next two tests test the run() method
	//
	@Test
	public void testRunLoseGame() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(false);
		int returnVal = g.run();
		g.doSomething("D");
		assertEquals(returnVal, 1);
	}

	@Test
	public void testRunWinGame() { // change method content
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(true);
		int returnVal = g.run();
		g.doSomething("D");
		assertEquals(returnVal, 0);
	}

	// doSomething should return 0 unless
	@Test
	public void testDoSomething() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(false);
		int returnVal = g.doSomething("D");
		assertEquals(returnVal, -1);
	}

	// **** House class tests ****

	// This will check descriptions
	@Test
	public void testGetDescription() {
		Room r = mock(Room.class);
		when(r.getDescription()).thenReturn("TEST"); // stub of relevant method
														// from Room
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); // real house with fake rooms
		String result = h.getCurrentRoomInfo();
		assertEquals(result, "TEST");
	}

	@Test
	public void testNonRoom() {
		Room r = mock(Room.class);
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); // real house with fake rooms
		h.moveSouth();
		String result = h.getCurrentRoomInfo();
		assertTrue(result.contains("error")); // there should be an error
												// message
	}

	// generateRooms()
//	@Test
//	public void testGenerateRooms() {
//		Room r = mock(Room.class);
//		Room[] rooms = new Room[1];
//		rooms[0] = r;
//		House h = new House(rooms); // real house with fake rooms
//		Room[] result = h.generateRooms(3);
//		// assertArrayEquals(result, result);
//		assertThat(result[2]);
//	}

	// Rebecca's Tests *********************************** Rebecca's Tests

	// **** Room class tests ****

	// Test hasSugar()
	// hasSugar() should return false when there's no sugar in the room
	@Test
	public void testHasSugar() {
		Room room = new Room(false, false, false, true, true);
		assertFalse(room.hasSugar());
	}

	// Test hasCream()
	// hasCream() should return false when there's no cream in the room
	@Test
	public void testHasCream() {
		Room room = new Room(false, false, false, true, true);
		assertFalse(room.hasCream());
	}

	// Test hasCoffee()
	// hasCoffee() should return false when there's no coffee in the room
	@Test
	public void testHasCoffee() {
		Room room = new Room(false, false, false, true, true);
		assertFalse(room.hasCoffee());
	}

	// Test hasItem()
	// hasItem() should return false when there's nothing in the room
	@Test
	public void testHasItem_None() {
		Room room = new Room(false, false, false, true, true);
		assertFalse(room.hasItem());
	}

	// Test hasItem()
	// hasItem() should return true when all the three ingredients are in the
	// room
	@Test
	public void testHasItem_All() {
		Room room = new Room(true, true, true, true, true);
		assertTrue(room.hasItem());
	}

	// Test hasItem()
	// hasItem() should return true when there's only one item in the room,
	// let's say, Sugar
	@Test
	public void testHasItem_Sugar() {
		Room room = new Room(true, false, false, true, true);
		assertTrue(room.hasItem());
	}

	// Test northExit()
	// northExit() should return true when there exists a north exit
	@Test
	public void testNorthExit() {
		Room room = new Room(false, false, false, true, true);
		assertTrue(room.northExit());
	}

	// Test southExit()
	@Test
	public void testSouthExit() {
		Room room = new Room(false, false, false, true, true);
		assertTrue(room.southExit());
	}

	// Test getDescription()
	// This will eventually check the descriptions
	@Test
	public void testGetDescription_southExit() {
		Room room = new Room(false, false, false, false, true);
		String st = room.getDescription();
		assertTrue(st.contains("You see a"));
		assertTrue(st.contains("room."));
		assertTrue(st.contains("It has a"));
		assertTrue(st.contains("door leads South."));
	}

	// **** Player class tests ****

	// Test hasAllItems()
	// hasAllItems() should return false when there's nothing in the player's
	// inventory
	@Test
	public void testHasAllItems_nothing() {
		Player player = new Player(false, false, false);
		assertFalse(player.hasAllItems());
	}

	// Test hasAllItems()
	// hasAllItems() should return false when there's only one thing, say, Sugar
	// in the player's inventory
	@Test
	public void testHasAllItems_sugar() {
		Player player = new Player(true, false, false);
		assertFalse(player.hasAllItems());
	}

	// Test hasAllItems()
	// hasAllItems() should return false when there are two items, say Sugar and
	// Cream in the player's inventory
	@Test
	public void testHasAllItems_sugar_cream() {
		Player player = new Player(true, true, false);
		assertFalse(player.hasAllItems());
	}

	// Test drink()
	// drink() should return false when the player has nothing in his/her
	// inventory
	@Test
	public void testDrink_nothing() {
		Player player = new Player(false, false, false);
		assertFalse(player.drink());
	}

	// Test drink()
	// drink() should return false when the player has only Sugar in his/her
	// inventory
	@Test
	public void testDrink_sugar() {
		Player player = new Player(true, false, false);
		assertFalse(player.drink());
	}

	// Test drink()
	// drink() should return false when the player has only Sugar in his/her
	// inventory
	@Test
	public void testDrink_all() {
		Player player = new Player(true, true, true);
		assertTrue(player.drink());
	}

	// Test for House

	// This tests the generateRooms method
	// @Test
	// public void testGenerateRooms(){
	// House h = new House(7);
	// String result = h.getCurrentRoomInfo();
	// verify(h, times(1)).generateRooms(7);
	// //assertTrue(result.contains("You see a"));
	// }
	//

}

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;

import org.junit.*;
import org.mockito.Mockito;

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
		verify(h).moveNorth();
		verify(h, never()).moveSouth();
		assertEquals(result, 0);
	}

	// make sure moveSouth is called and 0 is returned so the game continues
	@Test
	public void testMoveSouth() {
		// preconditions
		Player p = mock(Player.class);
		House h = mock(House.class);
		Game g = new Game(p, h);
		// execution step
		int result = g.doSomething("S");
		// assertions
		verify(h).moveSouth();
		verify(h, never()).moveNorth();
		assertEquals(result, 0);
	}

	// make sure look is called and 0 is returned so the game continues
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

	// make sure inventory is called and 0 is returned so the game continues
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

	// make sure -1 is returned when drinking inadvisably
	@Test
	public void testDoLoseGame() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(false);
		int returnVal = g.doSomething("D");
		assertEquals(returnVal, -1);
	}

	// make sure 1 is returned when drinking wisely
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
	// run should return 1 after drinking inadvisably
	@Test
	public void testRunLoseGame() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(false); // lose
		ByteArrayInputStream in1 = new ByteArrayInputStream("D".getBytes());
		System.setIn(in1);
		int returnVal = g.run();
		assertEquals(returnVal, 1);
	}

	// run should return 0 after drinking wisely
	@Test
	public void testRunWinGame() {
		House mockHouse = Mockito.mock(House.class);
		Player mockPlayer = Mockito.mock(Player.class);
		Game g = new Game(mockPlayer, mockHouse);
		Mockito.when(mockPlayer.drink()).thenReturn(true); // win
		ByteArrayInputStream in2 = new ByteArrayInputStream("D".getBytes());
		System.setIn(in2);
		int returnVal = g.run();
		assertEquals(returnVal, 0);
	}

	// doSomething should return -1 when drink method returns false
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

	// This will check that getCurrentRoomInfo()
	@Test
	public void testGetDescription() {
		Room r = mock(Room.class);
		when(r.getDescription()).thenReturn("TEST"); // stub of relevant method
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); // real house with fake rooms
		String result = h.getCurrentRoomInfo();
		assertEquals(result, "TEST");
	}

	// Testing behavior when the player moves South with no door/room south.
	// There should be an error message, but there is a magical land instead, so
	// test fails. Changing "error" to "magical" below makes it pass.
	@Test
	public void testNonRoom() {
		Room r = mock(Room.class);
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); // real house with fake rooms
		h.moveSouth();
		String result = h.getCurrentRoomInfo();
		assertTrue(result.contains("error"));
	}

	// test generateRooms(), ensuring we get a room array of the right length
	@Test
	public void testGenerateRooms() {
		Room r = mock(Room.class);
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); // real house with fake rooms
		Room[] result = h.generateRooms(3); // should return an array of 3 rooms
		assertEquals(3, result.length);
	}

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

	// The next three tests test the hasItem() method

	// hasItem() should return false when there's nothing in the room
	@Test
	public void testHasItem_None() {
		Room room = new Room(false, false, false, true, true);
		assertFalse(room.hasItem());
	}

	// hasItem() should return true when all the three ingredients are in the
	// room
	@Test
	public void testHasItem_All() {
		Room room = new Room(true, true, true, true, true);
		assertTrue(room.hasItem());
	}

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
	// southExit() should return true when there exists a south exit
	@Test
	public void testSouthExit() {
		Room room = new Room(false, false, false, true, true);
		assertTrue(room.southExit());
	}

	// Test getDescription()
	// This check the general description of a room with a door leading South
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

}

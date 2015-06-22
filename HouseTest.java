import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class HouseTest {
	
	//This will check descriptions
	@Test
	public void testGetDescription() {
		Room r = mock(Room.class);
		when(r.getDescription()).thenReturn("TEST"); //stub of relevant method from Room
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); //real house with fake rooms
		String result = h.getCurrentRoomInfo();
		assertEquals(result, "TEST");
	}
	
	@Test
	public void testNonRoom() {
		Room r = mock(Room.class);
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); //real house with fake rooms
		h.moveSouth();
		String result = h.getCurrentRoomInfo();
		assertTrue(result.contains("error")); //there should be an error message
	}
	
	//generateRooms()
	@Test
	public void testGenerateRooms() {
		Room r = mock(Room.class);
		Room[] rooms = new Room[1];
		rooms[0] = r;
		House h = new House(rooms); //real house with fake rooms
		Room [] result = h.generateRooms(3);
		//assertArrayEquals(result, result);
		assertThat(result[2]);
	}

}

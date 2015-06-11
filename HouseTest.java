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

}

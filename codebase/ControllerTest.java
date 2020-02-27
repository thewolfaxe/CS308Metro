import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {

	static Controller controller;
	@BeforeEach
	void setUp() {
		controller = new Controller();
		controller.setup();
	}
	
	@Test
	void getStartStationTest() throws Exception
	{
		Node expected = new Node("102", "ForestHills");
		Node actual = Controller.getStartStation();
		
		assertEquals(expected, actual);
	}

	@Test
	void getEndStationTest() throws Exception
	{
		Node expected = new Node("102", "ForestHills");
		Node actual = Controller.getStartStation();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void getExitTest() throws Exception
	{
		assertFalse(Controller.getExit());
		assertTrue(Controller.getExit());
	}
	
	@Test
	void getRouteTest() throws Exception
	{
		
	}
}

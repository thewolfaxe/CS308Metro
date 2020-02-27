import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {

	Controller controller;
	@BeforeEach
	void setUp() {
		controller = new Controller();
		controller.setup();
	}
	
	@Test
	void getStartStationTest() throws Exception
	{
		Node expected = new Node("102", "ForestHills");
		Node actual = controller.getStartStation();
		
		assertEquals(expected, actual);
	}

	@Test
	void getEndStationTest() throws Exception
	{
		Node expected = new Node("102", "ForestHills");
		Node actual = controller.getStartStation();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void getExitTest() throws Exception
	{
		assertFalse(controller.getExit());
		assertTrue(controller.getExit());
	}
	
	@Test
	void getRouteTest() throws Exception
	{
		
	}
}

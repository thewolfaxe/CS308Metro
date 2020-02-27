import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;

class ControllerTest {

	@BeforeEach
	void setUp() {
	}
	
	@Test
	void getStartStationTest() throws Exception
	{
		String testInput = "ForestHills";
		InputStream input = new ByteArrayInputStream(testInput.getBytes());
		Controller controller = new Controller(input);
		controller.setup();
		Node expected = new Node("102", "ForestHills");
		Node actual = controller.getStartStation();

		assertEquals(expected, actual);
	}

	@Test
	void getEndStationTest() throws Exception
	{
		String testInput = "ForestHills";
		InputStream input = new ByteArrayInputStream(testInput.getBytes());
		Controller controller = new Controller(input);
		controller.setup();
		Node expected = new Node("102", "ForestHills");
		Node actual = controller.getStartStation();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void getExitTestTrue() throws Exception
	{
		String testInput = "yes";
		InputStream input = new ByteArrayInputStream(testInput.getBytes());
		Controller controller = new Controller(input);
		controller.setup();
		assertFalse(controller.getExit());
	}

	@Test
	void getExitTestFalse() throws Exception {
		String testInput = "no";
		InputStream input = new ByteArrayInputStream(testInput.getBytes());
		Controller controller = new Controller(input);
		controller.setup();
		assertTrue(controller.getExit());
	}

	@Test
	void getRouteTest() throws Exception
	{
		String testInput = "yes";
		InputStream input = new ByteArrayInputStream(testInput.getBytes());
		Controller controller = new Controller(input);
		Graph graph = controller.setup();
		LinkedList<Edge> expectedList = new LinkedList<>();
		expectedList.add(graph.checkEdge(graph.getNode("central"), graph.getNode("kendall")).get(0));
		expectedList.add(graph.checkEdge(graph.getNode("kendall"), graph.getNode("charles/mgh")).get(0));
		expectedList.add(graph.checkEdge(graph.getNode("charles/mgh"), graph.getNode("parkstreet")).get(0));
		expectedList.add(graph.checkEdge(graph.getNode("parkstreet"), graph.getNode("downtowncrossing")).get(0));
		expectedList.add(graph.checkEdge(graph.getNode("downtowncrossing"), graph.getNode("state")).get(0));
		Node start = graph.getNode("central");
		Node end = graph.getNode("state");

		LinkedList<Edge> actual = controller.getRoute(graph);
		assertTrue(expectedList.containsAll(graph.getRoute(start, end)));

	}

}

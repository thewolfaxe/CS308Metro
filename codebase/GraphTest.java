import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

	Graph graph;
	@BeforeEach
	void setUp() throws Exception 
	{
		Parser parse = new Parser();
		Map<Node, ArrayList<Edge>> metroGraph = parse.loadFile("bostonMetro.txt");
		graph = new Graph(metroGraph);
	}
	
	@Test
	void getRouteTest() throws Exception 
	{
		LinkedList<Edge> expected = new LinkedList<>();
		expected.add(graph.checkEdge(graph.getNode("central"), graph.getNode("kendall")));
		expected.add(graph.checkEdge(graph.getNode("kendall"), graph.getNode("charles/mgh")));
		expected.add(graph.checkEdge(graph.getNode("charles/mgh"), graph.getNode("parkstreet")));
		expected.add(graph.checkEdge(graph.getNode("parkstreet"), graph.getNode("downtowncrossing")));
		expected.add(graph.checkEdge(graph.getNode("downtowncrossing"), graph.getNode("state")));
		Node start = graph.getNode("central");
		Node end = graph.getNode("state");
		assertTrue(expected.containsAll(graph.getRoute(start, end)));
		
	}
	
	@Test
	void getEdgesTest() throws Exception
	{
		ArrayList<Edge> tracks = new ArrayList<>();
		Edge track1 = new Edge("Orange", "82", "93", 1);
		Edge track2 = new Edge("Orange", "64", "82", 1);
		tracks.add(track1);
		tracks.add(track2);
		assertTrue(tracks.containsAll(graph.getEdges(graph.getNode("ruggles"))));
		Node notAStation = new Node("125", "notastation");
		assertNull(graph.getEdges(notAStation));
	}
	
	@Test
	void checkEdgeTest() throws Exception
	{
		Edge expected = new Edge("Orange", "82", "93", 1);
		Node station1 = graph.getNode("ruggles");
		Node station2 = graph.getNode("roxburycrossing");
		assertTrue(expected.equals(graph.checkEdge(station1, station2)));
		Node notAStation = new Node("125", "notastation");
		assertNull(graph.checkEdge(station1, station1));
		assertNull(graph.checkEdge(notAStation, station1));
		assertNull(graph.checkEdge(station2, notAStation));
	}
	
	@Test
	void getDegree() throws Exception
	{
		int expected = 4;
		int actual = graph.getDegree(graph.getNode("downtowncrossing"));
		assertEquals(expected, actual);
		Node notAStation = new Node("125", "notastation");
		assertEquals(-1, graph.getDegree(notAStation));
	}
	
	@Test
	void getAdjacentTest() throws Exception
	{
		ArrayList<Node> expected = new ArrayList<Node>();
		expected.add(graph.getNode("massachusettsavenue"));
		expected.add(graph.getNode("roxburycrossing"));
		boolean test = expected.containsAll(graph.getAdjacent(graph.getNode("ruggles")));
		assertTrue(test);
		Node notAStation = new Node("125", "notastation");
		assertTrue(graph.getAdjacent(notAStation).size() == 0);
		
	}
	
	@Test
	void getNodeTest() throws Exception
	{
		String expected = "ruggles";
		String actual = graph.getNode(expected).getName().toLowerCase();
		assertEquals(expected, actual);
		assertEquals(null, graph.getNode("notanode"));
	}
}

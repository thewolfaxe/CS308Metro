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
		expected.add(graph.checkEdge(graph.getNode("central"), graph.getNode("kendall")).get(0));
		expected.add(graph.checkEdge(graph.getNode("kendall"), graph.getNode("charles/mgh")).get(0));
		expected.add(graph.checkEdge(graph.getNode("charles/mgh"), graph.getNode("parkstreet")).get(0));
		expected.add(graph.checkEdge(graph.getNode("parkstreet"), graph.getNode("downtowncrossing")).get(0));
		expected.add(graph.checkEdge(graph.getNode("downtowncrossing"), graph.getNode("state")).get(0));
		Node start = graph.getNode("central");
		Node end = graph.getNode("state");
		assertTrue(expected.containsAll(graph.getRoute(start, end)));
		
		LinkedList<Edge> expected2 = new LinkedList<>();
		expected2.add(graph.checkEdge(graph.getNode("chinatown"), graph.getNode("downtowncrossing")).get(0));
		expected2.add(graph.checkEdge(graph.getNode("downtowncrossing"), graph.getNode("state")).get(0));
		expected2.add(graph.checkEdge(graph.getNode("state"), graph.getNode("haymarket")).get(0));
		expected2.add(graph.checkEdge(graph.getNode("haymarket"), graph.getNode("northstation")).get(0));
		expected2.add(graph.checkEdge(graph.getNode("northstation"), graph.getNode("communitycollege")).get(0));
		Node start2 = graph.getNode("chinatown");
		Node end2 = graph.getNode("communitycollege");
		assertTrue(expected2.containsAll(graph.getRoute(start2, end2)));
	}
	
	
	@Test
	void checkEdgeTest() throws Exception
	{
		Edge expected = new Edge("Orange", "82", "93", 1);
		Node station1 = graph.getNode("ruggles");
		Node station2 = graph.getNode("roxburycrossing");
		assertTrue(expected.equals(graph.checkEdge(station1, station2).get(0)));
		Node notAStation = new Node("125", "notastation");
		assertNull(graph.checkEdge(station1, station1));
		assertNull(graph.checkEdge(notAStation, station1));
		assertNull(graph.checkEdge(station2, notAStation));
		ArrayList<Edge> expectedEdges = new ArrayList<>();
		Edge edge1 = new Edge("Orange", "22", "20", 1);
		Edge edge2 = new Edge("Green", "22", "20", 1);
		expectedEdges.add(edge1);
		expectedEdges.add(edge2);
		Node station3 = graph.getNode("haymarket");
		Node station4 = graph.getNode("northstation");
		assertTrue(expectedEdges.containsAll(graph.checkEdge(station3, station4)));
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
	
	@Test
	void checkDuplicateTest() throws Exception
	{
		assertTrue(graph.checkDuplicateNode("st.paulstreet"));
		assertFalse(graph.checkDuplicateNode("chinatown"));
	}
	
	@Test
	void getNodeByLineTest() throws Exception
	{
		String expectedName = "st.paulstreet";
		String line = "greenb";
		String expectedID = "38";
		String actualName = graph.getNodeByLine(expectedName, line).getName().toLowerCase();
		String actualID = graph.getNodeByLine(expectedName, line).getID();
		assertEquals(expectedName, actualName);
		assertEquals(expectedID, actualID);
		assertNull(graph.getNodeByLine("notastation", line));
	}
}

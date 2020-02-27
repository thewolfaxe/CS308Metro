import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTests {

	private Parser parser;
	private Map<Node, ArrayList<Edge>> expectedGraph;
	
	@BeforeEach
	void setUp() throws Exception {
		parser = new Parser();
		
		expectedGraph = new HashMap<>();
		
		Node hamilton = new Node("1", "Hamilton");
		ArrayList<Edge> hamiltonTracks = new ArrayList<>();
		hamiltonTracks.add(new Edge("Pink", "3", "1", 0));
		hamiltonTracks.add(new Edge("Pink", "1", "0", 0));
		expectedGraph.put(hamilton, hamiltonTracks);
		
		Node airbles = new Node("2", "Airbles");
		ArrayList<Edge> airblesTracks = new ArrayList<>();
		airblesTracks.add(new Edge("Green", "3", "2", 3));
		airblesTracks.add(new Edge("Green", "2", "0", 3));
		expectedGraph.put(airbles, airblesTracks);
		
		Node motherwell = new Node("3", "Motherwell");
		ArrayList<Edge> motherwellTracks = new ArrayList<>();
		motherwellTracks.add(new Edge("Pink", "6", "3", 0));
		motherwellTracks.add(new Edge("Pink", "3", "1", 0));
		motherwellTracks.add(new Edge("Green", "4", "3", 3));
		motherwellTracks.add(new Edge("Green", "3", "2", 3));
		expectedGraph.put(motherwell, motherwellTracks);
		
		Node uddingston = new Node("4", "Uddingston");
		ArrayList<Edge> uddingstonTracks = new ArrayList<>();
		uddingstonTracks.add(new Edge("Green", "5", "4", 3));
		uddingstonTracks.add(new Edge("Green", "4", "3", 3));
		expectedGraph.put(uddingston, uddingstonTracks);
		
		Node glasgow = new Node("5", "Glasgow");
		ArrayList<Edge> glasgowTracks = new ArrayList<>();
		glasgowTracks.add(new Edge("Green", "4", "5", 3));
		glasgowTracks.add(new Edge("Green", "5", "0", 3));
		expectedGraph.put(glasgow, glasgowTracks);
		
		Node wishaw = new Node("6", "Wishaw");
		ArrayList<Edge> wishawTracks = new ArrayList<>();
		wishawTracks.add(new Edge("Pink", "3", "6", 0));
		wishawTracks.add(new Edge("Pink", "6", "0", 0));
		expectedGraph.put(wishaw, wishawTracks);
	}

	@Test
	void parsesTestFile() throws Exception {
		Map<Node, ArrayList<Edge>> actualGraph = parser.loadFile("testMetro.txt");
		assertEquals(expectedGraph, actualGraph);
	}

}

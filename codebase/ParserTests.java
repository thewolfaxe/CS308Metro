import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTests {

	private Parser parser;
	private Map<Station, ArrayList<Track>> expectedGraph;
	
	@BeforeEach
	void setUp() throws Exception {
		parser = new Parser();
		
		expectedGraph = new HashMap<>();
		
		Station hamilton = new Station("1", "Hamilton");
		ArrayList<Track> hamiltonTracks = new ArrayList<>();
		hamiltonTracks.add(new Track("Pink", "3", "1", 0));
		hamiltonTracks.add(new Track("Pink", "1", "0", 0));
		expectedGraph.put(hamilton, hamiltonTracks);
		
		Station airbles = new Station("2", "Airbles");
		ArrayList<Track> airblesTracks = new ArrayList<>();
		airblesTracks.add(new Track("Green", "3", "2", 3));
		airblesTracks.add(new Track("Green", "2", "0", 3));
		expectedGraph.put(airbles, airblesTracks);
		
		Station motherwell = new Station("3", "Motherwell");
		ArrayList<Track> motherwellTracks = new ArrayList<>();
		motherwellTracks.add(new Track("Pink", "6", "3", 0));
		motherwellTracks.add(new Track("Pink", "3", "1", 0));
		motherwellTracks.add(new Track("Green", "4", "3", 3));
		motherwellTracks.add(new Track("Green", "3", "2", 3));
		expectedGraph.put(motherwell, motherwellTracks);
		
		Station uddingston = new Station("4", "Uddingston");
		ArrayList<Track> uddingstonTracks = new ArrayList<>();
		uddingstonTracks.add(new Track("Green", "5", "4", 3));
		uddingstonTracks.add(new Track("Green", "4", "3", 3));
		expectedGraph.put(uddingston, uddingstonTracks);
		
		Station glasgow = new Station("5", "Glasgow");
		ArrayList<Track> glasgowTracks = new ArrayList<>();
		glasgowTracks.add(new Track("Green", "4", "5", 3));
		glasgowTracks.add(new Track("Green", "5", "0", 3));
		expectedGraph.put(glasgow, glasgowTracks);
		
		Station wishaw = new Station("6", "Wishaw");
		ArrayList<Track> wishawTracks = new ArrayList<>();
		wishawTracks.add(new Track("Pink", "3", "6", 0));
		wishawTracks.add(new Track("Pink", "6", "0", 0));
		expectedGraph.put(wishaw, wishawTracks);
	}

	@Test
	void parsesTestFile() throws Exception {
		Map<Station, ArrayList<Track>> actualGraph = parser.loadFile("testMetro.txt");
		assertEquals(expectedGraph, actualGraph);
	}

}

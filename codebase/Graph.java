import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph implements GraphInterface {

	//Map<Station, Track> metroGraph = new HashMap<Station, Track>();
	Map<Station, ArrayList<Track>> metroGraph;

	public Graph() {
		metroGraph = new HashMap<>();
	}

	public Graph setup() {
		Parser parse = new Parser();
		try {
			metroGraph = parse.loadFile();
		} catch (IOException | BadFileException e) {
			e.printStackTrace();
		}

		return (Graph) metroGraph;
	}

	@Override
	public LinkedList<Track> getRoute(Station start, Station finish) {
		//this needs a dijkstra's algorithm implemented
		return null;
	}

	public Track[] edges(Station stat) {
		//returns an array of tracks connected to this station
		return null;
	}

	@Override
	public boolean checkEdge(Station stat1, Station stat2) {
		//checks whether a track exists between the 2 stations
		return false;
	}

	public int getDegree(Station Station) {
		//returns the number of tracks connection to this station
		return 0;
	}

	public Station[] getAdjacent(Station stat) {
		//returns an array of stations connected to given station
		return null;
	}
}
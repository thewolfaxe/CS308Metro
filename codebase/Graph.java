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

	public ArrayList<Track> edges(Station stat) {
		//returns an array of tracks connected to this station
		return metroGraph.get(stat);
	}

	@Override
	public boolean checkEdge(Station stat1, Station stat2) {
		//checks whether a track exists between the 2 stations
		ArrayList<Track> tracks1 = metroGraph.get(stat1);
		ArrayList<Track> tracks2 = metroGraph.get(stat2);
		for (Track x : tracks1) {
			if(x.getRightNode() == stat2.getID()) {
				return true;
			}
			if(x.getLeftNode() == stat2.getID()) {
				return true;
			}
		}
		for (Track y : tracks2) {
			if(y.getRightNode() == stat2.getID()) {
				return true;
			}
			if(y.getLeftNode() == stat2.getID()) {
				return true;
			}
		}
		return false;
	}

	public int getDegree(Station Station) {
		//returns the number of tracks connection to this station
		return metroGraph.get(Station).size();
	}

	public ArrayList<Station> getAdjacent(Station stat) {
		//returns an array of stations connected to given station
		ArrayList<Station> stations = new ArrayList<>();
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (checkEdge(stat, entry.getKey())) {
				stations.add(entry.getKey());
			}
		}
		return stations;
	}
}
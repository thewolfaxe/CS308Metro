import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph implements GraphInterface {

	//Map<Station, Track> metroGraph = new HashMap<Station, Track>();
	Map<Station, ArrayList<Track>> metroGraph = new HashMap<>();

	public Graph()
	{}

	public Boolean checkEmpty() {
		return null;
	}

	public Integer numVert() {
		return null;
	}

	public Integer numEdge() {
		return null;
	}

	public Boolean checkEdge(Station one, Station two) {
		return null;
	}

	public Integer getDegree(Station Station) {
		return null;
	}

	public Station[] getAdjacent(Station Station) {
		return null;
	}

	public Boolean isAdjacent(Station one, Station two) {
		return null;
	}
}
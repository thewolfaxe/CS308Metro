import java.io.IOException;
import java.util.*;

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
		Queue<Station> agenda = new ArrayDeque<>();
		HashMap<Station, Station> ancestors = new HashMap<>();
		agenda.add(start);
		Station current;

		while(!agenda.isEmpty()){

			current = findShortest(agenda, ancestors, start);

			if(current == finish) {
				System.out.println("\n\nFound the station\n\n");
				ArrayList<Station> route = buildRoute(ancestors, current, finish);
				System.out.println(route.toString());
				return null;
			}

			ArrayList<Station> temp = getAdjacent(current);
			for(Station stat: temp) {
				if(!ancestors.containsKey(stat)) {
					ancestors.put(stat, current);
				}
			}
		}


		return null;
	}

	public Station findShortest(Queue<Station> agenda, HashMap<Station, Station> ancestors, Station start) {
		int best = -1;		//init best
		Station shortest = agenda.peek();		//init shortest station as the first in the agenda

		//loop through agenda and find the shortest path so far
		for(Station stat: agenda) {
			int attempt = pathSoFar(ancestors, stat, start);
			if(attempt < best || best == -1) {
				best = attempt;
				shortest = stat;
			}
		}
		return shortest;
	}

	public int pathSoFar(HashMap<Station, Station> ancestors, Station current, Station start) {
		int total = 0;
		Station previous = current;
		String line, prevLine = null;
		while(current != start) {
			++total;
			current = ancestors.get(current);
			line = checkEdge(previous, current).getLine();

			if(prevLine != null)
				if(!prevLine.equals(line))
					total += current.getWeight();

			prevLine = line;
			previous = current;
		}
		++total;
		return total;
	}

	public ArrayList<Station> buildRoute(HashMap<Station, Station> ancestors, Station current, Station start) {
		ArrayList<Station> route = new ArrayList<>();

		while (current != start) {
			route.add(current);
			current = ancestors.get(current);
		}

		Collections.reverse(route);
		return route;
	}

	public ArrayList<Track> edges(Station stat) {
		//returns an array of tracks connected to this station
		return metroGraph.get(stat);
	}

	@Override
	public Track checkEdge(Station stat1, Station stat2) {
		if(stat1 == stat2)
			return null;

		//checks whether a track exists between the 2 stations
		ArrayList<Track> tracks1 = metroGraph.get(stat1);
		ArrayList<Track> tracks2 = metroGraph.get(stat2);
		for (Track x : tracks1) {
			if(x.getRightNode().equals(stat2.getID())) {
				return x;
			}
			if(x.getLeftNode().equals(stat2.getID())) {
				return x;
			}
		}
		for (Track y : tracks2) {
			if(y.getRightNode().equals(stat2.getID())) {
				return y;
			}
			if(y.getLeftNode().equals(stat2.getID())) {
				return y;
			}
		}
		return null;
	}

	public int getDegree(Station Station) {
		//returns the number of tracks connection to this station
		return metroGraph.get(Station).size();
	}

	public ArrayList<Station> getAdjacent(Station stat) {
		//returns an array of stations connected to given station
		ArrayList<Station> stations = new ArrayList<>();
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (checkEdge(stat, entry.getKey()) != null) {
				stations.add(entry.getKey());
			}
		}
		return stations;
	}
	
	@Override
	public Station checkNode(String stationName) {
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if(entry.getKey().getName().equals(stationName)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
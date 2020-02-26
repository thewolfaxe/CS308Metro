import java.util.*;

public class Graph implements GraphInterface {

	private Map<Station, ArrayList<Track>> metroGraph;

	public Graph(Map<Station, ArrayList<Track>> map) {
		metroGraph = new HashMap<>();
		metroGraph = map;
	}

	//gets the route the user wants and returns it in a printable way
	@Override
	public LinkedList<Track> getRoute(Station start, Station finish) {
		ArrayList<Station> agenda = new ArrayList<>();				//used to keep track of potential paths
		HashMap<Station, Station> ancestors = new HashMap<>();		//dictionary used to build paths
		agenda.add(start);		//adding initial station to agenda
		Station current;

		while(!agenda.isEmpty()){
		    int index = agenda.indexOf(findShortest(agenda, ancestors, start));	//find index of shortest path so far
		    current = agenda.remove(index);		//set current and remove object from agenda

		    // if the current station is correct , build the path back up and return it
			if(current == finish) {
				return buildRoute(ancestors, current, start);
			}

			//if current is not the correct station, find adjacent stations and try again
			ArrayList<Station> temp = getAdjacent(current);
			for(Station stat: temp) {
				if(!ancestors.containsKey(stat)) {		//only add stations that aren't already in a shorter path
					ancestors.put(stat, current);
					agenda.add(stat);
				}
			}
		}
		return null;
	}

	//finds and returns the shortest route from the agenda
	private Station findShortest(ArrayList<Station> agenda, HashMap<Station, Station> ancestors, Station start) {
		int best = -1;		//init best
		Station shortest = agenda.get(0);		//init shortest station as the first in the agenda

		//loop through agenda and find the shortest path so far
		for(Station stat: agenda) {
			int attempt = pathSoFar(ancestors, stat, start);	//length of the path so far
			if(attempt < best || best == -1) {
				best = attempt;
				shortest = stat;
			}
		}
		return shortest;
	}

	//returns the length of a given path using a dictionary
	private int pathSoFar(HashMap<Station, Station> ancestors, Station current, Station start) {
		int total = 0;
		Station previous = current;
		String line, prevLine = null;

		//find out how long the current route is
		while(current != start) {
			++total;
			current = ancestors.get(current);
			line = checkEdge(previous, current).getLine();

			if(prevLine != null) {
				if (!prevLine.equals(line)) {		//if the tracks aren't on the same line, extra weight is added to avoid line switching
					total += current.getWeight();
				}
			}

			prevLine = line;
			previous = current;
		}
		++total;
		return total;
	}

	//similar to pathSoFar, but just returns the final route as a linked list of tracks
	private LinkedList<Track> buildRoute(HashMap<Station, Station> ancestors, Station current, Station start) {
		//first builds up an array of stations
		ArrayList<Station> route = new ArrayList<>();
		while (current != start) {
			route.add(current);
			current = ancestors.get(current);
		}

		route.add(start);
		Collections.reverse(route);		//reverses because it builds from end to beginning

		//creates the linked list of tracks for printing in userview
		//could move up to same loop as above but is here for easier readability
		LinkedList<Track> linkedRoute = new LinkedList<>();
		Track track;
		Station leaving, arriving;
		for(int i = 1; i < route.size(); ++i) {
			leaving = route.get(i-1);
			arriving = route.get(i);
			track = checkEdge(leaving, arriving);
			track.setLeaving(leaving.getName());
			track.setArriving(arriving.getName());
			linkedRoute.add(track);
		}

		return linkedRoute;
	}

	//returns an array of tracks connected to a given station
	public ArrayList<Track> getEdges(Station stat) {
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (entry.getKey().getName().equals(stat.getName())) {
				return metroGraph.get(stat);
			}
		}
		return null;
	}

	//checks whether or not an edge exits between two stations, returns the edge if it does exist
	public Track checkEdge(Station stat1, Station stat2) {
		//if the stations are the same return null
		if(stat1 == stat2)
			return null;

		//checks whether either end of a track as the same id as the second station
		ArrayList<Track> tracks = metroGraph.get(stat1);
		for (Track x : tracks) {
			if(x.getRightNode().equals(stat2.getID()) || x.getLeftNode().equals(stat2.getID())) {
				return x;
			}
		}
		return null;
	}

	//returns the number of tracks connected to a station
	public int getDegree(Station Station) {
		//returns the number of tracks connection to this station
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (entry.getKey().getName().equals(Station.getName())) {
				return metroGraph.get(Station).size();
			}
		}
		return -1;
	}

	//returns an array of stations connected to given station using checkEdge method
	public ArrayList<Station> getAdjacent(Station stat) {
		ArrayList<Station> stations = new ArrayList<>();
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (checkEdge(stat, entry.getKey()) != null) {
				stations.add(entry.getKey());
			}
		}
		return stations;
	}

	//checks whether a node with a given station name exists or not
	public Station checkNode(String stationName) {
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if(entry.getKey().getName().toLowerCase().equals(stationName)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
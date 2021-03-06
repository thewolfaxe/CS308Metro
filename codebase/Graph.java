import javax.swing.*;
import java.util.*;

public class Graph implements GraphInterface {

	private Map<Node, ArrayList<Edge>> metroGraph;

	public Graph(Map<Node, ArrayList<Edge>> map) {
		metroGraph = new HashMap<>();
		metroGraph = map;
	}

	//gets the route the user wants and returns it in a printable way
	@Override
	public LinkedList<Edge> getRoute(Node start, Node finish) {
		ArrayList<Node> agenda = new ArrayList<>();				//used to keep track of potential paths
		HashMap<Node, Node> ancestors = new HashMap<>();		//dictionary used to build paths
		agenda.add(start);		//adding initial station to agenda
		Node current;

		while(!agenda.isEmpty()){
		    int index = agenda.indexOf(findShortest(agenda, ancestors, start));	//find index of shortest path so far
		    current = agenda.remove(index);		//set current node to shortest and remove object from agenda

		    // if the current station is correct , build the path back up and return it
			if(current == finish) {
				return buildRoute(ancestors, current, start);
			}

			//if current is not the correct station, find adjacent stations and try again
			ArrayList<Node> temp = getAdjacent(current);
			for(Node stat: temp) {
				if(!ancestors.containsKey(stat)) {		//only add stations that aren't already in a shorter path
					ancestors.put(stat, current);
					agenda.add(stat);
				}
			}
		}
		return null;
	}

	//finds and returns the shortest route from the agenda
	private Node findShortest(ArrayList<Node> agenda, HashMap<Node, Node> ancestors, Node start) {
		int best = -1;		//init best
		Node shortest = agenda.get(0);		//init shortest station as the first in the agenda

		//loop through agenda and find the shortest path so far
		for(Node stat: agenda) {
			int attempt = pathSoFar(ancestors, stat, start);	//length of the path so far
			if(attempt < best || best == -1) {
				best = attempt;
				shortest = stat;
			}
		}
		return shortest;
	}

	//returns the length of a given path using a dictionary
	private int pathSoFar(HashMap<Node, Node> ancestors, Node current, Node start) {
		int total = 0;
		Node previous = current;
		String line = null, prevLine = null;
		boolean lineFlag = false;

		//find out how long the current route is
		while(current != start) {
			++total;
			current = ancestors.get(current);

			//As this is a multigraph there may be more than one edge going between the same nodes,
			//this makes sure that it correctly identifies the route with minimal line switches
			ArrayList<Edge> linePossibility = checkEdge(previous, current);
			if(linePossibility != null)
				for(Edge track: linePossibility) {
					if(track.getLine().equals(prevLine)) {
						lineFlag = true;
						line = track.getLine();
						break;
					} else {
						lineFlag = false;
						line = track.getLine();
					}
				}

			if(prevLine != null) {
				if (!lineFlag) {		//if the tracks aren't on the same line, extra weight is added to avoid line switching
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
	private LinkedList<Edge> buildRoute(HashMap<Node, Node> ancestors, Node current, Node start) {
		//first builds up an array of stations
		ArrayList<Node> route = new ArrayList<>();
		while (current != start) {
			route.add(current);
			current = ancestors.get(current);
		}

		route.add(start);
		Collections.reverse(route);		//reverses because it builds from end to beginning

		//creates the linked list of tracks for printing in userview
		//could move up to same loop as above but is here for easier readability
		LinkedList<Edge> linkedRoute = new LinkedList<>();
		Edge track = null, prevTrack = null;
		Node leaving, arriving;
		ArrayList<Edge> linePossibility;

		for(int i = 1; i < route.size(); ++i) {
			leaving = route.get(i-1);
			arriving = route.get(i);

			//similar to in pathSoFar(), as we are dealing with a multigraph, we need to make sure
			//that we pick the right edge between the nodes on our graph
			linePossibility = checkEdge(leaving, arriving);
			if(linePossibility != null) {
				for (Edge trackPoss : linePossibility) {
					if (prevTrack != null) {
						if (trackPoss.getLine().equals(prevTrack.getLine())) {
							track = trackPoss;
							break;
						} else {
							track = trackPoss;
						}
					} else {
						track = linePossibility.get(0);
					}
				}
			} else {
				System.out.println("This broke in buildRoute() and really shouldn't've");
			}

			if(track != null) {
				track.setLeaving(leaving.getName());
				track.setArriving(arriving.getName());
				linkedRoute.add(track);
				prevTrack = track;
			} else {
				System.out.println("This really shouldn't show as there is deffo a route at this point");
			}
		}

		return linkedRoute;
	}


	//checks whether or not an edge exits between two stations, returns the edge if it does exist
	public ArrayList<Edge> checkEdge(Node stat1, Node stat2) {
		//if the stations are the same return null
        ArrayList<Edge> possibles = new ArrayList<>();
		if(stat1.equals(stat2))
			return null;

		if(metroGraph.get(stat1) == null) {
			return null;
		}
		//checks whether either end of a track as the same id as the second station
		ArrayList<Edge> tracks = metroGraph.get(stat1);
		for (Edge x : tracks) {
			if(x.getRightNode().equals(stat2.getID()) || x.getLeftNode().equals(stat2.getID())) {
				//return x;
				possibles.add(x);
			}
		}
		if(possibles.size() != 0)
			return possibles;
		return null;
	}

	//returns the number of tracks connected to a station
	public int getDegree(Node Station) {
		//returns the number of tracks connection to this station
		for (Map.Entry<Node, ArrayList<Edge>> entry : metroGraph.entrySet()) {
			if (entry.getKey().getName().equals(Station.getName())) {
				return metroGraph.get(Station).size();
			}
		}
		return -1;
	}

	//returns an array of stations connected to given station using checkEdge method
	public ArrayList<Node> getAdjacent(Node stat) {
		ArrayList<Node> stations = new ArrayList<>();
		for (Map.Entry<Node, ArrayList<Edge>> entry : metroGraph.entrySet()) {
			if (checkEdge(stat, entry.getKey()) != null) {
				stations.add(entry.getKey());
			}
		}
		return stations;
	}

	@Override
	public boolean checkDuplicateNode(String stationName) {
		stationName = stationName.toLowerCase();
		int found = 0;
		for (Map.Entry<Node, ArrayList<Edge>> entry : metroGraph.entrySet()) {
			if(entry.getKey().getName().toLowerCase().equals(stationName)) {
				found ++;
			}
		}
		return found > 1;
	}

	@Override
	public Node getNodeByLine(String stationName, String line) {
		stationName = stationName.toLowerCase();
		for(Map.Entry<Node, ArrayList<Edge>> entry : metroGraph.entrySet()) {
			if(entry.getKey().getName().toLowerCase().equals(stationName)){
				if(metroGraph.get(entry.getKey()).get(0).getLine().toLowerCase().equals(line)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}
	
	@Override
	//checks whether a node with a given station name exists or not
	public Node getNode(String stationName) {
		stationName = stationName.toLowerCase();
		for (Map.Entry<Node, ArrayList<Edge>> entry : metroGraph.entrySet()) {
			if(entry.getKey().getName().toLowerCase().equals(stationName)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
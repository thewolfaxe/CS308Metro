import java.util.*;

public class Graph implements GraphInterface {

	//Map<Station, Track> metroGraph = new HashMap<Station, Track>();
	Map<Station, ArrayList<Track>> metroGraph;

	public Graph(Map<Station, ArrayList<Track>> map) {
		metroGraph = new HashMap<>();
		metroGraph = map;
	}

	@Override
	public LinkedList<Track> getRoute(Station start, Station finish) {
		ArrayList<Station> agenda = new ArrayList<>();
		HashMap<Station, Station> ancestors = new HashMap<>();
		agenda.add(start);
		Station current;

		while(!agenda.isEmpty()){
		    current = findShortest(agenda, ancestors, start);
		    int index = agenda.indexOf(current);
		    current = agenda.remove(index);

//			System.out.println(current.getName());

			if(current == finish) {
				System.out.println("\n\nFound the station");
				ArrayList<Station> route = buildRoute(ancestors, current, start);
				for(Station stat: route) {
					System.out.println(stat.getName());
				}
				return null;
			}

			ArrayList<Station> temp = getAdjacent(current);
			for(Station stat: temp) {
//				System.out.println(stat.getName());
				if(!ancestors.containsKey(stat)) {
					ancestors.put(stat, current);
					agenda.add(stat);
				}
			}
//			for(Map.Entry<Station, Station> entry: ancestors.entrySet())
//				System.out.println("" + entry.getKey().getName() + ", " + entry.getValue().getName());
//			return null;
		}

		return null;
	}

	public Station findShortest(ArrayList<Station> agenda, HashMap<Station, Station> ancestors, Station start) {
		int best = -1;		//init best
		Station shortest = agenda.get(0);		//init shortest station as the first in the agenda
//        int i = 0, j = 0;

		//loop through agenda and find the shortest path so far
		for(Station stat: agenda) {
			int attempt = pathSoFar(ancestors, stat, start);
//			i++;
			if(attempt < best || best == -1) {
//				j++;
				best = attempt;
				shortest = stat;
			}
		}
//		System.out.println("i: " + i + " j: " + j);
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

			if(prevLine != null) {
				System.out.println("ive dine");
				if (!prevLine.equals(line)) {
					total += current.getWeight();
				}
			}

			prevLine = line;
			previous = current;
		}
		++total;
//		System.out.println("total: " + total);
		return total;
	}

	public ArrayList<Station> buildRoute(HashMap<Station, Station> ancestors, Station current, Station start) {
		ArrayList<Station> route = new ArrayList<>();

//		for(Map.Entry<Station, Station> entry: ancestors.entrySet()) {
//			System.out.println("" + entry.getKey().getName() + ", " + entry.getValue().getName());
//		}

		while (current != start) {
			route.add(current);
			current = ancestors.get(current);
		}

		route.add(start);
		Collections.reverse(route);
		return route;
	}

	public ArrayList<Track> getEdges(Station stat) {
		//returns an array of tracks connected to this station
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (entry.getKey().getName().equals(stat.getName())) {
				return metroGraph.get(stat);
			}
		}
		return null;
	}

	@Override
	public Track checkEdge(Station stat1, Station stat2) {
		if(stat1 == stat2)
			return null;

//		System.out.println("stat1 id: " + stat1.getID() + ", stat2 id: " + stat2.getID());

		//checks whether a track exists between the 2 stations
		ArrayList<Track> tracks1 = metroGraph.get(stat1);
		ArrayList<Track> tracks2 = metroGraph.get(stat2);
		for (Track x : tracks1) {
//			System.out.println("x right node: " + x.getRightNode() + ", x left node: " + x.getLeftNode());
			if(x.getRightNode().equals(stat2.getID()) || x.getLeftNode().equals(stat2.getID())) {
//				System.out.println("adding x ");
				return x;
			} //else {
//				System.out.println("not adding x");
			//}
		}
//		for (Track y : tracks2) {
////			System.out.println("y right node: " + y.getRightNode() + ", y left node: " + y.getLeftNode());
//			if(y.getRightNode().equals(stat2.getID()) || y.getLeftNode().equals(stat2.getID())) {
//				System.out.println("adding y");
//				return y;
//			} else {
//				System.out.println("not adding y");
//			}
//		}
//		System.out.println("I make it past bs");
		return null;
	}

	public int getDegree(Station Station) {
		//returns the number of tracks connection to this station
		for (Map.Entry<Station, ArrayList<Track>> entry : metroGraph.entrySet()) {
			if (entry.getKey().getName().equals(Station.getName())) {
				return metroGraph.get(Station).size();
			}
		}
		return -1;
	}

	//something is wrong here
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
			if(entry.getKey().getName().toLowerCase().equals(stationName)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
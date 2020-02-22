import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;

public class Controller {
	
	private static Station current;
	private static Station destination;
	private static Graph graph;
	private static UserView view;
	
	public Controller() {
		view = new UserView();
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.setup();
		boolean exit = false;
		
		while(!exit) {
			current = getStation();
			destination = getStation();
			getRoute(graph);
			exit = getExit();
		}
	}
	
	public void setup() {
		System.out.println("Setting up graph");
		Parser parse = new Parser();
		try {
			Map<Station, ArrayList<Track>> metroGraph = parse.loadFile();
			graph = new Graph(metroGraph);
		} catch (IOException | BadFileException e) {
			e.printStackTrace();
		}
	}
	
	public static Station getStation() {
		while(true) {
			String stationName = view.getCurrentNode();
			stationName = stationName.replaceAll("\\s", "").toLowerCase();
			Station station = graph.getNode(stationName);
			if (station != null) {
				return station;
			}
			System.out.println("That station does not exist.");
		}
	}
	
	public static boolean getExit() {
		while(true) {
			String shouldContinue = view.getExit();
			shouldContinue = shouldContinue.replaceAll("\\s", "").toLowerCase();
			if (shouldContinue.equals("yes")) {
				return false;
			}
			else if (shouldContinue.equals("no")) {
				return true;
			}
			else {
				System.out.println("Please enter \"yes\" or \"no\".");
			}
		}
	}
	
	public static void getRoute(Graph graph) {
		LinkedList<Track> route = graph.getRoute(current, destination);
		view.displayRoute(route);
	}
}
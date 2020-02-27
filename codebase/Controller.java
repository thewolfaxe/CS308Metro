import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;

public class Controller {
	
	private static Node current;
	private static Node destination;
	private static Graph graph;
	private static UserView view;
	
	public Controller(InputStream input) {
		view = new UserView(input);
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller(System.in);
		controller.setup();
		boolean exit = false;
		
		while(!exit) {
			current = controller.getStartStation();
			destination = controller.getEndStation();
			controller.getRoute(graph);
			exit = controller.getExit();
		}
		System.out.println(" ");
		System.out.println("############################################################");
		System.out.println(" ");
		System.out.println("	Thank you for using this Boston Metro Route Finder.");
		System.out.println("			Quitting...");
		System.out.println(" ");
		System.out.println("############################################################");
	}
	
	public Graph setup() {
		view.printSetup();
		Parser parse = new Parser();
		try {
			Map<Node, ArrayList<Edge>> metroGraph = parse.loadFile("bostonMetro.txt");
			graph = new Graph(metroGraph);
		} catch (IOException | BadFileException e) {
			e.printStackTrace();
		}
		return graph;
	}
	
	public Node getStartStation() {
		while(true) {
			Node station;
			String stationName = view.getCurrentNode();
			stationName = stationName.replaceAll("\\s", "").toLowerCase();
			boolean dupe = graph.checkDuplicateNode(stationName);
			if(dupe) {
				String line = view.getCurrentLine();
				line = line.replaceAll("\\s", "").toLowerCase();
				station = graph.getNodeByLine(stationName, line);
				return station;
			}
			else {
				station = graph.getNode(stationName);
				if (station != null) {
					return station;
				}
				System.out.println(" ");
				System.out.println("	That station does not exist.");
			}
		}
	}
	
	public Node getEndStation() {
		while(true) {
			Node station;
			String stationName = view.getDestination();
			stationName = stationName.replaceAll("\\s", "").toLowerCase();
			boolean dupe = graph.checkDuplicateNode(stationName);
			if(dupe) {
				String line = view.getCurrentLine();
				line = line.replaceAll("\\s", "").toLowerCase();
				station = graph.getNodeByLine(stationName, line);
				return station;
			}
			else {
				station = graph.getNode(stationName);
				if (station != null) {
					return station;
				}
				System.out.println(" ");
				System.out.println("	That station does not exist.");
			}
		}
	}
	
	public boolean getExit() {
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
				System.out.println(" ");
				System.out.println("	Please enter \"yes\" or \"no\".");
			}
		}
	}
	
	public LinkedList<Edge> getRoute(Graph graph) {
		LinkedList<Edge> route = graph.getRoute(current, destination);
		while(true) {
			String shouldContinue = view.getRouteOption();
			shouldContinue = shouldContinue.replaceAll("\\s", "").toLowerCase();
			if (shouldContinue.equals("yes")) {
				view.displayDetailedRoute(route);
				return route;
			}
			else if (shouldContinue.equals("no")) {
				view.displayRoute(route);
				return route;
			}
			else {
				System.out.println(" ");
				System.out.println("	Please enter \"yes\" or \"no\".");
			}
		}
	}
}
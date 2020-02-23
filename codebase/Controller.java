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
			getCurrent();
			getDestination();
			getRoute(graph);
			exit = getExit();
		}
		System.out.println("Thank you for using this boston metro map search.\nQuitting...");
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
	
	public static void getCurrent() {
		String currentString;
		boolean test = false;
		
		while(!test) {
			currentString = view.getCurrentNode();
			currentString = currentString.replaceAll("\\s", "").toLowerCase();
			if (graph.checkNode(currentString) != null) {
				test = true;
				current = graph.checkNode(currentString);
			}
			else {
				System.out.println("That station does not exist.");
			}
		}
		
	}
	
	public static void getDestination() {
		String currentString;
		boolean test = false;
		
		while(!test) {
			currentString = view.getDestination();
			currentString = currentString.replaceAll("\\s", "").toLowerCase();
			if (graph.checkNode(currentString) != null) {
				test = true;
				destination = graph.checkNode(currentString);
			}
			else {
				System.out.println("That station does not exist.");
			}
		}
	}
	
	public static boolean getExit() {
		String currentString;
		boolean test = false;
		
		while(!test) {
			currentString = view.getExit();
			currentString = currentString.replaceAll("\\s", "").toLowerCase();
			if (currentString.equals("yes")) {
				return false;
			}
			else if (currentString.equals("no")) {
				return true;
			}
			else {
				System.out.println("Please enter \"yes\" or \"no\".");
			}
		}
		return false;
	}
	
	public static void getRoute(Graph graph) {
		LinkedList<Track> route = graph.getRoute(current, destination);

		view.displayRoute(route);
	}
}
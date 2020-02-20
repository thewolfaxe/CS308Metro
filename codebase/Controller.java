import java.util.LinkedList;

public class Controller {
	
	private Station current;
	private Station destination;
	private Graph graph;
	private UserView view;
	
	public Controller() {
		graph = new Graph();
		view = new UserView();
	}
	
	public void setup() {
		System.out.println("Setting up graph");
		graph = graph.setup();
	}
	
	public void getCurrent() {
		String currentString;
		boolean test = false;
		
		while(!test) {
			currentString = view.getCurrentNode();
			if (graph.checkNode(currentString) != null) {
				test = true;
				current = graph.checkNode(currentString);
			}
			else {
				System.out.println("That station does not exist.");
			}
		}
		
	}
	
	public void getDestination() {
		String currentString;
		boolean test = false;
		
		while(!test) {
			currentString = view.getDestination();
			if (graph.checkNode(currentString) != null) {
				test = true;
				destination = graph.checkNode(currentString);
			}
			else {
				System.out.println("That station does not exist.");
			}
		}
	}
	
	public boolean getExit() {
		String currentString;
		boolean test = false;
		
		while(!test) {
			currentString = view.getExit();
			if (currentString == "yes") {
				return false;
			}
			if (currentString == "no") {
				return true;
			}
		}
	}
	
	public void getRoute(Graph graph) {
		LinkedList<Track> route = graph.getRoute(current, destination);

		view.displayRoute(route);
	}

	public void main(String[] args) {
		Controller controller = new Controller();
		controller.setup();
		boolean exit = false;
		
		while(!exit) {
			getCurrent();
			getDestination();
			getRoute(graph);
			
		}
		//loop for entering stations and showing route
		//should do something along the lines of
		//getCurrent()
		//getDestinations
		//getRoute()
		//displayRoute()
	}
}
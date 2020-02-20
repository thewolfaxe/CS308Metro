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
		current = view.getCurrentNode();
	}
	
	public void getDestination() {
		destination = view.getDestination();
	}
	
	public void getRoute(Graph graph) {
		LinkedList<Track> route = graph.getRoute(current, destination);

		view.displayRoute(route);
	}

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.setup();

		
		//loop for entering stations and showing route
		//should do something along the lines of
		//getCurrent()
		//getDestinations
		//getRoute()
		//displayRoute()
	}
}
import java.io.InputStream;
import java.util.*;

public class UserView {
	private Scanner scanner;
	
	public UserView(InputStream inputStream) {
		scanner = new Scanner(inputStream);
	}
	
	public void printSetup() {
		System.out.println("############################################################");
		System.out.println(" ");
		System.out.println("	Welcome to the Boston Metro System Route Finder");
		System.out.println(" ");
		System.out.println("############################################################");
		System.out.println(" ");
		System.out.println("------------------------------------------------------------");
	}
	
	public String getCurrentNode() {
		String answer = "";
		System.out.println(" ");
		System.out.println("	Please enter your current station.");
		System.out.println(" ");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public String getDestination() {
		String answer = "";
		System.out.println(" ");
		System.out.println("	Please enter your destination station");
		System.out.println(" ");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public String getExit() {
		String answer = "";
		System.out.println(" ");
		System.out.println("	Would you like to search another route? yes/no");
		System.out.println(" ");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public String getRouteOption() {
		String answer = "";
		System.out.println(" ");
		System.out.println("	Would you like a detailed route view? yes/no");
		System.out.println(" ");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public void displayRoute(LinkedList<Edge> route) {
		String line = route.get(0).getLine();
		ArrayList<Edge> simpleRoute = new ArrayList<>();
		System.out.println(" ");
		System.out.println("Number of stations on your route: " + (route.size() + 1));
		System.out.println("Your route is:");
		System.out.println(" ");
		simpleRoute.add(route.get(0));
		for(Edge track: route) {
			if(!track.getLine().equals(line)) {
				simpleRoute.add(track);
				line = track.getLine();
			}	
		}
		simpleRoute.add(route.get(route.size()-1));
		for(int i = 0; i < (simpleRoute.size()-1); i++) {
			if(i == simpleRoute.size() -2) {
				System.out.println("Leaving: " + simpleRoute.get(i).getLeaving() + ", arriving: " + simpleRoute.get(i+1).getArriving() + ", on line: " + simpleRoute.get(i).getLine());
			}
			else {
				System.out.println("Leaving: " + simpleRoute.get(i).getLeaving() + ", arriving: " + simpleRoute.get(i+1).getLeaving() + ", on line: " + simpleRoute.get(i).getLine());
			}
		}
	}
	
	public void displayDetailedRoute(LinkedList<Edge> route) {
		//display the route by going through the linked list,
		//apparently this needs to give pretty detailed full sentence
		//instructions about stations and stuff
		System.out.println(" ");
		System.out.println("Number of stations on your route: " + (route.size() + 1));
		System.out.println("Your route is:");
		System.out.println(" ");
		for(Edge track: route) {
			System.out.println("Leaving: " + track.getLeaving() + ", arriving: " + track.getArriving() + ", on line: " + track.getLine());
		}
	}
}
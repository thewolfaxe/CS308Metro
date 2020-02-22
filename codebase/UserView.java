import java.util.*;

public class UserView {
	private Scanner scanner;
	
	public UserView() {
		scanner = new Scanner(System.in);
		
	}
	
	public String getCurrentNode() {
		String answer = "";
		System.out.println("Please enter your current station.");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public String getDestination() {
		String answer = "";
		System.out.println("Please enter your destination station");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public String getExit() {
		String answer = "";
		System.out.println("Would you like to search another route? yes/no");
		if(scanner.hasNextLine()) {
			answer = scanner.nextLine();
		}
		return answer;
	}
	
	public void displayRoute(LinkedList<Track> route) {
		//display the route by going through the linked list,
		//apparently this needs to give pretty detailed full sentence
		//instructions about stations and stuff
	}
}
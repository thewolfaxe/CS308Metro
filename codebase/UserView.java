import java.util.*;

public class UserView {
	
	public UserView() {
		
	}
	
	public String getCurrentNode() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your current station.");
		String answer = scanner.nextLine();
		scanner.close();
		return answer;
	}
	
	public String getDestination() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your destination station");
		String answer = scanner.nextLine();
		scanner.close();
		return answer;
	}
	
	public String getExit() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your destination station");
		String answer = scanner.nextLine();
		scanner.close();
		return answer;
	}
	
	public void displayRoute(LinkedList<Track> route) {
		//display the route by going through the linked list,
		//apparently this needs to give pretty detailed full sentence
		//instructions about stations and stuff
	}
}
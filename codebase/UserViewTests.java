import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserViewTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void getCurrentNodeTest() throws Exception {
		String expectedStation = "Oakgrove";
		InputStream testInput = new ByteArrayInputStream( expectedStation.getBytes("UTF-8") );
		UserView view = new UserView(testInput);
		String actualStation = view.getCurrentNode();
		assertEquals(expectedStation, actualStation);
	}
	
	@Test
	void getDestinationTest() throws Exception {
		String expectedStation = "Oakgrove";
		InputStream testInput = new ByteArrayInputStream( expectedStation.getBytes("UTF-8") );
		UserView view = new UserView(testInput);
		String actualStation = view.getDestination();
		assertEquals(expectedStation, actualStation);
	}
	
	@Test
	void getExitTest() throws Exception {
		String expectedAnswer = "yes";
		InputStream testInput = new ByteArrayInputStream( expectedAnswer.getBytes("UTF-8") );
		UserView view = new UserView(testInput);
		String actualAnswer = view.getExit();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	@Test
	void getRouteOption() throws Exception {
		String expectedAnswer = "yes";
		InputStream testInput = new ByteArrayInputStream( expectedAnswer.getBytes("UTF-8") );
		UserView view = new UserView(testInput);
		String actualAnswer = view.getRouteOption();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	
	
	
	

}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrackTests
{
    List<Track> temp = new ArrayList<>();
    @BeforeEach
    void setUp() throws Exception
    {
        temp.add(new Track("Red", "3", "1", 0));
        temp.add(new Track("Green", "10", "11", 1));
        temp.add(new Track("Blue", "13", "20", 2));

    }

    @Test
    void getLeftNodeTest() throws Exception
    {
        String expected = "3";
        String actual = temp.get(0).getLeftNode();
        assertEquals(expected,actual);
    }
    @Test
    void getRightNodeTest() throws Exception
    {
        String expected = "11";
        String actual = temp.get(1).getRightNode();
        assertEquals(expected,actual);
    }
    @Test
    void getWeightTest() throws Exception
    {
        int expected = 2;
        int actual = temp.get(2).getWeight();
        assertEquals(expected,actual);
    }
    @Test
    void getLineTest() throws Exception
    {
        String expected = "Red";
        String actual = temp.get(0).getLine();
        assertEquals(expected, actual);
    }
}

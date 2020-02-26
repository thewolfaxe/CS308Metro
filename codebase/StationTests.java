import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StationTests
{
    List<Station> temp = new ArrayList<>();
    @BeforeEach
    void setUp() throws Exception
    {
        Station alewife = new Station("8", "Alewife");
        temp.add(alewife);
        Station davis = new Station("7", "Davis");
        temp.add(davis);
        Station porter = new Station("10", "Porter");
        temp.add(porter);
    }

    @Test
    void getNameTest() throws Exception
    {
        String expected = "Alewife";
        String actual = temp.get(0).getName();
        assertEquals(expected,actual);
    }
    @Test
    void getIDTest() throws Exception
    {
        String expected = "7";
        String actual = temp.get(1).getID();
        assertEquals(expected,actual);
    }
    @Test
    void getWeightTest() throws Exception
    {
        int expected = 15;
        int actual = temp.get(2).getWeight();
        assertEquals(expected,actual);
    }

}

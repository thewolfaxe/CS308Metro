import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

interface GraphInterface
{
    LinkedList<Track> getRoute(Station start, Station finish);
}
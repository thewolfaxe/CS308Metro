import java.util.LinkedList;

interface GraphInterface
{
    LinkedList<Track> getRoute(Station start, Station finish);

    Track checkEdge(Station stat1, Station stat2);
    
    Station getNode(String stationName);
}
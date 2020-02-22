import java.util.LinkedList;

interface GraphInterface
{
    //public void addEdge(Station stat1, Station stat2);

    public LinkedList<Track> getRoute(Station start, Station finish);

    public Track checkEdge(Station stat1, Station stat2);
    
    public Station getNode(String stationName);
}
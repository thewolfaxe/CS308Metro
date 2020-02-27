import java.util.LinkedList;

interface GraphInterface
{
    LinkedList<Edge> getRoute(Node start, Node finish);

    Edge checkEdge(Node stat1, Node stat2);
    
    Node getNode(String stationName);
}
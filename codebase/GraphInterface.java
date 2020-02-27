import java.util.LinkedList;

interface GraphInterface
{
    LinkedList<Edge> getRoute(Node start, Node finish);

    Node getNode(String stationName);
}
import java.util.LinkedList;

public abstract class GraphADT {
	
	private LinkedList<Station>[] graphRep;
	
	public GraphADT() {
		
	}
	
	public Boolean checkEmpty() {
		return null;
	}
	
	public Integer numVert() {
		return null;
	}
	
	public Integer numEdge() {
		return null;
	}
	
	public Boolean checkEdge(Station one, Station two) {
		return null;
	}
	
	public Boolean insertStation(Station Station) {
		return null;
	}
	
	public Boolean insertEdge(Track track) {
		return null;
	}
	
	public Boolean removeStation(Station Station) {
		return null;
	}
	
	public Boolean removeEdge(Track track) {
		return null;
	}
	
	public Integer getDegree(Station Station) {
		return null;
	}
	
	public Station[] getAdjacent(Station Station) {
		return null;
	}
	
	public Boolean isAdjacent(Station one, Station two) {
		return null;
	}
	
}
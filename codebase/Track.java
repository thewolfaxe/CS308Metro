public class Track implements EdgeInterface {

	private int weight;
	private String line;
	private String inStationID;
	private String outStationID;
	private String leaving;
	private String arriving;

	public Track(String line, String leftStationID, String rightStationID, int weight)
	{
		this.line = line;
		this.inStationID = leftStationID;
		this.outStationID = rightStationID;
		this.weight = 1;
	}

	@Override
	public String getLeftNode() {
		return inStationID;
	}

	@Override
	public String getRightNode() {
		return outStationID;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	public String getLine() {
		return this.line;
	}

	public void setLeaving(String leaving) {
		this.leaving = leaving;
	}

	public String getLeaving() {
		return leaving;
	}

	public void setArriving(String arriving) {
		this.arriving = arriving;
	}

	public String getArriving() {
		return arriving;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Track))
			return false;
		Track otherTrack = (Track)other;
		boolean equal = line.equals(otherTrack.getLine()) &&
				inStationID.equals(otherTrack.getLeftNode()) &&
				outStationID.equals(otherTrack.getRightNode()) &&
				weight == otherTrack.getWeight() &&
				(leaving == null && otherTrack.getLeaving() == null ||
				leaving.equals(otherTrack.getLeaving())) &&
				(arriving == null && otherTrack.getArriving() == null ||
				arriving.equals(otherTrack.getArriving()));
		return equal;
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
		hash = 17 * hash + line.hashCode();
		hash = 17 * hash + inStationID.hashCode();
		hash = 17 * hash + outStationID.hashCode();
		hash = 17 * hash + weight;
		
		if (leaving != null)
			hash = 17 * hash + leaving.hashCode();
		
		if (arriving != null)
			hash = 17 * hash + arriving.hashCode();
		
		return hash;
	}
}
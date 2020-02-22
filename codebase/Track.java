public class Track implements EdgeInterface {

	private int weight;
	private String line;
	private String inStation;
	private String outStation;

	public Track(String line, String inStationID, String outStationID, int weight)
	{
		this.line = line;
		this.inStation = inStationID;
		this.outStation = outStationID;
		this.weight = weight;
	}

	@Override
	public String getLeftNode() {
		return inStation;
	}

	@Override
	public String getRightNode() {
		return outStation;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public String getLine() {
		return this.line;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Track))
			return false;
		Track otherTrack = (Track)other;
		return line.equals(otherTrack.getLine()) &&
				inStation.equals(otherTrack.getLeftNode()) &&
				outStation.equals(otherTrack.getRightNode()) &&
				weight == otherTrack.getWeight();
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
		hash = 17 * hash + line.hashCode();
		hash = 17 * hash + inStation.hashCode();
		hash = 17 * hash + outStation.hashCode();
		hash = 17 * hash + weight;
		return hash;
	}
}
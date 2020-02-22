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
		this.weight = 1;
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

	public String getLine() {
		return line;
	}
}
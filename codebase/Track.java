public class Track implements EdgeInterface {

	private int weight;
	private String line;
	private String inStation;
	private int inStationID;
	private String outStation;
	private int outStationID;

	public Track(String line, String inStation, int inStationID, String outStation, int outStationID, int weight)
	{
		this.line = line;
		this.inStation = inStation;
		this.inStationID = inStationID;
		this.outStation = outStation;
		this.outStationID = outStationID;
		this.weight = weight;
	}

	@Override
	public String getLeftNode() {
		return inStation;
	}

	@Override
	public int getLeftNodeID() {
		return inStationID;
	}


	@Override
	public String getRightNode() {
		return outStation;
	}

	@Override
	public int getRightNodeID() {
		return outStationID;
	}

	@Override
	public int getWeight() {
		return weight;
	}
}
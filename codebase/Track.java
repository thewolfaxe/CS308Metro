public class Track implements EdgeInterface {

	private int weight;
	private String line;
	private String inStation;
	private String inStationID;
	private String outStation;
	private String outStationID;

	public Track(String line, String inStation, String inStationID, String outStation, String outStationID, int weight)
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
	public String getLeftNodeID() {
		return inStationID;
	}


	@Override
	public String getRightNode() {
		return outStation;
	}

	@Override
	public String getRightNodeID() {
		return outStationID;
	}

	@Override
	public int getWeight() {
		return weight;
	}
}
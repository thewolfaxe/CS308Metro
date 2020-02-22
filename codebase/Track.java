public class Track implements EdgeInterface {

	private int weight;
	private String line;
	private String inStation;
	private String outStation;

	public Track(String line, String leftStationID, String rightStationID, int weight)
	{
		this.line = line;
		this.inStation = leftStationID;
		this.outStation = rightStationID;
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

	@Override
	public String getLine() {
		return this.line;
	}
}
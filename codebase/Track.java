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

	@Override
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
}
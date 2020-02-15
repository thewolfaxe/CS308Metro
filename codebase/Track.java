public class Track implements EdgeInterface {

	private int weight;
	private String line;
	private String inStation;
	private String outStation;
	public Track(String line, String outStation, String inStation)
	{
		this.line = line;
		this.inStation = inStation;
		this.outStation = outStation;
	}

	@Override
	public String getLeftNode() {
		return outStation;
	}

	@Override
	public String getRightNode() {
		return inStation;
	}

	@Override
	public Integer getWeight() {
		return weight;
	}
}
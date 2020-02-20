public class Station implements NodeInterface {
	
	private int weight;
	private String id, name;
	
	public Station(String id, String name)
	{
		this.name = name;
		this.id = id;
		this.weight = 15;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public String getID()
	{
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
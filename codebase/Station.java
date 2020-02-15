public class Station implements NodeInterface {
	
	private Integer weight;
	private String id, name;
	
	public Station(String id, String name)
	{
		this.name = name;
		this.id = id;
	}
	
	public Integer getWeight() {
		return null;
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
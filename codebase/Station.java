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
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Station))
			return false;
		Station otherStation = (Station)other;
		return id.equals(otherStation.getID()) &&
				name.equals(otherStation.getName()) &&
				weight == otherStation.getWeight();
	}
	
	@Override
	public int hashCode() {
		int hash = 11;
		hash = 17 * hash + id.hashCode();
		hash = 17 * hash + name.hashCode();
		hash = 17 * hash + weight;
		return hash;
	}
}
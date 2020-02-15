interface GraphInterface
{
	public Boolean checkEmpty();

	public Integer numVert();

	public Integer numEdge();

	public Boolean checkEdge(Station one, Station two);

	public Integer getDegree(Station Station);

	public Station[] getAdjacent(Station Station);

	public Boolean isAdjacent(Station one, Station two);
}
package model.logic;

public class Pista implements Comparable<Pista>
{
	private String id;
	
	private Double avgVader;
	
	private Double totalAvgVader;
	
	private int numHashtags;
	
	
	public Pista(String id)
	{
		this.id = id;
		avgVader = 0.0;
		totalAvgVader = 0.0;
		numHashtags = 0;
	}
	public void sumarHashTag(double avgVader)
	{
		this.totalAvgVader+=avgVader;
		numHashtags++;
		this.avgVader = totalAvgVader/numHashtags;
	}
	public double daravgVader()
	{
		return avgVader;
	}
	public int darNumHashTags()
	{
		return numHashtags;
	}
	@Override
	public int compareTo(Pista o) 
	{
		return id.compareTo(o.id);
	}
}

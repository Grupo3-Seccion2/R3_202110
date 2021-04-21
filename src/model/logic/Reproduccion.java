package model.logic;

import java.util.Date;

public class Reproduccion implements Comparable<Reproduccion>
{
	// Propiedades musicales de la reproduccion
	private double instrumentalness; //0
	private double liveness;
	private double speechiness;
	private double danceability;
	private double valence;
	private double loudness;
	private double tempo;
	private double acousticness;
	private double energy;
	private double mode;
	private double key;//10
	
	//Propiedades del evento de reproduccion
	
	private String id;//18
	
	private String idArtist;//11
	
	private String idTrack;//13
	
	private String idUser;//17
	
	private Date dateCreation;//14
	
	
	public Reproduccion(double instrumentalness,double liveness,double speechiness, double danceability,double valence,double loudness,double tempo,double acousticness,double energy,double mode,double key,String id,String idArtist,String idTrack,String idUser,Date dateCreation)
	{
		this.instrumentalness= instrumentalness;
		this.liveness= liveness;
		this.speechiness= speechiness;
		this.danceability = danceability;
		this.valence = valence;
		this.loudness = loudness;
		this.tempo = tempo;
		this.acousticness = acousticness;
		this.energy = energy;
		this.mode = mode;
		this.key = key;
		
		this.id= id;
		this.idArtist= idArtist;
		this.idTrack= idTrack;
		this.idUser=idUser;
		this.dateCreation= dateCreation;
		
	}
	
	public double darLlaveArbol()
	{
		return danceability;
	}

	@Override
	public int compareTo(Reproduccion o) 
	{
		
		return this.id.compareTo(o.id) ;
	}
}

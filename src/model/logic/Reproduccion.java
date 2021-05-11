package model.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reproduccion implements Comparable<Reproduccion>
{
	// Propiedades musicales de la reproduccion
	private double instrumentalness; 
	private double liveness;
	private double speechiness;
	private double danceability;
	private double valence;
	private double loudness;
	private double tempo;
	private double acousticness;
	private double energy;
	private double mode;
	private double key;
	
	
	
	//Propiedades del evento de reproduccion
	
	private String id;
	
	private String idArtist;
	
	private String idTrack;
	
	private String idUser;
	
	private Date dateCreation;
	
	private Double llaveArbol;
	
	private Date horaCreacion;
	
	private double avgVaderHashTag;
	
	public Reproduccion(double instrumentalness,double liveness,double speechiness, double danceability,double valence,double loudness,double tempo,double acousticness,double energy,double mode,double key,String id,String idArtist,String idTrack,String idUser,Date dateCreation, Date horaCreacion, double avgVaderHashTag)
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
		this.horaCreacion = horaCreacion;
		this.avgVaderHashTag =avgVaderHashTag;
		
	}
	public double darInstrumentalness()
	{
		return instrumentalness;
	}
	public double darLiveness()
	{
		return liveness;
	}
	public double darSpeechiness()
	{
		return speechiness;
	
	}
	public double darDanceability()
	{
		return danceability;
	}
	public double darValence()
	{
		return valence;
	}
	public double darLoudness()
	{
		return loudness;
	}
	
	public double darTempo()
	{
		return tempo;
	}
	
	public double darAcousticness()
	{
		return acousticness;
	}
	
	public double darEnergy()
	{
		return energy;
	}
	
	public double darMode()
	{
		return mode;
	}
	
	public double darKey()
	{
		return key;
	}
	
	public String darId()
	{
		return id;
	}
	
	public String darArtistId()
	{
		return idArtist;
	}
	
	public String darTrackId()
	{
		return idTrack;
	}
	public String darUserId()
	{
		return idUser;
	}
	
	public Date darCreationDate()
	{
		return dateCreation;
	}
	public double darLlaveArbol()
	{
		return llaveArbol;
	}
	public void setLLaveArbol(String caracteristica)
	{
		if(caracteristica.compareToIgnoreCase("instrumentalness")==0)
		{
			llaveArbol = instrumentalness;
		}
		else if(caracteristica.compareToIgnoreCase("liveness")==0)
		{
			llaveArbol = liveness;
		}
		else if(caracteristica.compareToIgnoreCase("speechiness")==0)
		{
			llaveArbol = speechiness;
		}
		else if(caracteristica.compareToIgnoreCase("danceability")==0)
		{
			llaveArbol =danceability;
		}
		else if(caracteristica.compareToIgnoreCase("valence")==0)
		{
			llaveArbol = valence;
		}
		else if (caracteristica.compareToIgnoreCase("loudness")==0)
		{
			llaveArbol= loudness;
		}
		else if(caracteristica.compareToIgnoreCase("tempo")==0)
		{
			llaveArbol = tempo;
		}
		else if(caracteristica.compareToIgnoreCase("acousticness")==0)
		{
			llaveArbol = acousticness;
		}
		else if(caracteristica.compareToIgnoreCase("mode")==0)
		{
			llaveArbol = mode;
		}
		else if (caracteristica.compareToIgnoreCase("energy")==0)
		{
			llaveArbol = energy;
		}
		else if (caracteristica.compareToIgnoreCase("key")==0)
		{
			llaveArbol = key;
		}
	}

	public Date darHora()
	{
		return horaCreacion;
	}
	public double daravgVader()
	{
		return avgVaderHashTag;
	}
	@Override
	public int compareTo(Reproduccion o) 
	{
		
		return this.idTrack.compareTo(o.idTrack) ;
	}
}

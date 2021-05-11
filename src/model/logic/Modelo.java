package model.logic;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.RedBlackTree;
import model.data_structures.TablaHashLinearProbing;
import model.data_structures.TablaHashSeparateChaining;

public class Modelo 
{

	
	private TablaHashSeparateChaining<String, String> generos;
	
	private ILista<Reproduccion> eventosDeEscucha;

	private TablaHashSeparateChaining<String,Double> hashTags;
	
	private TablaHashSeparateChaining<String,Double> sentimentValues;
	public Modelo()
	{
		eventosDeEscucha = new ArregloDinamico<>(10000);
		
		hashTags =new TablaHashSeparateChaining<>(1000,10);
		sentimentValues =new TablaHashSeparateChaining<>(1000,5); 
		generos = new TablaHashSeparateChaining<>(15,2);
		generos.put("reggae","60-90");
		generos.put("down-tempo", "70-100");
		generos.put("chill-out", "90-120");
		generos.put("hip-hop", "85-115");
		generos.put("jazz and funk", "120-125");
		generos.put("pop", "100-130");
		generos.put("r&B", "60-80");
		generos.put("rock","110-140");
		generos.put("metal", "100-160");
		try
		{
			cargarSentimentValue();
			cargarHashTags();
			cargarDatos();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	    //------------------
		//Carga de  Datos
		//------------------
	public void cargarSentimentValue()throws NumberFormatException, ParseException
	{
		try
		{
			Reader in = new FileReader("./data/sentiment_values.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record :records)
			{
				double avgVADER =0;
				String hashtag = record.get(0);
				if(!record.get(4).isEmpty())
				{
					avgVADER = Double.parseDouble(record.get(4));
				}
				
				sentimentValues.put(hashtag,avgVADER);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void cargarHashTags()throws NumberFormatException, ParseException
	{
		try
		{
			Reader in = new FileReader("./data/user_track_hashtag_timestamp-small.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record :records)
			{
				String userId = record.get(0);
				String trackId = record.get(1);
				String hashtag = record.get(2);
				String date = record.get(3);
				double avg =0;
				if(sentimentValues.contains(hashtag.toLowerCase()))
					avg =sentimentValues.get(hashtag.toLowerCase());
				hashTags.put(userId+trackId+date,avg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void cargarDatos() throws NumberFormatException, ParseException
	{
		Reproduccion reproduccion = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			Reader in = new FileReader("./data/context_content_features-small.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			int i = 0;
			for(CSVRecord record : records)
			{
				double instrumentalness = Double.parseDouble(record.get(0));
				double liveness = Double.parseDouble(record.get(1));
				double speechiness = Double.parseDouble(record.get(2));
				double danceability = Double.parseDouble(record.get(3));
				double valence = Double.parseDouble(record.get(4));
				double loudness = Double.parseDouble(record.get(5));
				double tempo = Double.parseDouble(record.get(6));
				double acousticness= Double.parseDouble(record.get(7));
				double energy = Double.parseDouble(record.get(8));
				double mode = Double.parseDouble(record.get(9));
				double key = Double.parseDouble(record.get(10));
				
				String id = record.get(18);
				String idArtist = record.get(11);
				String idTrack = record.get(13);

				String idUser = record.get(17);
				
				Date dateCreation =format.parse(record.get(14));
				
				SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
				String horaCreacion = hora.format(dateCreation);
				Date horaCr = hora.parse(horaCreacion);
				
				String llave = idUser+idTrack+record.get(14);
				double avg = 0;
				if(hashTags.contains(llave))
					avg = hashTags.get(llave);
				
				reproduccion = new Reproduccion(instrumentalness, liveness, speechiness, danceability, valence, loudness, tempo, acousticness, energy, mode, key, id, idArtist, idTrack, idUser, dateCreation, horaCr,avg );
				eventosDeEscucha.addLast(reproduccion);
				i++;

			}
			System.out.println("Termino la carga de datos!!");
			System.out.println("El número total de reproducciones es "+i);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("No se encontro el archivo para cargar los datos");
		}
	}
	public RedBlackTree<Double,ILista<Reproduccion>> crearArbolSegunCaracteristica(ILista<Reproduccion> eventos, String caracteristica)
	{
		RedBlackTree<Double,ILista<Reproduccion>> arbol = new RedBlackTree<>();
		for(int i = 1; i <= eventos.size(); i++)
		{
			Reproduccion reproduccion = eventos.getElement(i);
			reproduccion.setLLaveArbol(caracteristica);
			ILista<Reproduccion> valoresActuales = arbol.get(reproduccion.darLlaveArbol());
			if(valoresActuales!= null)
			{
				valoresActuales.addLast(reproduccion);
				arbol.put(reproduccion.darLlaveArbol(), valoresActuales);
			}
			else
			{
				valoresActuales = new ArregloDinamico<>(5);
				valoresActuales.addLast(reproduccion);
				arbol.put(reproduccion.darLlaveArbol(), valoresActuales);
			}
		}
		return arbol;
	}
	public RedBlackTree<Date,ILista<Reproduccion>> crearArbolSegunHora(ILista<ILista<Reproduccion>> eventos)
	{
		RedBlackTree<Date,ILista<Reproduccion>> arbol = new RedBlackTree<>();
		for(int j = 1; j <= eventos.size(); j++)
		{
				for(int i = 1; i<=eventos.getElement(j).size();i++)
				{
					Reproduccion reproduccion = eventos.getElement(j).getElement(i);
					ILista<Reproduccion> valoresActuales = arbol.get(reproduccion.darHora());
					if(valoresActuales!= null)
					{
						valoresActuales.addLast(reproduccion);
						arbol.put(reproduccion.darHora(), valoresActuales);
					}
					else
					{
						valoresActuales = new ArregloDinamico<>(5);
						valoresActuales.addLast(reproduccion);
						arbol.put(reproduccion.darHora(), valoresActuales);
				}
			}
		}
		return arbol;
	}
	
	public ILista<String> darGeneros()
	{
		return generos.keySet();
	}
	public void agregarGenero(String nombre, String rango)
	{
		generos.put(nombre, rango);
	}
    //------------------------------------	
	//Requerimientos
    //------------------------------------
	
	public String req1(String caracteristica, double min, double max )
	{
		String valores = null;
		RedBlackTree<Double, ILista<Reproduccion>> arbol = crearArbolSegunCaracteristica(eventosDeEscucha, caracteristica);
		ILista<ILista<Reproduccion>> valoresEnRango = arbol.valuesInRange(min, max);
		int numRep = 0;
		ILista<String> artistasUnicos = new ArregloDinamico<>(100);
		for(int i = 1; i <= valoresEnRango.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRango.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
				numRep++;
			
					if(!artistasUnicos.isPresent(actual.getElement(j).darArtistId()))
					{
						artistasUnicos.addLast(actual.getElement(j).darArtistId());
					}
				
			}
		}
		valores = numRep+"-"+artistasUnicos.size();
		return valores;
	}
	
	public ILista<Reproduccion> req2(double minEnergy, double maxEnergy, double minDanceability, double maxDanceability)
	{
		RedBlackTree<Double, ILista<Reproduccion>> arbolEnergy = crearArbolSegunCaracteristica(eventosDeEscucha,"energy");
		ILista<ILista<Reproduccion>> valoresEnRangoEnergy = arbolEnergy.valuesInRange(minEnergy,maxEnergy);
		ILista<Reproduccion> reproduccionesEnergy = new ArregloDinamico<>(arbolEnergy.size());
		for(int i = 1; i <= valoresEnRangoEnergy.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRangoEnergy.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
				reproduccionesEnergy.addLast(actual.getElement(j));
			}
		}
		RedBlackTree<Double, ILista<Reproduccion>> arbolDan = crearArbolSegunCaracteristica(reproduccionesEnergy,"danceability");
		ILista<ILista<Reproduccion>> valoresEnRangoDan = arbolDan.valuesInRange(minDanceability,maxDanceability);
		
		ILista<Reproduccion> pistasUnicas = new ArregloDinamico<>(arbolDan.size());
		for(int i = 1; i <= valoresEnRangoDan.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRangoDan.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
					if(!pistasUnicas.isPresent(actual.getElement(j)))
					{
						pistasUnicas.addLast(actual.getElement(j));
					}
			}
		}
		return pistasUnicas;	
	}
	
	public ILista<Reproduccion> req3(double minTempo, double maxTempo, double minInstrumentalness, double maxInstrumentalness)
	{
		RedBlackTree<Double, ILista<Reproduccion>> arbolTempo = crearArbolSegunCaracteristica(eventosDeEscucha,"tempo");
		ILista<ILista<Reproduccion>> valoresEnRangoEnergy = arbolTempo.valuesInRange(minTempo,maxTempo);
		ILista<Reproduccion> reproduccionesEnergy = new ArregloDinamico<>(arbolTempo.size());
		for(int i = 1; i <= valoresEnRangoEnergy.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRangoEnergy.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
				reproduccionesEnergy.addLast(actual.getElement(j));
			}
		}
		RedBlackTree<Double, ILista<Reproduccion>> arbolIns = crearArbolSegunCaracteristica(reproduccionesEnergy,"danceability");
		ILista<ILista<Reproduccion>> valoresEnRangoDan = arbolIns.valuesInRange(minInstrumentalness,maxInstrumentalness);
		
		ILista<Reproduccion> pistasUnicas = new ArregloDinamico<>(arbolIns.size());
		for(int i = 1; i <= valoresEnRangoDan.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRangoDan.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
					if(!pistasUnicas.isPresent(actual.getElement(j)))
					{
						pistasUnicas.addLast(actual.getElement(j));
					}
			}
		}
		return pistasUnicas;	
	}
	public ILista<ILista<Reproduccion>> req4(ILista<String> generos)
	{
		int totalReproducciones = 0;
		ILista<ILista<Reproduccion>> reproduccionesGeneros = new ArregloDinamico<>(generos.size());
		for(int i = 1; i <= generos.size();i++)
		{
			reproduccionesGeneros.addLast(new ArregloDinamico<>(100));
		}
		for(int i = 1; i<= generos.size();i++)
		{
			
			RedBlackTree<Double, ILista<Reproduccion>> arbolTempo = crearArbolSegunCaracteristica(eventosDeEscucha,"tempo");
			String[] rango = this.generos.get(generos.getElement(i)).split("-");
			
			ILista<ILista<Reproduccion>> valoresEnTempoGenero= arbolTempo.valuesInRange(Double.parseDouble(rango[0]),Double.parseDouble(rango[1]));
			
			for(int j = 1; j<= valoresEnTempoGenero.size();j++)
			{
				ILista<Reproduccion> actual = valoresEnTempoGenero.getElement(j);
				for(int k= 1; k<= actual.size();k++)
				{
					reproduccionesGeneros.getElement(i).addLast(actual.getElement(k));
					totalReproducciones++;
				}
			}
		}
		System.out.println("Total de Reproducciones: "+ totalReproducciones);
		return reproduccionesGeneros;
	}
	
	public void req5(Date horaMax, Date horaMin)
	{
		ILista<ILista<Reproduccion>> masEscuchado = null;
		String generoMasEscuchado = null;
		int max = 0;
		RedBlackTree<Double, ILista<Reproduccion>> arbolTempo = crearArbolSegunCaracteristica(eventosDeEscucha,"tempo");
		ILista<String> generos = this.generos.keySet();
		for(int i =1;i<= generos.size();i++)
		{
			String[] rango = this.generos.get(generos.getElement(i)).split("-");
			ILista<ILista<Reproduccion>> valoresEnTempoGenero= arbolTempo.valuesInRange(Double.parseDouble(rango[0]),Double.parseDouble(rango[1]));
			RedBlackTree<Date, ILista<Reproduccion>> arbolHora = crearArbolSegunHora(valoresEnTempoGenero);
			ILista<ILista<Reproduccion>> valoresEnHora = arbolHora.valuesInRange(horaMin, horaMax);
			int c = 0;
			for(int j = 1; j<= valoresEnHora.size();j++)
			{
				c+= valoresEnHora.getElement(j).size();
			}
			
			if(c>max)
			{
				masEscuchado = valoresEnHora;
				generoMasEscuchado = generos.getElement(i);
				max = c;
			}	
		}
		System.out.println(generoMasEscuchado);
		System.out.println(max);
		ILista<Reproduccion> reproduccionesMasEscuchadas = new ArregloDinamico<>(max);
		for(int i = 1; i <= masEscuchado.size();i++)
		{
			for(int j = 1; j <=masEscuchado.getElement(i).size();j++)
			{
					reproduccionesMasEscuchadas.addLast(masEscuchado.getElement(i).getElement(j));
			}
		}
		
	}
}

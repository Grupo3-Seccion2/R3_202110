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

<<<<<<< HEAD

	private TablaHashLinearProbing<String, String> generos;

=======
	
	private TablaHashLinearProbing<String, String> generos;
	
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
	private ILista<Reproduccion> eventosDeEscucha;

	public Modelo()
	{
		eventosDeEscucha = new ArregloDinamico<>(10000);
<<<<<<< HEAD

=======
	
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
		generos = new TablaHashLinearProbing<>(15,0.5);
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
			cargarDatos();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
<<<<<<< HEAD

	//------------------
	//Carga de  Datos
	//------------------
=======
	
	    //------------------
		//Carga de  Datos
		//------------------
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
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

				reproduccion = new Reproduccion(instrumentalness, liveness, speechiness, danceability, valence, loudness, tempo, acousticness, energy, mode, key, id, idArtist, idTrack, idUser, dateCreation);
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
<<<<<<< HEAD
	//------------------------------------	
	//Requerimientos
	//------------------------------------

=======
    //------------------------------------	
	//Requerimientos
    //------------------------------------
	
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
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
<<<<<<< HEAD

				if(!artistasUnicos.isPresent(actual.getElement(j).darArtistId()))
				{
					artistasUnicos.addLast(actual.getElement(j).darArtistId());
				}

=======
			
					if(!artistasUnicos.isPresent(actual.getElement(j).darArtistId()))
					{
						artistasUnicos.addLast(actual.getElement(j).darArtistId());
					}
				
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
			}
		}
		valores = numRep+"-"+artistasUnicos.size();
		return valores;
	}
<<<<<<< HEAD

=======
	
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
	public ILista<String> req2(double minEnergy, double maxEnergy, double minDanceability, double maxDanceability)
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
<<<<<<< HEAD

=======
		
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
		ILista<String> pistasUnicas = new ArregloDinamico<>(arbolDan.size());
		for(int i = 1; i <= valoresEnRangoDan.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRangoDan.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
<<<<<<< HEAD
				if(!pistasUnicas.isPresent(actual.getElement(j).darTrackId()))
				{
					pistasUnicas.addLast(actual.getElement(j).darTrackId());
				}
=======
					if(!pistasUnicas.isPresent(actual.getElement(j).darTrackId()))
					{
						pistasUnicas.addLast(actual.getElement(j).darTrackId());
					}
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
			}
		}
		return pistasUnicas;	
	}
<<<<<<< HEAD

=======
	
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
	public ILista<String> req3(double minTempo, double maxTempo, double minInstrumentalness, double maxInstrumentalness)
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
<<<<<<< HEAD

=======
		
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
		ILista<String> pistasUnicas = new ArregloDinamico<>(arbolIns.size());
		for(int i = 1; i <= valoresEnRangoDan.size();i++)
		{
			ILista<Reproduccion> actual = valoresEnRangoDan.getElement(i);
			for(int j = 1; j<= actual.size();j++)
			{
<<<<<<< HEAD
				if(!pistasUnicas.isPresent(actual.getElement(j).darTrackId()))
				{
					pistasUnicas.addLast(actual.getElement(j).darTrackId());
				}
=======
					if(!pistasUnicas.isPresent(actual.getElement(j).darTrackId()))
					{
						pistasUnicas.addLast(actual.getElement(j).darTrackId());
					}
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
			}
		}
		return pistasUnicas;	
	}
<<<<<<< HEAD

=======
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
	public void req4(ILista<String> generos)
	{
		ILista<Reproduccion> totalReproducciones = new ArregloDinamico<>(100);
		for(int i = 1; i<= generos.size();i++)
		{
			RedBlackTree<Double, ILista<Reproduccion>> arbolTempo = crearArbolSegunCaracteristica(eventosDeEscucha,"tempo");
			String[] rango = this.generos.get(generos.getElement(i)).split("-");
<<<<<<< HEAD

			ILista<ILista<Reproduccion>> valoresEnTempoGenero= arbolTempo.valuesInRange(Double.parseDouble(rango[0]),Double.parseDouble(rango[1]));

=======
			
			ILista<ILista<Reproduccion>> valoresEnTempoGenero= arbolTempo.valuesInRange(Double.parseDouble(rango[0]),Double.parseDouble(rango[1]));
			
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
			for(int j = 1; j<= valoresEnTempoGenero.size();j++)
			{
				ILista<Reproduccion> actual = valoresEnTempoGenero.getElement(j);
				for(int k= 1; k<= actual.size();k++)
				{
					totalReproducciones.addLast(actual.getElement(k));
				}
			}
		}
		System.out.println(totalReproducciones.size());
	}
<<<<<<< HEAD

	public void req5(Date min, Date max)
	{

	}
=======
>>>>>>> 0d91d5ab1d496082670ff8973dd4363771d02749
}

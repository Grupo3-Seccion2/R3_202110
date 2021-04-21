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

public class Modelo 
{
	private RedBlackTree<Double, ILista<Reproduccion>> arbol;

	public Modelo()
	{
		arbol = new RedBlackTree<>();
	}
	
	    //------------------
		//Carga de  Datos
		//------------------
	public void cargarDatosArbol() throws NumberFormatException, ParseException
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
				i++;

			}
			System.out.println("Termino la carga de datos!!");
			System.out.println("El número total de reproducciones es "+i);
			System.out.println("El número de llaves presentes en el árbol RBT es "+arbol.size());
			System.out.println("La altura del Arbol es "+arbol.height());
			double min = arbol.min();
			double max = arbol.max();
			System.out.println("La llave menor es "+ min+ ". Cuenta con "+arbol.get(min).size()+" reproducciones");
			System.out.println("La llave mayor es "+ max+ ". Cuenta con "+arbol.get(max).size()+" reproducciones");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("No se encontro el archivo para cargar los datos");
		}
	}
}

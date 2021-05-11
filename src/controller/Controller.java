package controller;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.Reproduccion;
import view.View;

public class Controller {
	/**
	 *  Instancia del Modelo
	 */
	private Modelo modelo;
	
	/**
	 *  Instancia de la Vista
	 */
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 */
	public Controller ()
	{
		modelo = new Modelo();
		view = new View();
		
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		view.printMenu();
		while( !fin )
			{
				int option = lector.nextInt();
				switch(option)
				{
					case 1:
						view.printMessage("Para realizar el requerimiento es necesario ingresar los siguientes datos: \n -Nombre de la caracteristica: ");
						String carac = lector.next();
						view.printMessage("-El valor maximo de la caracteristica (con punto como separador decimal): ");
						Double max = lector.nextDouble();
						view.printMessage("-El valor minimo de la caracteristica (con punto como separador decimal): ");
						Double min = lector.nextDouble();
						String[] valores =modelo.req1(carac, min, max).split("-");
						view.printMessage(carac+ "entre "+min+ " y "+max);
						view.printMessage("Numero de reproducciones en el rango: "+valores[0]);
						view.printMessage("Numero de artistas unicos en el rango: "+valores[1]);
						break;
					case 2:
						view.printMessage("Para realizar el requerimiento es necesario ingresar los siguientes datos (Recordando que se debe poner punto como separador decimal): \n -Valor maximo de la caracteristica Energy:  ");
						Double maxEn = lector.nextDouble();
						view.printMessage("-Valor minimo de la caracteristica Energy:  ");
						Double minEn = lector.nextDouble();
						view.printMessage("-Valor maximo de la caracteristica Danceability:  ");
						Double maxDan = lector.nextDouble();
						view.printMessage("-Valor minimo de la caracteristica Energy: Danceability: ");
						Double minDan = lector.nextDouble();
						ILista<Reproduccion> pistas = modelo.req2(minEn, maxEn, minDan, maxDan);
						view.printMessage("Energy entre "+minEn+" y "+maxEn);
						view.printMessage("Danceability entre "+minDan+" y "+maxDan);
						view.printMessage("Numero pistas unicas: "+ pistas.size());
						view.printMessage("-------- Id Track Unico---------");
						for(int i = 1; i<= 10;i++)
						{
							String mensaje = "Track "+i+" : "+pistas.getElement(i).darTrackId()+" con un Energy de "+pistas.getElement(i).darEnergy() + " y un Danceability de "+ pistas.getElement(i).darDanceability();
							view.printMessage(mensaje);
						}
						break;
						
					case 3: 
						view.printMessage("Para realizar el requerimiento es necesario ingresar los siguientes datos (Recordando que se debe poner punto como separador decimal): \n -Valor maximo de la caracteristica Tempo:  ");
						Double maxTem = lector.nextDouble();
						view.printMessage("-Valor minimo de la caracteristica Tempo:  ");
						Double minTem = lector.nextDouble();
						view.printMessage("-Valor maximo de la caracteristica Instrumentalness:  ");
						Double maxIns = lector.nextDouble();
						view.printMessage("-Valor minimo de la caracteristica Instrumentalness:  ");
						Double minIns = lector.nextDouble();
						ILista<Reproduccion> pistas3 = modelo.req3(minTem, maxTem, minIns, maxIns);
						view.printMessage("Tempo entre "+minTem+" y "+maxTem);
						view.printMessage("Instrumentalness entre "+minIns+" y "+maxIns);
						view.printMessage("Numero pistas unicas: "+ pistas3.size());
						view.printMessage("-------- Id Track Unico---------");
						for(int i = 1; i<=5;i++)
						{
							String mensaje = "Track "+i+" : "+pistas3.getElement(i).darTrackId()+" con un Tempo de "+pistas3.getElement(i).darTempo() + " y un Instrumentalness de "+ pistas3.getElement(i).darInstrumentalness();
							view.printMessage(mensaje);
						}
						
						break;
					
					case 4:
						view.printMessage("-------La lista actual de generos es la siguiente:");
						ILista<String> generosActuales = modelo.darGeneros();
						for(int i = 1; i <= generosActuales.size();i++)
						{
							view.printMessage("-" +generosActuales.getElement(i));
						}
						view.printMessage("Para realizar el requerimiento es necesario ingresar los siguientes datos. Como se indica a continuacion: \n -Ingrese el nombre del genero que desea y presione enter para ingresar otro");
						view.printMessage("- Si desea ingresar uno nuevo, ingrese 'Nuevo' y presione enter.");
						view.printMessage("- Ingrese 'Termino' al momento de terminar");
						ILista<String> generos = new ArregloDinamico<>(3);
						boolean termino = false;
						while(!termino)
						{
							String genero = lector.next();
							if(genero.contains("Termino"))
							{
								termino = true;
							}
							else if(genero.contains("Nuevo"))
							{
								view.printMessage("Ingrese en nuevo nombre del genero: ");
								String nombre = lector.next();
								view.printMessage("Ingrese el valor minimo del Tempo del genero: ");
								double minTempo= lector.nextDouble();
								view.printMessage("Ingrese el valor maximo del Tempo del genero: ");
								double maxTempo= lector.nextDouble();
								modelo.agregarGenero(nombre, minTempo+"-"+maxTempo);
								generos.addLast(nombre);
							}	
							else
							{
								generos.addLast(genero);
							}
						}
						ILista<ILista<Reproduccion>> rep = modelo.req4(generos);
						for( int i = 1; i<= rep.size();i++)
						{
							view.printMessage("===================="+generos.getElement(i)+"====================");
							view.printMessage(generos.getElement(i)+" con un total de reproducciones de: "+rep.getElement(i).size());
							view.printMessage("------------------"+"Diferenetes artistas para "+generos.getElement(i)+"------------------");
							for(int j = 1; j <= 10; j++)
							{
								view.printMessage("Artista "+j+": "+rep.getElement(i).getElement(j).darArtistId());
							}
						}
						break;
						
					case 5: 
						SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
						String minHora = lector.next();
						String maxHora = lector.next();
						try
						{
							modelo.req5(formato.parse(maxHora), formato.parse(minHora));
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
						break;
					case 6:
						view.printMessage("--------- \n Hasta pronto !! \n---------"); 
						lector.close();
						fin = true;
						break;
				
				}
			}
		
	}
	
}

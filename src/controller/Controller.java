package controller;

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
						String carac = lector.next();
						Double max = lector.nextDouble();
						Double min = lector.nextDouble();
						String[] valores =modelo.req1(carac, min, max).split("-");
						view.printMessage("Numero de reproducciones en el rango: "+valores[0]);
						view.printMessage("Numero de artistas unicos en el rango: "+valores[1]);
						break;
					case 2:
						Double maxEn = lector.nextDouble();
						Double minEn = lector.nextDouble();
						Double maxDan = lector.nextDouble();
						Double minDan = lector.nextDouble();
						ILista<String> pistas = modelo.req2(minEn, maxEn, minDan, maxDan);
						view.printMessage("Numero pistas unicas: "+ pistas.size());
						break;
						
					case 3: 
						Double maxTem = lector.nextDouble();
						Double minTem = lector.nextDouble();
						Double maxIns = lector.nextDouble();
						Double minIns = lector.nextDouble();
						ILista<String> pistas3 = modelo.req3(minTem, maxTem, minIns, maxIns);
						view.printMessage("Numero pistas unicas: "+ pistas3.size());
						break;
					
					case 4:
						String[] generos = new String[10];
						boolean termino = false;
						while(!termino)
						{
							String genero = lector.next();
							if(genero.contains("termino"))
								termino = true;
							else
								for(int i = 0; i < generos.length; i++)
								{
									generos[i] = genero;
								}
						}
						modelo.req4(generos);
						break;
						
					case 5: 
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

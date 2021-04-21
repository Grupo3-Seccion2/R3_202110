package controller;

import java.util.Scanner;

import model.data_structures.ILista;
import model.logic.Modelo;
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
						try
						{
							modelo.cargarDatosArbol();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						break;
							
					case 2:
						view.printMessage("--------- \n Hasta pronto !! \n---------"); 
						lector.close();
						fin = true;
						break;
				
				}
			}
		
	}
	
}

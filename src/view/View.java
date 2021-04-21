package view;

import model.data_structures.ILista;
import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			
			System.out.println("\n1. Cargar los datos");
			System.out.println("2. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		
		 /**
	     * Metodo que imprime en la consola un mensaje deseado ingresado por el ususario
	     * @param mensaje. Cadena de caracteres que se quiere imprimir 
	     */
		public void printMessage(String mensaje) 
		{

			System.out.println(mensaje);
		}		

		
}


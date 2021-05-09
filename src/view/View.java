package view;

import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.Reproduccion;

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
			
			System.out.println("\n1. Requerimiento 1");
			System.out.println("2. Requerimiento 2");
			System.out.println("3. Requerimiento 3");
			System.out.println("4. Requerimiento 4");
			System.out.println("5. Requerimiento 5");
			System.out.println("6. Exit");
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
		public void printReq1()
		{
			
		}
		public void printReq2(ILista<Reproduccion> pistas, double maxEnergy, double minEnergy,double maxDanceability,double minDanceability)
		{
			
		}
		
		public void printReq3(ILista<Reproduccion> pistas, double maxTempo, double minTempo,double maxInstru,double minInstru)
		{
			
		}
		public void printReq4(ILista<ILista<Reproduccion>> reproducciones)
		{
			
		}
		
}


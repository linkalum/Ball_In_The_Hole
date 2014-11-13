package game;

import paneles.vista.IPantalla;
import paneles.vista.Pantalla;

/**
 * This class defines the start of the game. In this class is developed the
 * main() method which start the game and lets us play. Because the library
 * paneles-vista.jar is in spanish some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Start {

	public static void main(String[] args) {
		Game actualGame = new Game();

		IPantalla screen = new Pantalla(actualGame); // Pantalla = screen

		actualGame.setPantalla(screen);

	}
}

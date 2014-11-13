package scene;

import java.util.LinkedList;
import java.util.Random;

import geometry.Point;
import geometry.Circle;
import geometry.Rectangle;

public class Programa2 {

	// Método auxiliar usado para comprobar el método generarPaneles()
	public static void mostrar() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out
				.println("***************Añadir paneles al tablero*******************");
		int numPaneles = 10;
		int celdasX = 4;
		int celdasY = 4;
		int anchura = 200;
		int altura = 200;
		LinkedList<Panel> coleccionPaneles = new LinkedList<Panel>();

		System.out.println("Antes del for cuanto es el tamaño de la lista: "
				+ coleccionPaneles.size());
		int contRandom = 0;

		boolean[] arrayCasillas = new boolean[celdasX * celdasY];

		System.out.println("El array se inicializa a: ");
		for (int j = 0; j < arrayCasillas.length; j++) {
			System.out.println(arrayCasillas[j]);
		}

		for (int i = 0; i < numPaneles; i++) {

			int cont = coleccionPaneles.size();

			System.out.println("El contador cont es: " + cont);

			while (cont == coleccionPaneles.size()) {
				// Construir punto aleatorio

				Random aleatorio = new Random();

				int casilla = aleatorio.nextInt(celdasX * celdasY);

				contRandom++;
				System.out.println("La casilla es: " + casilla);

				int x = ((casilla % celdasX) * (anchura / celdasX));
				System.out.println("X: " + x);
				int y = ((casilla / celdasY) * (altura / celdasY));
				System.out.println("Y: " + y);

				Point puntoAleatorio = new Point(x, y);
				System.out.println(puntoAleatorio.toString());

				// Analizar si el punto sirve
				if (!arrayCasillas[casilla]) {
					Panel nuevoPanel = new Panel(new Rectangle(puntoAleatorio,
							(anchura / celdasX), (altura / celdasY)));
					coleccionPaneles.add(nuevoPanel);
					arrayCasillas[casilla] = true;
					System.out.println("Añadido a la lista");
				}
			}
		}
		for (Panel panelActual : coleccionPaneles) {
			System.out.println(panelActual.toString());
		}

		System.out.println();
		System.out.println();
		System.out.println("Veces ejecutadas el random: " + contRandom);
	}

	public static void main(String[] args) {
		Panel panel = new Panel(new Rectangle(new Point(), 4), 2);

		Panel copiaPanel = panel.clone();

		FragilePanel fragil = new FragilePanel(new Rectangle(new Point(2, 1),
				4, 1), 3, 5);
		GrowingPanel creciente = new GrowingPanel(new Rectangle(
				new Point(3, 5), 3), 2);

		GrowingPanel copiaCreciente = creciente.clone();
		FragilePanel copiaFragil = creciente.clone();

		PanelPlus plus = new PanelPlus(new Rectangle(new Point(5, 9), 9), 4, 2);
		PanelMinus menos = new PanelMinus(new Rectangle(new Point(4, 8), 2), 5);

		PanelPlus copiaPlus = plus.clone();
		PanelMinus copiaMenos = menos.clone();

		System.out.println("****************Paneles*******************");
		System.out.println(panel.equals(copiaPanel));
		System.out.println(panel.hashCode() == copiaPanel.hashCode());
		System.out.println("*************************************************");
		System.out.println(fragil.equals(copiaFragil));
		System.out.println(fragil.hashCode() == copiaFragil.hashCode());
		System.out.println("*************************************************");
		System.out.println(creciente.equals(copiaCreciente));
		System.out.println(creciente.hashCode() == copiaCreciente.hashCode());
		System.out.println("*************************************************");
		System.out.println(plus.equals(copiaPlus));
		System.out.println(plus.hashCode() == copiaPlus.hashCode());
		System.out.println("*************************************************");
		System.out.println(menos.equals(copiaMenos));
		System.out.println(menos.hashCode() == copiaMenos.hashCode());

		System.out.println("*************************************************");
		System.out.println("*************************************************");
		System.out.println(fragil.equals(panel));
		System.out.println(copiaCreciente.equals(copiaFragil));

		System.out.println("****************Rectangulos******************");
		Circle circulo = new Circle(new Point(12, 18), 3);
		Circle copiaCirculo = circulo.clone();

		Point punto = new Point();

		Rectangle rectangulo = new Rectangle(punto, 4);

		System.out.println(rectangulo.getVertexDL().equals(punto));
		System.out.println(rectangulo.equals(panel.getRegion().getVertexDL()));

		System.out.println("***************Circulos*******************");
		System.out.println(circulo.equals(copiaCirculo));
		copiaCirculo.move(2, 3);
		System.out.println(circulo.equals(copiaCirculo));

		// mostrar();
	}
}

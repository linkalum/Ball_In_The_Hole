package geometry;

/* THIS CLASS IS A MAIN PROGRAM FOR TESTING ALL THE GEOMETRY CLASSES.*/

public class Programa {

	public static void main(String[] args) {
		Point centro = new Point();
		Point prueba = new Point(-3, -2);

		System.out.println("Punto en coordenadas negativas: " + prueba.getX()
				+ ", " + prueba.getY());

		Circle circulo = new Circle(centro, 5);
		Circle copia = circulo.clone();

		Rectangle rect1 = new Rectangle(centro, 6, 3);
		Rectangle rect2 = new Rectangle(centro, 2);

		System.out.println(circulo.getCenter().getX() + " "
				+ circulo.getCenter().getY());
		System.out.println("La copia es igual: " + copia.equals(circulo));

		circulo.move(0, 0);
		if (circulo.getCenter() == copia.getCenter())
			System.out.println("verdad");

		circulo.move(2, 2);

		System.out.println(circulo.getCenter().getX() + " "
				+ circulo.getCenter().getY());

		System.out.println(rect1.getVertexDL().getX() + " "
				+ rect1.getVertexDL().getY());
		System.out.println(rect2.getVertexDR().getX() + " "
				+ rect2.getVertexDR().getY());
		System.out.println(rect1.getVertexUR().getX() + " "
				+ rect1.getVertexUR().getY());

		System.out.println(copia.equals(circulo));

		System.out
				.println("*******************Prueba métodos clase figura*********************");

		System.out.println(rect1.getLowestX());
		System.out.println(rect1.getHighestY());
		System.out.println(rect2.getHighestX());
		System.out.println(rect2.getLowestY());
		System.out.println(rect1.isInside(new Point(4, 2)));
		System.out.println(rect1.isInside(circulo));
		System.out.println("*************************");
		for (Point punto : rect2.getFramingPolygon()) {
			System.out.println(punto);
		}
		System.out.println(rect1.isOverlapped(rect2));
	}

}

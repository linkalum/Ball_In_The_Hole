package scene;

import geometry.Point;
import geometry.Rectangle;

public class Programa {

	public static void main(String[] args) {
		Panel panel = new Panel(new Rectangle(new Point(), 1));

		FragilePanel fragil = new FragilePanel(
				new Rectangle(new Point(5, 7), 2), 2, 4);
		PanelPlus plus = new PanelPlus(new Rectangle(new Point(10, 5), 4, 2),
				3, 2);
		PanelMinus menos = new PanelMinus(new Rectangle(new Point(7, 12), 6), 4);
		GrowingPanel creciente = new GrowingPanel(new Rectangle(
				new Point(2, 2), 7), 2);

		System.out.println("Panel: " + panel.receiveHit());
		System.out.println("PanelFragil: " + fragil.receiveHit());
		System.out.println("PanelPlus: " + plus.receiveHit());
		System.out.println("PanelMenos: " + menos.receiveHit());
		System.out.println("PanelCreciente: " + creciente.receiveHit());

		System.out.println("Panel: " + panel.receiveHit());
		System.out.println("PanelFragil: " + fragil.receiveHit());
		System.out.println("PanelPlus: " + plus.receiveHit());
		System.out.println("PanelMenos: " + menos.receiveHit());
		System.out.println("PanelCreciente: " + creciente.receiveHit());

		System.out.println("Panel: " + panel);

		System.out.println(System.currentTimeMillis());

	}

}
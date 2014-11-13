package scene;

import geometry.Rectangle;

/**
 * This class defines the panel minus. In this class we implemented all those
 * methods that can be applied on any object of PanelMinus type, which inherit
 * from Panel class. Because the library paneles-vista.jar is in spanish some of
 * the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class PanelMinus extends Panel {

	/**
	 * Constructor of a panel minus. This constructor creates a panel minus with
	 * the parameters that we pass.
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @param points
	 *            The points to add when the panel is hit.
	 */
	public PanelMinus(Rectangle region, int points) {
		super(region, points);
	}

	/**
	 * Override of receiveHit() method. In this override we check if the hit is
	 * accepted and manage the points decreasing.
	 * 
	 * @see Panel#receiveHit()
	 */
	@Override
	public int receiveHit() {
		if ((super.receiveHit() != 0) && (this.getPoints() > 0)) {
			this.setPoints(this.getPoints() - 1);
			return this.getPoints();
		}
		return 0;
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + super.toString();
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public PanelMinus clone() {
		return (PanelMinus) super.clone();
	}

	/**
	 * Implementation of getImagen() method from Dibujable interface [ getImagen
	 * = getImage ].
	 */
	@Override
	public String getImagen() {
		if (this.isVisible())
			return "panel-minus";
		return "";
	}
}

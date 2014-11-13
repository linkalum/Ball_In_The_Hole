package scene;

import geometry.Rectangle;

/**
 * This class defines a growing panel. In this class we implemented all those
 * methods that can be applied on any object of GrowingPanel type, which inherit
 * from FragilePanel class. Because the library paneles-vista.jar is in spanish
 * some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class GrowingPanel extends FragilePanel {

	/**
	 * Constructor of a growing panel. This constructor creates a growing panel
	 * with the parameters that we pass, and set a default value (0) as the
	 * points to add.
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @param supportedHits
	 *            The number of hits the panel can support before sink.
	 * @see FragilePanel#FragilePanel(Rectangle, int, int)
	 */
	public GrowingPanel(Rectangle region, int supportedHits) {
		super(region, 0, supportedHits);
	}

	/**
	 * Override of receiveHit() method. In this override we check the status of
	 * the growing panel and manage the change of size.
	 * 
	 * @see FragilePanel#receiveHit()
	 */
	@Override
	public int receiveHit() {
		if (this.isAccepted() && !this.isSunk()) {
			super.receiveHit();
			setRegion(new Rectangle(getRegion().getVertexDL(), getRegion()
					.getSideX() * 2, getRegion().getSideY() * 2));
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
	public GrowingPanel clone() {
		return (GrowingPanel) super.clone();
	}

	/**
	 * Implementation of getImagen() method from Dibujable interface [ getImagen
	 * = getImage ].
	 */
	@Override
	public String getImagen() {
		if (this.isSunk())
			return "growing-panel-sunk";
		else if (this.isVisible())
			return "growing-panel";
		return "";
	}
}
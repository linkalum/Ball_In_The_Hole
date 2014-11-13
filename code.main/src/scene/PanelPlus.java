package scene;

import geometry.Rectangle;

/**
 * This class defines the panel plus. In this class we implemented all those
 * methods that can be applied on any object of PanelPlus type, which inherit
 * from Panel class. Because the library paneles-vista.jar is in spanish some of
 * the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class PanelPlus extends Panel {

	private int pointsIncrease;

	/**
	 * Constructor of a panel plus. This constructor creates a panel plus with
	 * the parameters that we pass.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The points increase that we pass must be bigger or
	 * equal 0 (positive value).</li>
	 * </ul>
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @param points
	 *            The points to add when the panel is hit.
	 * @param pointsIncrease
	 *            Points increase to the points to add.
	 */
	public PanelPlus(Rectangle region, int points, int pointsIncrease) {
		super(region, points);
		if (pointsIncrease < 0)
			throw new IllegalArgumentException(
					"The points increasing must be bigger or equal 0.");
		this.pointsIncrease = pointsIncrease;
	}

	/**
	 * Override of receiveHit() method. In this override we check if the hit is
	 * accepted and manage the points increasing.
	 * 
	 * @see Panel#receiveHit()
	 */
	@Override
	public int receiveHit() {
		if (super.receiveHit() != 0) {
			this.setPoints(this.getPoints() + pointsIncrease);
			return this.getPoints();
		}
		return 0;
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + super.toString() + "[pointsIncrease="
				+ pointsIncrease + "]";
	}

	/**
	 * Override of hashCode() method from Object class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + pointsIncrease;
		return result;
	}

	/**
	 * Override of equals() method from Object class.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelPlus other = (PanelPlus) obj;
		return pointsIncrease == other.pointsIncrease;
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public PanelPlus clone() {
		return (PanelPlus) super.clone();
	}

	/**
	 * Implementation of getImagen() method from Dibujable interface [ getImagen
	 * = getImage ].
	 */
	@Override
	public String getImagen() {
		if (this.isVisible())
			return "panel-plus";
		return "";
	}
}
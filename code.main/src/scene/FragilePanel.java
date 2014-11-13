package scene;

import geometry.Rectangle;

/**
 * This class defines a fragile panel. In this class we implemented all those
 * methods that can be applied on any object of FragilePanel type, which inherit
 * from Panel class. Because the library paneles-vista.jar is in spanish some of
 * the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class FragilePanel extends Panel {

	private int supportedHits;
	private boolean sunk;

	/**
	 * Constructor of a fragile panel. This constructor creates a fragile panel
	 * with the parameters that we pass.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The supported hits must be bigger or equal 0.</li>
	 * </ul>
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @param points
	 *            The points added by the panel when it is hit.
	 * @param supportedHits
	 *            The number of hits the panel can support before sink.
	 * @see Panel#Panel(Rectangle, int)
	 */
	public FragilePanel(Rectangle region, int points, int supportedHits) {
		super(region, points);
		if (supportedHits < 0)
			throw new IllegalArgumentException(
					"The supported hits must be bigger or equal 0.");
		this.supportedHits = supportedHits;
		this.sunk = false;
	}

	/**
	 * Constructor of a fragile panel. This constructor creates a fragile panel
	 * with the parameters that we pass and set a default value (10) as
	 * supported hits.
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @param points
	 *            The points added by the panel when is hit.
	 * @see #FragilePanel(Rectangle, int, int)
	 */
	public FragilePanel(Rectangle region, int points) {
		this(region, points, 10);
	}

	/**
	 * Constructor of a fragile panel. This constructor creates a fragile panel
	 * in the region that we pass as a parameter, set a default value (0) as the
	 * points to add and set a default value (10) as the supported hits.
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @see #FragilePanel(Rectangle, int, int)
	 */
	public FragilePanel(Rectangle region) {
		this(region, 0, 10);
	}

	/**
	 * Method for get the supported hits.
	 * 
	 * @return The number of hits the panel can support before sink.
	 */
	public int getSupportedHits() {
		return supportedHits;
	}

	/**
	 * Method for check if the panel is sunk.
	 * 
	 * @return true if the panel is sunk, false if it is not.
	 */
	public boolean isSunk() {
		return sunk;
	}

	/**
	 * Override of receiveHit() method. In this override we check the status of
	 * the FragilePanel and manage the change of the status (sunk).
	 * 
	 * @see Panel#receiveHit()
	 */
	@Override
	public int receiveHit() {
		if ((this.getHitsNumber() <= this.supportedHits) && (!this.isSunk())) {
			int valor = super.receiveHit();
			if (this.getHitsNumber() > this.supportedHits)
				this.sunk = true;
			return valor;
		} else {
			return 0;
		}
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + super.toString() + "[supportedHits="
				+ supportedHits + ", sunk=" + sunk + "]";
	}

	/**
	 * Override of hashCode() method from Object class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + supportedHits;
		result = prime * result + (sunk ? 1231 : 1237);
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
		FragilePanel other = (FragilePanel) obj;
		return (supportedHits == other.supportedHits) && (sunk == other.sunk);
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public FragilePanel clone() {
		return (FragilePanel) super.clone();
	}

	/**
	 * Implementation of getImagen() method from Dibujable interface [ getImagen
	 * = getImage ].
	 */
	@Override
	public String getImagen() {
		// If the panel is sunk we want to show it always.
		if (this.isSunk())
			return "fragile-panel-sunk";
		else if (this.isVisible() && (this.getHitsNumber() == 0))
			return "fragile-panel-start";
		else if (this.isVisible())
			return "fragile-panel";
		return "";
	}
}
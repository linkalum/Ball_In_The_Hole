package scene;

import geometry.Rectangle;
import paneles.vista.Dibujable;
import paneles.vista.IRegion;

/**
 * This class defines the basic panel of the game. In this class we implemented
 * all those methods that can be applied on any object of Panel type. Because
 * the library paneles-vista.jar is in spanish some of the methods are in
 * spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Panel implements Cloneable, Dibujable {

	private final static int TIME = 1000;

	private Rectangle region;
	private int points;
	private boolean visible;
	private int hitsNumber;

	private long lastHit; // Only intern use.

	/**
	 * Constructor of a panel. This constructor creates a panel with the
	 * parameters that we pass.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The amount of points that we pass must be bigger or
	 * equal 0 (positive value).</li>
	 * </ul>
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 * @param points
	 *            The points to add when the panel is hit.
	 */
	public Panel(Rectangle region, int points) {
		if (points < 0)
			throw new IllegalArgumentException(
					"The amount of points must be positive.");
		this.region = region;
		this.points = points;
		this.visible = true;
		this.lastHit = 0;
	}

	/**
	 * Constructor of a panel. This constructor creates a panel with the region
	 * we pass as a parameter and with a default points value (0).
	 * 
	 * @param region
	 *            The region occupied by the panel.
	 */
	public Panel(Rectangle region) {
		this(region, 0);
	}

	/**
	 * Method fot get the region.
	 * 
	 * @return The region occupied by the panel.
	 */
	public Rectangle getRegion() {
		return new Rectangle(region.getVertexDL(), region.getSideX(),
				region.getSideY());
	}

	/**
	 * Method for set the region.
	 * 
	 * @param region
	 *            New region assigned to the panel.
	 */
	public void setRegion(Rectangle region) {
		this.region = region;
	}

	/**
	 * Method for get the points.
	 * 
	 * @return The points added by the panel.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Method for set the points.
	 * 
	 * @param points
	 *            The new value of points added by the panel.
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Method for get the visibility.
	 * 
	 * @return true if it is visible, false if it is not.
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Method for get the received hits.
	 * 
	 * @return The hits received by the panel.
	 */
	public int getHitsNumber() {
		return hitsNumber;
	}

	/*
	 * This protected method (package visibility and descendents) is used for
	 * know if the panel can support a new hit, and means that has been time
	 * enough (class constant TIME) since the last time it was hit.
	 */
	protected boolean isAccepted() {
		return ((System.currentTimeMillis() - lastHit) >= TIME);
	}

	/**
	 * Method for receive a hit. This method hits the panel and return the
	 * points to add.
	 * 
	 * @return The points added by the panel.
	 */
	public int receiveHit() {
		if (this.isAccepted()) {
			if (visible) {
				this.hitsNumber += 1;
				this.lastHit = System.currentTimeMillis();
				this.visible = false;
				return points;
			} else {
				this.visible = true;
				this.lastHit = System.currentTimeMillis();
				return 0;
			}
		}
		return 0;
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[region=" + region + ", points="
				+ points + ", visible=" + visible + ", hitsNumber="
				+ hitsNumber + "]";
	}

	/**
	 * Override of hashCode() method from Object class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hitsNumber;
		result = prime * result + points;
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		// result = prime * result + (int) (ultimoGolpe ^ (ultimoGolpe >>> 32));
		// We do not check this one because it is an intern attribute.
		result = prime * result + (visible ? 1231 : 1237);
		return result;
	}

	/**
	 * Override of equals() method from Object class.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Panel other = (Panel) obj;
		if ((hitsNumber != other.hitsNumber) || (visible != other.visible)
				|| (points != other.points))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		// if (ultimoGolpe != other.ultimoGolpe)
		// return false;
		// We do not check this one because it is an intern attribute.
		return true;
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public Panel clone() {
		Panel copy = null;
		try {
			copy = (Panel) super.clone();
		} catch (CloneNotSupportedException e) {
			assert false : "Unnable to clone.";
		}
		return copy;
	}

	/**
	 * Implementation of getDimension() method from Dibujable interface.
	 */
	@Override
	public IRegion getDimension() {
		return region;
	}

	/**
	 * Implementación of getImagen() method from Dibujable interface [ getImagen
	 * = getImage ].
	 */
	@Override
	public String getImagen() {
		if (visible)
			return "basic-panel";
		return "";
	}
}
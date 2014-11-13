package geometry;

import paneles.vista.IPunto;

/**
 * This class defines the figure of Point. In this class we implemented all
 * those methods that can be applied on any object of Point type. Because the library
 * paneles-vista.jar is in spanish some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Point implements Cloneable, IPunto {

	private final int x;
	private final int y;

	/**
	 * Constructor of a point. This constructor creates a point with the two
	 * parameters that we set.
	 * 
	 * @param x
	 *            Value of X coordinate.
	 * @param y
	 *            Value of Y coordinate.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor of a point. This constructor creates a point without setting
	 * any parameters, it set the coordinates in [0, 0].
	 * 
	 * @see #Point(int, int)
	 */
	public Point() {
		this(0, 0);
	}

	/**
	 * Constructor of a point (Copy Constructor). This constructor creates a
	 * point using a previous existing point.
	 * 
	 * @param other
	 *            The point used as reference.
	 * @see #Point(int, int)
	 */
	public Point(Point other) {
		this(other.x, other.y);
	}

	/**
	 * Method for get the X coordinate. This method returns the X coordinate of
	 * the point.
	 * 
	 * @return Value of X coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method for get the Y coordinate. This method returns the Y coordinate of
	 * the point.
	 * 
	 * @return Value of Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[x = " + x + ", y = " + y + "]";
	}

	/**
	 * Override of hashCode() method from Object class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Point otro = (Point) obj;
		return (x == otro.y) && (y == otro.y);
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public Point clone() {
		Point copy = null;
		try {
			copy = (Point) super.clone();
		} catch (CloneNotSupportedException e) {
			assert false : "Unnable to clone";
		}
		return copy;
	}

	/**
	 * Implementation of getCoordenadaX() method from IPunto interface
	 * [getCoordenadaX = getXCoordinate].
	 */
	@Override
	public int getCoordenadaX() {
		return this.x;
	}

	/**
	 * Implementation of getCoordenadaY() method from IPunto interface
	 * [getCoordenadaY = getYCoordinate].
	 */
	@Override
	public int getCoordenadaY() {
		return this.y;
	}
}
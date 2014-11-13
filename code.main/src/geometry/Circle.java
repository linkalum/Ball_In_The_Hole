package geometry;

/**
 * This class defines the Circle figure. In this class we implemented all those
 * methods that can be applied on any object of Circle type, which inherit from Figure
 * abstract class.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Circle extends Figure implements Cloneable {

	private Point center;
	private int radius;

	/**
	 * Constructor of a circle. This constructor creates a circle with the
	 * parameters that we pass.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The value of the radius parameter must be positive
	 * and bigger than 0.</li>
	 * </ul>
	 * 
	 * @param center
	 *            The center of the circle (Point type).
	 * @param radius
	 *            The radius of the circle (int type).
	 */
	public Circle(Point center, int radius) {
		if (radius <= 0)
			throw new IllegalArgumentException(
					"The radius must have a positive value and bigger than 0.");
		this.center = new Point(center.getX(), center.getY());
		this.radius = radius;
	}

	/**
	 * Constructor of a circle (Copy constructor). This constructor creates a
	 * circle using a previous existing circle.
	 * 
	 * @param other
	 *            The circle used as reference.
	 * @see #Circle(Point, int)
	 */
	public Circle(Circle other) {
		this(other.center, other.radius);
	}

	/**
	 * Method for get the perimeter. This method calculates the perimeter of the
	 * circle.
	 * 
	 * @return The value of the circle's perimeter.
	 */
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	/**
	 * Method for get the center. This method gets the center of the circle.
	 * 
	 * @return The circule's center (Point type).
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Method for get the radius. This method gets the radius of the circle.
	 * 
	 * @return The circle's radius (int type).
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Method for moving the circle. This method moves the circle the distance
	 * we pass as parameters in coordinates.
	 * 
	 * @param x
	 *            The value of X axis moving.
	 * @param y
	 *            The value of Y axis moving.
	 */
	public void move(int x, int y) {
		this.center = new Point(center.getX() + x, center.getY() + y);
	}

	/**
	 * Method for moving the circle. This method moves the circle to the point
	 * we pass as parameter.
	 * 
	 * @param other
	 *            Point where the circle is going to be placed.
	 */
	public void moveTo(Point other) {
		this.center = other;
	}

	/**
	 * Implementation of getLowestX() abstract method from Figure class.
	 * 
	 * @see Figure#getLowestX()
	 */
	@Override
	public int getLowestX() {
		return center.getX() - radius;
	}

	/**
	 * Implementation of getHighestX() abstract method from Figure class.
	 * 
	 * @see Figure#getHighestX()
	 */
	@Override
	public int getHighestX() {
		return center.getX() + radius;
	}

	/**
	 * Implementation of getLowestY() abstract method from Figure class.
	 * 
	 * @see Figure#getLowestY()
	 */
	@Override
	public int getLowestY() {
		return center.getY() - radius;
	}

	/**
	 * Implementation of getHighestY() abstract method from Figure class.
	 * 
	 * @see Figure#getHighestY()
	 */
	@Override
	public int getHighestY() {
		return center.getY() + radius;
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[center = " + center.toString()
				+ ", radius = " + radius + "]";
	}

	/**
	 * Override of hashCode() method from Object class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result + radius;
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
		Circle other = (Circle) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (radius != other.radius)
			return false;
		return true;
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public Circle clone() {
		Circle copy = null;
		try {
			copy = (Circle) super.clone();
		} catch (CloneNotSupportedException e) {
			assert false : "Unnable to clone.";
		}
		return copy;
	}
}
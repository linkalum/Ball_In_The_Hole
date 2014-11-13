package geometry;

/**
 * This class defines the figure Rectangle. In this class we implemented all
 * those methods that can be applied on any object of Rectangle type, which
 * inherit from Figure abstract class.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Rectangle extends Figure implements Cloneable {

	private final Point vertexDL;
	private final int sideX;
	private final int sideY;

	/**
	 * Constructor of a rectangle. This constructor creates a rectangle with the
	 * parameters we pass.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The value of the width that we pass must be positive
	 * and bigger than 0.</li>
	 * <li>Precondition 2: The value of height that we pass must be positive and
	 * bigger than 0.</li>
	 * </ul>
	 * 
	 * @param vertexDL
	 *            The point of the down-left vertex.
	 * @param sideX
	 *            The side on the X axis (width).
	 * @param sideY
	 *            The side on the Y axix (height).
	 */
	public Rectangle(Point vertexDownLeft, int width, int height) {
		if ((width <= 0) || (height <= 0))
			throw new IllegalArgumentException(
					"The rectangle must have positive and bigger than 0 dimensions.");
		this.vertexDL = new Point(vertexDownLeft);
		this.sideX = width;
		this.sideY = height;
	}

	/**
	 * Constructor of a rectangle (square). This constructor creates a rectangle
	 * with the parametros that we pass, fixing the width and the height at the
	 * same value.
	 * 
	 * @param vertexDownLeft
	 *            The point of the down-left vertex.
	 * @param side
	 *            The side of the square.
	 * @see #Rectangle(Point, int, int)
	 */
	public Rectangle(Point vertexDownLeft, int side) {
		this(vertexDownLeft, side, side);
	}

	/**
	 * Method for get the down-left vertex. This method provides the down-left
	 * vertex of the rectangle.
	 * 
	 * @return The point of the down-left vertex.
	 */
	public Point getVertexDL() {
		return vertexDL;
	}

	/**
	 * Method for get sideX (width). This method provides the width of the
	 * rectangle.
	 * 
	 * @return The side on X axis (width).
	 */
	public int getSideX() {
		return sideX;
	}

	/**
	 * Method for get sideY (height). This method provides the height of the
	 * rectangle.
	 * 
	 * @return The side on Y axis (width).
	 */
	public int getSideY() {
		return sideY;
	}

	/**
	 * Method for get the up-left vertex. This method provides the up-left
	 * vertex of the rectangle.
	 * 
	 * @return The point of the up-left vertex.
	 */
	public Point getVertexUL() {
		return new Point(vertexDL.getX(), vertexDL.getY() + sideY);
	}

	/**
	 * Method for get the up-right vertex. This method provides the up-right
	 * vertex of the rectangle.
	 * 
	 * @return The point of the up-right vertex.
	 */
	public Point getVertexUR() {
		return new Point(vertexDL.getX() + sideX, vertexDL.getY() + sideY);
	}

	/**
	 * Method for get the down-right vertex. This method provides the down-right
	 * vertex of the rectangle.
	 * 
	 * @return The point of the down-right vertex.
	 */
	public Point getVertexDR() {
		return new Point(vertexDL.getX() + sideX, vertexDL.getY());
	}

	/**
	 * Implementation of getLowestX() abstract method from Figure class.
	 * 
	 * @see Figure#getLowestX()
	 */
	@Override
	public int getLowestX() {
		return vertexDL.getX();
	}

	/**
	 * Implementation of getHighestX() abstract method from Figure class.
	 * 
	 * @see Figure#getHighestX()
	 */
	@Override
	public int getHighestX() {
		return getVertexUR().getX();
	}

	/**
	 * Implementation of getLowestY() abstract method from Figure class.
	 * 
	 * @see Figure#getLowestY()
	 */
	@Override
	public int getLowestY() {
		return vertexDL.getY();
	}

	/**
	 * Implementation of getHighestY() abstract method from Figure class.
	 * 
	 * @see Figure#getHighestY()
	 */
	@Override
	public int getHighestY() {
		return getVertexUR().getY();
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[vertxDL=" + vertexDL + ", sideX="
				+ sideX + ", sideY=" + sideY + "]";
	}

	/**
	 * Override of hashCode() method from Object class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sideX;
		result = prime * result + sideY;
		result = prime * result
				+ ((vertexDL == null) ? 0 : vertexDL.hashCode());
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
		Rectangle other = (Rectangle) obj;
		if ((sideX != other.sideX) || (sideY != other.sideY))
			return false;
		if (vertexDL == null) {
			if (other.vertexDL != null)
				return false;
		} else if (!vertexDL.equals(other.vertexDL))
			return false;
		return true;
	}

	/**
	 * Implementation of clone() method from Cloneable interface.
	 */
	@Override
	public Rectangle clone() {
		Rectangle copia = null;
		try {
			copia = (Rectangle) super.clone();
		} catch (CloneNotSupportedException e) {
			assert false : "Unnable to clone.";
		}
		return copia;
	}
}
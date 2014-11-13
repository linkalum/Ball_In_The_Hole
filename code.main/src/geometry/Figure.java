package geometry;

import java.util.LinkedList;

import paneles.vista.IPunto;
import paneles.vista.IRegion;

/**
 * This class defines the abstract class Figure. In this class we implement or
 * declare all those common methods that can be applied on any object that
 * inherit from the abstract class Figure. Because the library paneles-vista.jar
 * is in spanish some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public abstract class Figure implements IRegion {

	/**
	 * Method for get the lowest X coordinate. This method returns the value of
	 * the X coordinate of the figure's point that is farthest to the left.
	 * 
	 * @return Value of the lowest X coordinate.
	 */
	public abstract int getLowestX();

	/**
	 * Method for get the highest X coordinate. This method returns the value of
	 * the X coordinate of the figure's point that is farthest to the right.
	 * 
	 * @return Value of the highest X coordinate.
	 */
	public abstract int getHighestX();

	/**
	 * Method for get the lowest Y coordinate. This method returns the value of
	 * the Y coordinate of the figure's point that is farthest to the down.
	 * 
	 * @return Value of the lowest Y coordinate.
	 */
	public abstract int getLowestY();

	/**
	 * Method for get the highest Y coordinate. This method returns the value of
	 * the Y coordinate of the figure's point that is farthest to the up.
	 * 
	 * @return Value of the highest Y coordinate.
	 */
	public abstract int getHighestY();

	/**
	 * Method for get a framing polygon. This method create a polygon that
	 * frames the figure.
	 * 
	 * @return A collection of 4 points that frames the figure.
	 */
	public LinkedList<Point> getFramingPolygon() {
		LinkedList<Point> listPoints = new LinkedList<Point>();
		listPoints.add(new Point(getLowestX(), getLowestY()));
		listPoints.add(new Point(getLowestX(), getHighestY()));
		listPoints.add(new Point(getHighestX(), getLowestY()));
		listPoints.add(new Point(getHighestX(), getHighestY()));
		return listPoints;
	}

	/**
	 * This method checks if a point is inside of a figure. This method checks
	 * if the point we pass as parameter is located inside of the figure that
	 * calls the method.
	 * 
	 * @param point
	 *            Point we want to check.
	 * @return true if the point is inside the figure, false if it is not.
	 */
	public boolean isInside(Point point) {
		return (point.getX() >= this.getLowestX()
				&& point.getX() <= this.getHighestX()
				&& point.getY() >= this.getLowestY() && point.getY() <= this
				.getHighestY());
	}

	/**
	 * This method checks if a figure is inside of another figure. This method
	 * checks if the figure we pass as parameter is located inside of the figure
	 * that calls the method.
	 * 
	 * @param figure
	 *            Figura que queremos comprobar.
	 * @return true si la figura parámetro está dentro de la figura receptora,
	 *         false si no lo está.
	 */
	public boolean isInside(Figure figure) {
		boolean content = false;
		for (Point point : figure.getFramingPolygon()) {
			content = this.isInside(point);
			if (!content)
				return content;
		}
		return content;
	}

	/**
	 * This method checks if a figure overlaps to another figure. This method
	 * checks if the figure we pass as parameter overlaps to the figure that
	 * calls the method.
	 * 
	 * @param figure
	 *            Figure we want to check.
	 * @return true if the figure parameter is overlapped to the figure that
	 *         calls the method, false if it is not.
	 */
	public boolean isOverlapped(Figure figure) {
		return (this.getHighestX() >= figure.getLowestX()
				&& this.getLowestX() <= figure.getHighestX()
				&& this.getHighestY() >= figure.getLowestY() && this
				.getLowestY() <= figure.getHighestY());
	}

	/**
	 * Implementation of getPosicionInferiorDerecha() method from IRegion
	 * interface [getPosicionInferiorDerecha = getDownRightPosition].
	 */
	@Override
	public IPunto getPosicionInferiorDerecha() {
		return new Point(getHighestX(), getLowestY());
	}

	/**
	 * Implementation of getPosicionInferiorIzquierda() method from IRegion
	 * interface [getPosicionInferiorIzquierda = getDownLeftPosition].
	 */
	@Override
	public IPunto getPosicionInferiorIzquierda() {
		return new Point(getLowestX(), getLowestY());
	}

	/**
	 * Implementation of getPosicionSuperiorDerecha() method from IRegion
	 * interface [getPosicionSuperiorDerecha = getUpRightPosition].
	 */
	@Override
	public IPunto getPosicionSuperiorDerecha() {
		return new Point(getHighestX(), getHighestY());
	}

	/**
	 * Implementation of getPosicionSuperiorIzquierda() method from IRegion
	 * interface [getPosicionSuperiorIzquierda = getUpLeftPosition].
	 */
	@Override
	public IPunto getPosicionSuperiorIzquierda() {
		return new Point(getLowestX(), getHighestY());
	}
}

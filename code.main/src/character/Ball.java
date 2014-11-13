package character;

import java.util.Collection;

import scene.Panel;
import scene.FragilePanel;
import geometry.Circle;
import geometry.Direction;
import geometry.Point;
import paneles.vista.Dibujable;
import paneles.vista.IRegion;

/**
 * This class defines the ball of the game. In this class we implemented all
 * those methods that can be applied on any object of Ball type. Because the
 * library paneles-vista.jar is in spanish some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Ball implements Dibujable {

	private Circle region;
	private int speed;
	private int angle;
	private int acceleration;
	private int turnAngle;
	private int points;

	/**
	 * Constructor of a Ball. This constructor creates a ball with the
	 * parameters that we pass.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The acceleration that we pass must be bigger than 0.</li>
	 * <li>Precondition 2: The turn angle that we pass must be bigger than 0.</li>
	 * </ul>
	 * 
	 * @param region
	 *            The region occupied by the ball.
	 * @param acceleration
	 *            The increment of speed every time the ball is accelerated.
	 * @param turnAngle
	 *            The increment of angle every time the ball turns.
	 */
	public Ball(Circle region, int acceleration, int turnAngle) {
		if ((acceleration <= 0) || (turnAngle <= 0))
			throw new IllegalArgumentException(
					"The increments must be bigger than 0.");
		this.region = new Circle(region);
		this.acceleration = acceleration;
		this.turnAngle = turnAngle;
		this.speed = 0;
		this.angle = 0;
	}

	/**
	 * Constructor of a ball. This constructor creates a ball with the default
	 * parameters.
	 * 
	 * @param region
	 *            The region occupied by the ball.
	 * @see #Ball(Circle, int, int)
	 */
	public Ball(Circle region) {
		this(region, 1, 15);
	}

	/**
	 * Method for get the region.
	 * 
	 * @return The region occupied by the ball.
	 */
	public Circle getRegion() {
		return region;
	}

	/**
	 * Method for get the speed.
	 * 
	 * @return The ball's speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Method for get the angle.
	 * 
	 * @return The ball's angle of movement.
	 */
	public int getAngle() {
		return angle;
	}

	/*
	 * Method for set the angle of movement. <p> <strong>Preconditions:</strong>
	 * </p> <ul> <li>Precondition 1: The angle that we pass must be bigger or
	 * equal than 0.</li> </ul>
	 * 
	 * @param angleMovement Angle that we set as new angle of movement.
	 */
	private void setAngleMovement(int angleMovement) {
		if (angleMovement < 0)
			throw new IllegalArgumentException(
					"The angle of movement must be bigger or equal than 0º.");
		if (angleMovement > 360)
			angleMovement = angleMovement % 360;
		this.angle = angleMovement;
	}

	/**
	 * Method for get the acceleration.
	 * 
	 * @return The ball's acceleration.
	 */
	public int getAcceleration() {
		return acceleration;
	}

	/**
	 * Method for get the turn angle.
	 * 
	 * @return The ball's turn angle.
	 */
	public int getTurnAngle() {
		return turnAngle;
	}

	/**
	 * Method for get the player's points.
	 * 
	 * @return The ball's points.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Method for accelerate the ball. It has 10 as speed limit for helping to
	 * see the ball.
	 */
	public void accelerate() {
		if ((speed += acceleration) > 10)
			speed = 10;
	}

	/**
	 * Method for breaking the ball. It has 0 as lower speed limit for avoiding
	 * to change the ball direction when the speed value goes negative.
	 */
	public void breaking() {
		if ((speed -= acceleration) < 0)
			speed = 0;
	}

	/**
	 * Method for rotate the ball's in clockwise direction. This method applies
	 * the turn angle in clockwise direction.
	 */
	public void turnClockwise() {
		if ((angle -= turnAngle) < 0)
			angle += 360;
	}

	/**
	 * Method for rotate the ball's in counterclockwise direction. This method
	 * applies the turn angle in counterclockwise direction.
	 */
	public void turnCounterclockwise() {
		angle = (angle + turnAngle) % 360;
	}

	/**
	 * Method for moving the ball. This method moves the ball using its
	 * direction and speed. We return the point of its center previous to the
	 * movement.
	 * 
	 * @return The point previous to the movement.
	 */
	public Point move() {
		Point stablePoint = this.region.getCenter();
		int incX = (int) Math
				.round(speed * 3 * Math.cos(Math.toRadians(angle)));
		int incY = (int) Math
				.round(speed * 3 * Math.sin(Math.toRadians(angle)));
		region.move(incX, incY);
		return stablePoint;
	}

	/**
	 * Mehtod that rebound the ball. This method rebound the ball according to
	 * the direction that we pass. In exameple, if we pass "RIGHT" it means that
	 * the ball rebounds with the right wall of the board.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The direction must be not null</li>
	 * </ul>
	 * 
	 * @param direction
	 *            The rebound direction.
	 */
	public void rebound(Direction direction) {
		if (direction != null) {
			switch (direction) {
			case UP:
				if (angle == 90)
					setAngleMovement(270);
				else if ((angle > 90) && (angle < 180))
					angle += 90;
				else if ((angle < 90) && (angle > 0))
					angle += 270;
				break;
			case DOWN:
				if (angle == 270)
					setAngleMovement(90);
				else if ((angle > 270) && (angle < 360))
					angle -= 270;
				else if ((angle > 180) && (angle < 270))
					angle -= 90;
				break;
			case RIGHT:
				if (angle == 0)
					setAngleMovement(180);
				else if ((angle > 270) && (angle < 360))
					angle -= 90;
				else if ((angle > 0) && (angle < 90))
					angle += 90;
				break;
			case LEFT:
				if (angle == 180)
					setAngleMovement(0);
				else if ((angle > 180) && (angle < 270))
					angle += 90;
				else if ((angle > 90) && (angle < 180))
					angle -= 90;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Method that makes the ball interacts with a group of panels. If the ball
	 * must hit the panel (their regions are overlapped) will hit it for getting
	 * the panel's point.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The colection of panels must be not null.</li>
	 * </ul>
	 * 
	 * @param panels
	 *            A colection of the panels against the ball will hit.
	 * @return true if the ball has interacted correctly and it has not fall in
	 *         a sunk panel, false if it falls in a sunk panel and the game is
	 *         over.
	 */
	public boolean interact(Collection<Panel> panels) {
		if (panels == null)
			throw new IllegalArgumentException("The panels colection is null.");
		for (Panel panel : panels) {
			if (this.getRegion().isOverlapped(panel.getRegion())) {
				if ((panel instanceof FragilePanel)
						&& (panel.getRegion().isInside(this.getRegion()
								.getCenter()))
						&& (((FragilePanel) panel).isSunk())) {
					return false;
				} else
					this.points += panel.receiveHit();
			}
		}
		return true;
	}

	/**
	 * Override of toString() method from Object class.
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[region = " + region.toString()
				+ ", speed = " + speed + ", angle = " + angle
				+ ", acceleration = " + acceleration + ", turnAngle = "
				+ turnAngle + ", points = " + points + "]";
	}

	/**
	 * Implementation of getDimension() method from Dibujable interface.
	 */
	@Override
	public IRegion getDimension() {
		return region;
	}

	/**
	 * Implementation of getImagen() method from Dibujable interface [ getImagen
	 * = getImage ].
	 */
	@Override
	public String getImagen() {
		if (speed == 0)
			return "stopped-ball";
		return "moving-ball";
	}

}
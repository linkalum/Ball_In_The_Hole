package scene;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import geometry.Direction;
import geometry.Point;
import geometry.Rectangle;
import character.Ball;
import paneles.vista.Dibujable;
import paneles.vista.IRegion;

/**
 * This class defines the board of the game. In this class we implemented all
 * those methods that can be applied on any object of Board type. Because the
 * library paneles-vista.jar is in spanish some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Board implements Dibujable {

	private final int width;
	private final int height;
	private final Rectangle region;
	private LinkedList<Panel> collectionOfPanels;
	private Ball ball;
	private Status gameStatus;

	/**
	 * Constructor of a board. This constructor creates a board with the
	 * parameters that we pass. The down-left vertex will always be the point
	 * [0,0] and the status will be NON_STARTED.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The height that we pass must be bigger or equal 0
	 * (positive value).</li>
	 * <li>Precondition 2: The width that we pass must be bigger or equal 0
	 * (positive value).</li>
	 * </ul>
	 * 
	 * @param width
	 *            The board's width.
	 * @param height
	 *            The board's height.
	 */
	public Board(int width, int height) {
		if (width < 0 || height < 0)
			throw new IllegalArgumentException(
					"The height and width values must be positive.");
		this.width = width;
		this.height = height;
		this.region = new Rectangle(new Point(), width, height);
		this.collectionOfPanels = new LinkedList<Panel>();
		this.ball = null;
		this.gameStatus = Status.NON_STARTED;
	}

	/**
	 * Method for get the width.
	 * 
	 * @return The board's width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Method for get the height.
	 * 
	 * @return The board's height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Method for get the region.
	 * 
	 * @return The region occupied by the board.
	 */
	public Rectangle getRegion() {
		return region;
	}

	/**
	 * Method for get the panels collection.
	 * 
	 * @return A copy of the panels collection.
	 */
	@SuppressWarnings("unchecked")
	public Collection<Panel> getCollectionOfPanels() {
		return (Collection<Panel>) collectionOfPanels.clone();
	}

	/**
	 * Method for get the ball.
	 * 
	 * @return The board's ball.
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * Method for get the status of the game.
	 * 
	 * @return The board's status.
	 */
	public Status getGameStatus() {
		return gameStatus;
	}

	/**
	 * Method for get the score.
	 * 
	 * @return The ball's actual points.
	 */
	public int getScore() {
		if (this.ball == null)
			return 0;
		return this.ball.getPoints();
	}

	/**
	 * Method for adding a panel to the board. This method adds the panel that
	 * we pass as a parameter to the board's collection of panels.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The game status must be NON_STARTED.</li>
	 * <li>Precondition 2: The panel must be inside the board.</li>
	 * </ul>
	 * 
	 * @param panel
	 *            The panel to add.
	 */
	public void addPanel(Panel panel) {
		if (this.gameStatus != Status.NON_STARTED)
			throw new IllegalStateException(
					"The game status must be NON_STARTED.");
		else {
			if (!this.region.isInside(panel.getRegion()))
				throw new IllegalArgumentException(
						"The panel must be inside the board.");
			else
				this.collectionOfPanels.addLast(panel);
		}
	}

	/**
	 * Method for inicialize the ball. This method set a new ball to the board.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The game status must be NON_STARTED.</li>
	 * <li>Precondition 2: The ball must be inside the board.</li>
	 * </ul>
	 * 
	 * @param ball
	 *            The ball assigned to the board.
	 */
	public void settingBall(Ball ball) {
		if (this.gameStatus != Status.NON_STARTED)
			throw new IllegalStateException(
					"The game status must be NON_STARTED.");
		else {
			if (!this.region.isInside(ball.getRegion()))
				throw new IllegalArgumentException(
						"The ball must be inside the board.");
			else
				this.ball = ball;
		}
	}

	/**
	 * Method for start the game. This method change the game status from
	 * NON_STARTED to STARTED.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The game status must be NON_STARTED.</li>
	 * </ul>
	 */
	public void start() {
		if (this.gameStatus != Status.NON_STARTED)
			throw new IllegalStateException(
					"The game status must be NON_STARTED before start.");
		else
			this.gameStatus = Status.STARTED;
	}

	/**
	 * Method for stop the game. This method change the game status from STARTED
	 * to ENDED.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The game status must be STARTED.</li>
	 * </ul>
	 */
	public void stop() {
		if (this.gameStatus != Status.STARTED)
			throw new IllegalStateException(
					"The game status must be STARTED before stop.");
		else
			this.gameStatus = Status.ENDED;
	}

	/**
	 * Method for generate and add panels to the board. This method generates
	 * panels and place them on the board randomly for having several different
	 * games.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The game status must be NON_STARTED.</li>
	 * <li>Precondition 2: The number of horizontal and vertical cells must be
	 * proportional to the height and the width of the board.</li>
	 * <li>Precondition 3: The number of panels must not be bigger than the
	 * board's number of cells.</li>
	 * </ul>
	 * 
	 * @param cellsX
	 *            Amount of panels that can fix on X axis.
	 * @param cellsY
	 *            Amount of panels that can fix on Y axis.
	 * @param numPanels
	 *            Amount of panels we want to add (they don't have to fill the
	 *            board).
	 * @param points
	 *            Amount of points that we pass to the generated panels.
	 */
	public void generatePanels(int cellsX, int cellsY, int numPanels, int points) {
		if (this.gameStatus != Status.NON_STARTED)
			throw new IllegalStateException(
					"The game status must be NON_STARTED.");
		if (((this.width % cellsX) != 0) || ((this.height % cellsY) != 0))
			throw new IllegalArgumentException(
					"The number of horizontal and vertical cells must be proportional to the height and the width of the board.");
		if (numPanels > (cellsX * cellsY))
			throw new IllegalArgumentException(
					"The number of panels must not be bigger than the board's number of cells.");

		boolean[] arrayCells = new boolean[cellsX * cellsY];
		for (int i = 0; i < numPanels; i++) {
			int cont = collectionOfPanels.size();
			while (cont == collectionOfPanels.size()) {
				// Build a random point.
				Random aleatorium = new Random();
				int cell = aleatorium.nextInt(cellsX * cellsY);
				int x = (cell % cellsX) * (this.width / cellsX);
				int y = (cell / cellsY) * (this.height / cellsY);
				Point randomPoint = new Point(x, y);
				// Check if the point is valid and add it.
				if (!arrayCells[cell]) {
					Panel newPanel = new Panel(new Rectangle(randomPoint,
							(this.width / cellsX), (this.height / cellsY)),
							points);
					collectionOfPanels.add(newPanel);
					arrayCells[cell] = true;
				}
			}
		}
	}

	/**
	 * Method for update and move the game forward. This method moves the ball,
	 * check and handle rebounds if there is one and make the ball interacts
	 * with the board's panels.
	 * 
	 * <p>
	 * <strong>Preconditions:</strong>
	 * </p>
	 * <ul>
	 * <li>Precondition 1: The game status must be STARTED.</li>
	 * 
	 * @return The value (boolean) returned by the ball's interaction with the
	 *         panels collection.
	 */
	public boolean update() {
		if (this.gameStatus != Status.STARTED)
			throw new IllegalStateException("The game status must be STARTED.");
		else {
			// Move the ball
			Point stablePoint = this.ball.move();
			// Check if it must rebound.
			if (!this.region.isInside(this.ball.getRegion())) {
				// Check the rebound direction.
				if (this.ball.getRegion().getHighestX() > this.width) {
					this.ball.getRegion().moveTo(stablePoint);
					this.ball.rebound(Direction.RIGHT);
				} else if (this.ball.getRegion().getLowestX() < 0) {
					this.ball.getRegion().moveTo(stablePoint);
					this.ball.rebound(Direction.LEFT);
				} else if (this.ball.getRegion().getHighestY() > this.height) {
					this.ball.getRegion().moveTo(stablePoint);
					this.ball.rebound(Direction.UP);
				} else if (this.ball.getRegion().getLowestY() < 0) {
					this.ball.getRegion().moveTo(stablePoint);
					this.ball.rebound(Direction.DOWN);
				}
			}
			// The ball interact with the panels.
			return this.ball.interact(collectionOfPanels);
		}
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
		return "fondo"; // THE BACKGROUND NAME MUST BE fondo BECAUSE OF THE
						// LIBRARY. THIS COULD BE A THING TO FIX.
	}
}
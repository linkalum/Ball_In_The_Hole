package game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scene.Status;
import scene.GrowingPanel;
import scene.FragilePanel;
import scene.PanelMinus;
import scene.PanelPlus;
import scene.Board;
import geometry.Circle;
import geometry.Point;
import geometry.Rectangle;
import character.Ball;
import paneles.vista.Dibujable;
import paneles.vista.IControlador;
import paneles.vista.IPantalla;

/**
 * This class defines one game for this application. In this class we
 * implemented all those methods that are applied on an object of the type Game,
 * which is a class that implements the interface IControlador. Because the
 * library paneles-vista.jar is in spanish some of the methods are in spanish.
 * 
 * @author José Sirés Campos (original)
 * @author Alejandro Campillo Martínez (original)
 * 
 */
public class Game implements IControlador {

	private Board board;
	private IPantalla screen;
	private int MAX_POINTS = 100;
	private int MAX_LEVELS = 3;
	private int INIT_ACCELERATION = 1;
	private int INIT_TURNANGLE = 15;
	private boolean optionsChanged = false;
	private int currentLevel;

	@Override
	public void abrir(String arg0) {
	}

	/**
	 * Implementation of actualiza() method from IControlador interface
	 * [actualiza = update]. Here we control if the ball felt into a hole, if
	 * the player win the game or if we just have to update all the screen.
	 */
	@Override
	public void actualiza() {
		if (this.board.update()) {
			if (this.board.getScore() >= MAX_POINTS) {
				this.board.stop();
				JOptionPane.showMessageDialog(null, "YOU REACH " + MAX_POINTS
						+ " POINTS", "NEW LEVEL", JOptionPane.CANCEL_OPTION);
				if (currentLevel > 0)
					newLevel();
				else
					JOptionPane.showMessageDialog(null, "YOU WON " + MAX_LEVELS
							+ " LEVELS", "BIG WINNER",
							JOptionPane.CANCEL_OPTION);
			} else {
				// [setBarraEstado = setStateBar]
				this.screen.setBarraEstado("Position: ["
						+ board.getBall().getRegion().getCenter().getX() + ", "
						+ board.getBall().getRegion().getCenter().getY()
						+ "]            Speed: " + board.getBall().getSpeed()
						+ "            Angle: " + board.getBall().getAngle()
						+ "            Points: " + board.getBall().getPoints());
			}
		} else {
			this.board.stop();
			JOptionPane.showMessageDialog(null, "YOU FELL INTO THE HOLE",
					"GAME OVER", JOptionPane.CANCEL_OPTION);
		}
	}

	/**
	 * Implementation of getAlturaVisualizacion() method from IControlador
	 * interface [getAlturaVisualizacion = getHeightVisualization (return the
	 * height of the board)].
	 */
	@Override
	public int getAlturaVisualizacion() {
		return this.board.getHeight();
	}

	/**
	 * Implementation of getAnchuraVisualizacion() method from IControlador
	 * interface [getAnchuraVisualizacion = getWidthVisualization (return the
	 * width of the board)].
	 */
	@Override
	public int getAnchuraVisualizacion() {
		return this.board.getWidth();
	}

	/**
	 * Implementation of getDibujables() method from IControlador interface
	 * [getDibujalbes = getDrawables].
	 */
	@Override
	public Collection<Dibujable> getDibujables() {
		Collection<Dibujable> completeCollection = new LinkedList<Dibujable>();
		completeCollection.addAll(board.getCollectionOfPanels());
		completeCollection.add(board.getBall());
		return completeCollection;
	}

	/**
	 * Implementation of juegoPreparado() method from IControlador
	 * interface[juegoPreparado = gameReady].
	 */
	@Override
	public boolean juegoPreparado() {
		if (board.getGameStatus() == Status.STARTED)
			return true;
		return false;
	}

	// Private method for adding special panels.
	private void addSpecialPanels() {
		for (int i = 0; i < 4; i++) {
			Random aleatory = new Random();
			int x = aleatory.nextInt(this.board.getWidth() - 50);
			int y = aleatory.nextInt(this.board.getHeight() - 50);
			Point aleatoryPoint = new Point(x, y);
			switch (i) {
			case 0:
				this.board.addPanel(new GrowingPanel(new Rectangle(
						aleatoryPoint, 50), 2));
				break;
			case 1:
				this.board.addPanel(new FragilePanel(new Rectangle(
						aleatoryPoint, 50), 5, 3));
				break;
			case 2:
				this.board.addPanel(new PanelMinus(new Rectangle(aleatoryPoint,
						50), 5));
				break;
			case 3:
				this.board.addPanel(new PanelPlus(new Rectangle(aleatoryPoint,
						50), 5, 1));
				break;
			default:
				break;
			}
		}
	}

	// Private method for starting a new level in the same game.
	private void newLevel() {
		this.currentLevel--;
		this.board = new Board(500, 500);
		if (this.optionsChanged) {
			this.board.settingBall(new Ball(
					new Circle(new Point(250, 250), 20), INIT_ACCELERATION,
					INIT_TURNANGLE));
		} else {
			this.board
					.settingBall(new Ball(new Circle(new Point(250, 250), 20)));
		}
		this.board.generatePanels(10, 10, 6, 3);
		this.addSpecialPanels();
		this.board.start();
	}

	// Private method for showing the options menu and changing the game
	// options.
	private void options() {
		JTextField init_acc_button = new JTextField();
		JTextField init_turn_ang_button = new JTextField();
		JTextField max_points_button = new JTextField();
		JTextField max_level_button = new JTextField();

		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		optionPanel.add(new JLabel("Initial acceleration:"));
		optionPanel.add(init_acc_button);
		optionPanel.add(new JLabel("Initial turn angle:"));
		optionPanel.add(init_turn_ang_button);
		optionPanel.add(new JLabel("Points per level:"));
		optionPanel.add(max_points_button);
		optionPanel.add(new JLabel("Number of levels:"));
		optionPanel.add(max_level_button);

		int result = JOptionPane.showConfirmDialog(null, optionPanel,
				"OPTIONS.", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if (init_acc_button.getText().equals("")
					|| Integer.parseInt(init_acc_button.getText()) <= 0)
				this.INIT_ACCELERATION = 1;
			else
				this.INIT_ACCELERATION = Integer.parseInt(init_acc_button
						.getText());

			if (init_turn_ang_button.getText().equals("")
					|| Integer.parseInt(init_turn_ang_button.getText()) <= 0)
				this.INIT_TURNANGLE = 15;
			else
				this.INIT_TURNANGLE = Integer.parseInt(init_turn_ang_button
						.getText());

			if (max_points_button.getText().equals("")
					|| Integer.parseInt(max_points_button.getText()) < 0)
				this.MAX_POINTS = 100;
			else
				this.MAX_POINTS = Integer.parseInt(max_points_button.getText());

			if (max_level_button.getText().equals("")
					|| Integer.parseInt(max_level_button.getText()) < 0)
				this.MAX_LEVELS = 3;
			else
				this.MAX_LEVELS = Integer.parseInt(max_level_button.getText());

			this.optionsChanged = true;
			if (MAX_LEVELS == 0) {
				newLevel();
				this.board.stop();
				JOptionPane.showMessageDialog(null, "YOU WON " + MAX_LEVELS
						+ " LEVELS", "BIG WINNER", JOptionPane.CANCEL_OPTION);
			} else {
				this.currentLevel = MAX_LEVELS;
				newLevel();
			}
		} else {
			nueva();
		}
	}

	/**
	 * Implementation of nueva() method from IControlador interface [nueva =
	 * new]. Here we also implemented the main menu.
	 */
	@Override
	public void nueva() {
		int selection = JOptionPane
				.showOptionDialog(null, "Seleccione opcion",
						"BALL IN THE HOLE", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] {
								"START NEW GAME", "OPTIONS", "EXIT" },
						"START NEW GAME");

		if (selection == 0) {
			// START NEW GAME
			this.currentLevel = MAX_LEVELS;
			newLevel();
		} else if (selection == 1) {
			// OPTIONS
			options();
		} else {
			// EXIT
			newLevel();
			this.board.stop();
			JOptionPane.showMessageDialog(null, "HOPE TO SEE YOU SOON",
					"GAME OVER", JOptionPane.CANCEL_OPTION);
		}
	}

	/**
	 * Implementation of setPantalla() method from IControlador interface
	 * [setPantalla = setScreen].
	 */
	@Override
	public void setPantalla(IPantalla arg0) {
		this.screen = arg0;
	}

	/**
	 * Implementation of solicitaAcelerar() method from IControlador interface
	 * [solicitaAcelerar = requestAccelerate].
	 */
	@Override
	public void solicitaAcelerar() {
		this.board.getBall().accelerate();
	}

	/**
	 * Implementation of solicitaFrenar() method from IControlador interface
	 * [solicitaFrenar = requestBreak].
	 */
	@Override
	public void solicitaFrenar() {
		this.board.getBall().breaking();
	}

	/**
	 * Implementation of solicitaGirarInversa() method from IControlador
	 * interface [solicitaGirarInversa = requestInverseTurn].
	 */
	@Override
	public void solicitaGirarInversa() {
		this.board.getBall().turnCounterclockwise();
	}

	/**
	 * Implementation of solicitaGirarReloj() method from IControlador interface
	 * [solicitaGirarReloj = requestWatchTurn].
	 */
	@Override
	public void solicitaGirarReloj() {
		this.board.getBall().turnClockwise();
	}
}
package game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

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

	@Override
	public void abrir(String arg0) {
	}

	/**
	 * Implementation of actualiza() method from IControlador interface
	 * [actualiza = update].
	 */
	@Override
	public void actualiza() {
		if (this.board.update()) {
			this.screen.setBarraEstado("Position: ["
					+ board.getBall().getRegion().getCenter().getX() + ", "
					+ board.getBall().getRegion().getCenter().getY()
					+ "]            Speed: " + board.getBall().getSpeed()
					+ "            Angle: " + board.getBall().getAngle()
					+ "            Points: " + board.getBall().getPoints());
		} else {
			this.board.parar();
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

	/**
	 * Implementation of nueva() method from IControlador interface [nueva =
	 * new].
	 */
	@Override
	public void nueva() {
		this.board = new Board(500, 500);
		this.board
				.settingBall(new Ball(new Circle(new Point(250, 250), 20)));
		this.board.generatePanels(10, 10, 6, 3);
		this.addSpecialPanels();
		this.board.start();
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
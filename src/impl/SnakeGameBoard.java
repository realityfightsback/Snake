package impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controls.Keyboard;

public class SnakeGameBoard extends JFrame implements Runnable {

	private Image dbImage;
	private Graphics dbg;

	// Can be customized or left as is for fullscreen
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	int snakePixelWidth = 10;

	Snake snake = new Snake();

	boolean gameSurface[][];

	public SnakeGameBoard() throws HeadlessException {
		super();

		addKeyListener(new Keyboard(snake));

		gameSurface = new boolean[screenWidth][screenHeight];

		setSize(screenWidth, screenHeight);
		setBackground(Color.WHITE);
		setTitle("SnakeGame");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void run() {
		// TODO work in end of game/start conditional
		snake.initializeSnake(1, 0);
		while (true) {
			try {
				this.repaint();
				if (Math.random() > .7){
					snake.increaseSnakeSize();
				System.out.println("Increasing snake size");
				}
				updateGameState();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);

		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();

		drawSnake();
		// dbg.drawLine(50, 100, 50, 100);

		g.drawImage(dbImage, 0, 0, null);

	}

	private void drawSnake() {
		// Draws column by column, maybe very inefficient doing many draws
		// rather than aggregating.

		/*
		 * 1 2 3 4 5 1 F F F F F 2 F F F F F 3 F F F F F 4 F F F F F 5 F F F F F
		 */
		for (int i = 0; i < gameSurface.length; i++) {
			for (int j = 0; j < gameSurface[i].length; j++) {

				dbg.setColor(Color.RED);
				if (gameSurface[i][j] == true) {
					// dbg.drawLine(i, j, i, j);
					dbg.fillOval(i, j, 25, 25);

				}

				// dbg.drawLine(40, 40, 40, 40);

				// dbg.fillRect(50, 50, 50, 50);
			}
		}
		// dbg.fillRect(50, 50, snakePixelWidth, 3*snakePixelWidth);
	}

	private void updateGameState() {
		Location noLongerSnake = snake.move();

		updateGameSurface(noLongerSnake);

		// printGameSurface();

		checkForCollisions();
		

		System.out.println("Head now at "
				+ snake.head.currLocation.toString() + " Tail at ");
		if(noLongerSnake != null){
			System.out.println("(" + noLongerSnake.x + "," + noLongerSnake.y + ")");
		}

	}

	private void updateGameSurface(Location noLongerSnake) {
		addSnakeHeadSegment();

		if (noLongerSnake != null)
			removeSnakeTailSegment(noLongerSnake);

	}

	private void removeSnakeTailSegment(Location noLongerSnake) {
		int x = noLongerSnake.x;
		int y = noLongerSnake.y;

		gameSurface[x][y] = false;

	}

	private void addSnakeHeadSegment() {
		int x = snake.head.currLocation.x;
		int y = snake.head.currLocation.y;

		gameSurface[x][y] = true;

	}

	// TODO
	private void checkForCollisions() {

	}

	// private void printGameSurface() {
	// for (int i = 0; i < gameSurface.length; i++) {
	// for (int j = 0; j < gameSurface[i].length; j++) {
	// System.out.print(booleanAsLetter(gameSurface[i][j]));
	// }
	// System.out.println();
	// }
	// }
	//
	// private String booleanAsLetter(boolean b) {
	// if (b)
	// return "T";
	// return "F";
	// }

	/**
	 * I don't want to think in terms of the origin at the top left. Allows me
	 * to use bottom right origin thinking for the array.
	 * 
	 * @param y
	 * @return
	 */
	// private int translateYCoord(int y) {
	// return screenHeight - y;
	// }
}

package controls;

import impl.Snake;
import impl.SnakeBodyParts.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {

	Snake snake;

	public Keyboard(Snake snake) {
		this.snake = snake;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			snake.setHeadDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_UP:
			snake.setHeadDirection(Direction.UP);
			break;
		case KeyEvent.VK_RIGHT:
			snake.setHeadDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			snake.setHeadDirection(Direction.LEFT);
			break;

		default:
			break;
		}

	}
}

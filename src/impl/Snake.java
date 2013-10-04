package impl;

import java.util.ArrayDeque;

import impl.SnakeBodyParts.Direction;

public class Snake {

	Boolean popTail = true;
	Head head;
	// TODO probably need some kind of body segments to give, or a stack
	// Tail tail;

	// Push and pop LIFO style from this to remove old tail segments and retain
	// the snake body
	ArrayDeque<Location> tailStack = new ArrayDeque<Location>();

	int length = 0;

	public void initializeSnake(int hX, int hY) {

		head = new Head(hX, hY, Direction.DOWN);

		// tail = new Tail(tX, tY, Direction.DOWN);
	}

	/**
	 * Updates the snakes position.
	 * 
	 * @return The abandoned segment that was the tail. Update the gameSurface
	 *         to reflect it no longer being part of the snake
	 */
	public Location move() {
		head.updatePartLocation();
		tailStack.addLast(head.prevLocation);
		// tail.updatePartLocation();
		if (popTail)
			return tailStack.pop();
		else {
			popTail = true;
			return null;
		}

	}

	public void setHeadDirection(Direction d) {
		head.direction = d;
	}

	/**
	 * Back of the snake stay in place for a moment, which the head expands out
	 * a segment more
	 */
	public void increaseSnakeSize() {
		popTail = false;
	}

}

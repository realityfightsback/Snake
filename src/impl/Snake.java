package impl;

import java.util.ArrayDeque;

import impl.SnakeBodyParts.Direction;

public class Snake {

	Boolean popTail = true;
	Head head;
	// Head location is put on the end of the stack. The stack is popped to
	// remove the oldest former Head location. If we want to grow the snake
	// we do not pop on an iteration of the game loop.
	ArrayDeque<Location> tailStack;

	int length = 0;

	public void initializeSnake(int hX, int hY) {

		head = new Head(hX, hY, Direction.DOWN);
		tailStack = new ArrayDeque<Location>();
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
		length++;
	}

}

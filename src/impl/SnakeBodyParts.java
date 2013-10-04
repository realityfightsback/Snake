package impl;

public class SnakeBodyParts {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	};

	Direction direction;
	protected Location currLocation;
	protected Location prevLocation;

	public SnakeBodyParts(int x, int y, Direction d) {
		direction = d;
		currLocation = new Location(x, y);
		prevLocation = null;
	}

	public void updatePartLocation() {

		recordPreviousLocation();

		switch (direction) {
		case DOWN:
			currLocation.y++;
			break;
		case UP:
			currLocation.y--;
			break;
		case LEFT:
			currLocation.x--;
			break;
		case RIGHT:
			currLocation.x++;
			break;
		default:
			break;
		}

	}

	private void recordPreviousLocation() {
		prevLocation = new Location(currLocation);
	}
}

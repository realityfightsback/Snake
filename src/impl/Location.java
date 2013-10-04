package impl;

public class Location {
	int x, y;

	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Location(Location currLocation) {
		this.x = currLocation.x;
		this.y = currLocation.y;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + "," + y + ")";
	}

}

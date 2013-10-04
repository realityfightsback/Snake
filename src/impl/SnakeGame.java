package impl;

public class SnakeGame {
	
	public static void main(String[] args) {

		Thread t = new Thread(new SnakeGameBoard());
		t.start();

		
	}

}

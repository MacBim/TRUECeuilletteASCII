import java.util.Random;

public class Agent {
	
	Position position;
	/*
	 * float interest;
	 * ...
	 * 
	 */

	public Position getPosition() {
		return position;
	}
	
	public Agent(Position position) {
		this.position = position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	void move(GameIA gameIA, Position pos){
		Position oldPos = this.getPosition();
		gameIA.content[oldPos.getX()][oldPos.getY()] = "| ";
		this.setPosition(pos);
		int x = pos.getX();
		int y = pos.getY();
		gameIA.putAgent(this);
	}
	
	void moveRandom(GameIA gameIA){
		Random randomGenerator = new Random();
		int xRandom = randomGenerator.nextInt(100);
		int yRandom = randomGenerator.nextInt(100);
		Position randomPosition = new Position(xRandom, yRandom);
		this.move(gameIA, randomPosition);
	}
	
	void moveLevy(GameIA gameIA){
		//TODO levy
	}
}

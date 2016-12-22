import java.util.List;
import java.util.Random;

/**
 * @author Jean-Baptiste
 *
 */
public class Agent {
	
	private Position position;

	public Position getPosition() {
		return position;
	}
	
	/**
	 * Builder - Create an Agent to the designated position
	 * @param position
	 */
	public Agent(Position position) {
		this.position = position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Move the Agent using a random function to get the new coordinates
	 * @param gameEngine Game
	 * @param gamePanel Window of the game
	 */
	void moveRandom(GameEngine gameEngine, GamePanel gamePanel){
		Position oldPos = this.position;
		Random randomGenerator = new Random();
		int xRandom = randomGenerator.nextInt(gameEngine.getSize()*GameEngine.SCALE);
		int yRandom = randomGenerator.nextInt(gameEngine.getSize()*GameEngine.SCALE);
		Position randomPosition = new Position(xRandom, yRandom);
		this.position = randomPosition;

		gameEngine.putAgent(this, oldPos);
	}
	
	
	/**
	 * Calculate the distance to jump using a Lévy distribution function
	 * @param alpha
	 * @return the distance to jump
	 */
	int getLevyNumber(float alpha){
		Random rand = new Random();
		float m = 0;
		double a = 0;
		double b = 0;
		float somme = 0;
		int n = 100;
		for(int i = 0;i<n;i++){
			a = rand.nextGaussian();
			b = rand.nextGaussian();
			m = (float) (a / Math.pow(Math.abs(b),(1/alpha)));
			somme += m;
		}
		somme = (float) (((1 / Math.pow(n, (1/alpha))) * somme) * 10);
		return Math.round(somme);
	}

	
	/**
	 * Calculate the new coordinates of the agent using a randomly generated angle
	 * @param gameEngine Game
	 * @param alpha
	 * @param gamePanel Game Window
	 */
	void moveLevy(GameEngine gameEngine,float alpha,GamePanel gamePanel){
		
		int distanceToJump = this.getLevyNumber(alpha);
		Random rand = new Random();
		int angle = rand.nextInt(360);
		
		int radAngle = (int) Math.round(angle * Math.PI/180);
		
		// dans le cas ou l'angle tiré nous fait sortir de la map, on en un tire un autre valide
		int newX = (int) Math.round((Math.cos(radAngle)/distanceToJump)*100 + this.position.getX());
		while(newX < 0 || newX > gameEngine.getSize()*gameEngine.SCALE){ // si l'agent sort du terrain on cherche un meilleur angle
			 angle = rand.nextInt(360);
			 radAngle = (int) Math.round(angle * Math.PI/180);
			 newX = (int) Math.round((Math.cos(radAngle)/distanceToJump)*100 + this.position.getX());
		}
		
		// idem
		int newY = (int) Math.round((Math.sin(radAngle)/distanceToJump)*100 + this.position.getY());
		while(newY < 0 || newY > gameEngine.getSize()*gameEngine.SCALE){
			 angle = rand.nextInt(360);
			 radAngle = (int) Math.round(angle * Math.PI/180);
			 newY = (int) Math.round((Math.sin(radAngle)/distanceToJump)*100 + this.position.getY());
		}

		Position oldPostion = this.position;
		Position newPosition = new Position(newX, newY);
		
		this.setPosition(newPosition);
		
		gameEngine.putAgent(this,oldPostion);
	}
}

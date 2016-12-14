import java.util.List;
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
	
	void move(GameEngine gameIA, Position pos,GamePanel ui){
		Position oldPos = this.getPosition();
		this.setPosition(pos);
		gameIA.putAgent(this,oldPos);
	}
	
	void moveRandom(GameEngine gameEngine, GamePanel ui){
		Position oldPos = this.position;
		Random randomGenerator = new Random();
		int xRandom = randomGenerator.nextInt(gameEngine.getSize()*GameEngine.SCALE);
		int yRandom = randomGenerator.nextInt(gameEngine.getSize()*GameEngine.SCALE);
		Position randomPosition = new Position(xRandom, yRandom);
		this.position = randomPosition;
		
		//move(gameIA, randomPosition, ui);
		gameEngine.putAgent(this, oldPos);
	}
	int getLevyNumber(float alpha){
		Random rand = new Random();
		float m = 0;
		double a = 0;
		double b = 0;
		float somme = 0;
		for(int i = 0;i<100;i++){
			a = rand.nextGaussian();
			b = rand.nextGaussian();
			m = (float) (a / Math.pow(Math.abs(b),(1/alpha)));
			somme += m;
		}
		somme = (((float) (1 / Math.pow(100, (1/alpha))) * somme) * 10);
		return Math.round(somme);
	}
	public static int mod (int a, int b) {
        int res = a % b;
        if (res<0 && b>0) {
            res += b;
        }
        return res;
    }
	
	void moveLevy(GameEngine gameEngine,float alpha,GamePanel ui){
		int distanceToJump = this.getLevyNumber(alpha);
		
		Random rand = new Random();
		int angle = rand.nextInt(360);
		
		int radAngle = (int) Math.round(angle * Math.PI/180);
		
		int newX = (int) Math.round((Math.cos(radAngle)/distanceToJump)*100 + this.position.X);
		while(newX < 0 || newX > gameEngine.getSize()*gameEngine.SCALE){ // si l'agent sort du terrain on retire un angle
			 angle = rand.nextInt(360);
			 radAngle = (int) Math.round(angle * Math.PI/180);
			 newX = (int) Math.round((Math.cos(radAngle)/distanceToJump)*100 + this.position.X);
		}
		int newY = (int) Math.round((Math.sin(radAngle)/distanceToJump)*100 + this.position.Y);
		while(newY < 0 || newY > gameEngine.getSize()*gameEngine.SCALE){ // si l'agent sort du terrain on retire un angle
			 angle = rand.nextInt(360);
			 radAngle = (int) Math.round(angle * Math.PI/180);
			 newY = (int) Math.round((Math.sin(radAngle)/distanceToJump)*100 + this.position.Y);
		}
		//System.out.println("x : "+newX+"  y : "+newY);
		Position oldPostion = this.position;
		Position newPosition = new Position(newX, newY);
		
		this.setPosition(newPosition);
		//System.out.println("x : "+this.position.getX()+"+"+x+"  y : "+this.position.getY()+"+"+y);
		//System.out.println("x : "+mod(this.position.getX()+x,gameIA.content.length)+"  y : "+mod(this.position.getY()+y,gameIA.content.length));
		//System.out.println("x : "+this.position.X+"  y : "+this.position.Y);
		
		gameEngine.putAgent(this,oldPostion);
	}
}

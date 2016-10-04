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
		gameIA.content[oldPos.getX()][oldPos.getY()] = gameIA.EMPTY_SPACE;
		this.setPosition(pos);
		gameIA.putAgent(this);
	}
	
	void moveRandom(GameIA gameIA){
		Random randomGenerator = new Random();
		int xRandom = randomGenerator.nextInt(gameIA.content.length);
		int yRandom = randomGenerator.nextInt(gameIA.content.length);
		Position randomPosition = new Position(xRandom, yRandom);
		this.move(gameIA, randomPosition);
	}
//	void moveRandomGauss(GameIA gameIA){
//		Random randomGenerator = new Random();
////		randomGenerator.setSeed(2);
//
//		System.out.println("x : "+xRandom+" y : "+yRandom+"\n");
//		Position randomPosition = new Position(xRandom, yRandom);
//		this.move(gameIA, randomPosition);
//	}
	
	void moveLeoSteps(GameIA gameIA){
		/*
		 *
		 * This function will move agent this way:
		 *  Agent (0) can only move in case right next to him (X)
		 *  Like this:   
		 *   XXX
		 *   XOX
		 *   XXX
		 * 
		 *  Agent will proceed a few moves like this and then will do a big jump and start again
		 *  i.e for A(x,y), move can be A(x +/- 1, y +/- 1)
		 */
		Random randomGenerator = new Random();
		int xRandom = randomGenerator.nextInt(gameIA.content.length);
		int yRandom = randomGenerator.nextInt(gameIA.content.length);
		Position randomPosition = new Position(xRandom, yRandom);
		this.move(gameIA, randomPosition);
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
	
	void moveLevy(GameIA gameIA,float alpha){
		int x = this.getLevyNumber(alpha);
		int y = this.getLevyNumber(alpha);
		Position oldPos = this.position;
		gameIA.content[oldPos.getX()][oldPos.getY()] = gameIA.EMPTY_SPACE;
		//System.out.println("x : "+this.position.getX()+"+"+x+"  y : "+this.position.getY()+"+"+y);
		//System.out.println("x : "+mod(this.position.getX()+x,gameIA.content.length)+"  y : "+mod(this.position.getY()+y,gameIA.content.length));
		this.position.setX(mod(this.position.getX()+x,gameIA.content.length));
		this.position.setY(mod(this.position.getY()+y,gameIA.content.length));
		//System.out.println("x : "+this.position.X+"  y : "+this.position.Y);
		gameIA.putAgent(this);
	}
}

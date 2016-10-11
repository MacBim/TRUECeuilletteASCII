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
	
	void move(GameIA gameIA, Position pos,UIv2 ui){
		Position oldPos = this.getPosition();
		this.setPosition(pos);
		gameIA.putAgent(this,ui,oldPos);
	}
	
	void moveRandom(GameIA gameIA, UIv2 ui){
		Random randomGenerator = new Random();
		int xRandom = randomGenerator.nextInt(gameIA.getSize()*10);
		int yRandom = randomGenerator.nextInt(gameIA.getSize()*10);
		Position randomPosition = new Position(xRandom, yRandom);
		this.position = randomPosition;
		move(gameIA, randomPosition, ui);
		//gameIA.putAgent(this, ui, true);
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
	
	void moveLevy(GameIA gameIA,float alpha,UIv2 ui){
		int x = this.getLevyNumber(alpha);
		int y = this.getLevyNumber(alpha);
		Position oldPos = this.position;
		//System.out.println("x : "+this.position.getX()+"+"+x+"  y : "+this.position.getY()+"+"+y);
		//System.out.println("x : "+mod(this.position.getX()+x,gameIA.content.length)+"  y : "+mod(this.position.getY()+y,gameIA.content.length));

		this.position.setX(mod(this.position.getX()+x,gameIA.getSize()*10));
		this.position.setY(mod(this.position.getY()+y,gameIA.getSize()*10));
		//System.out.println("x : "+this.position.X+"  y : "+this.position.Y);
		gameIA.putAgent(this,ui,oldPos);
	}
}

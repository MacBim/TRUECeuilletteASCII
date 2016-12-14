import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameEngine implements Runnable {

	private static final Color PATCH = Color.GREEN;
	private static final Color AGENT = Color.RED;
	private static final Color MULTIPLE_AGENTS = Color.BLUE;
	private static final Color PATCH_FOUND = Color.YELLOW;
	
	private GamePanel gamePanel;
	
	public static final int SCALE = 10;
	private int size;

	private int nbAgent;
	private float alpha;
	private boolean functionUsed;
	
	public int nbPatch;
	public int nbTours;
	private MenuUI menu;
	
	public GameEngine(List mapContent, GamePanel gamePanel, MenuUI menu){
		this.gamePanel = gamePanel;
		this.menu = menu;
		for (int i = 0; i < mapContent.size(); i++) {
			if(i==0){
				this.size = (int) mapContent.get(i);
			} else {
				Position tmp = (Position) mapContent.get(i);
				IDrawable patch = new RectangleDrawable(PATCH, new Position(tmp.X*SCALE, tmp.Y*SCALE), new Dimension(10, 10));
				gamePanel.addDrawable(patch);
				this.nbPatch++;
				
			}
		}
		gamePanel.setNbPatch(this.nbPatch);
	}

	void putAgent(Agent agent, Position oldPos){
		if(oldPos != null){
			List ltmp = this.gamePanel.findDrawables(oldPos);
			if(ltmp == null){
				System.out.println("null");
			}
			for(int i = 0; i < ltmp.size(); i++){
				FormDrawable form = (FormDrawable) ltmp.get(i);
				this.gamePanel.removeDrawable(form);
			}
		}
		
		
		Position posAgent = agent.getPosition();
		//System.out.println(posAgent.X+"  "+posAgent.Y);

		FormDrawable agentRect = new RectangleDrawable(AGENT, posAgent, new Dimension(10, 10));
		
		if(this.gamePanel.isFree(agentRect.getRectangle())){
			this.gamePanel.addDrawable(agentRect);
		} else {
			List<FormDrawable> l = this.gamePanel.findDrawables(posAgent);
			if(!l.isEmpty()){
				for(int i = 0;i<l.size();i++){
					FormDrawable var = l.get(i);
					if(var.color == PATCH){
						FormDrawable patchFound = new RectangleDrawable(PATCH_FOUND, (var).pos, new Dimension(10,10));
						this.gamePanel.addDrawable(patchFound);
						this.gamePanel.removeDrawable(var);
						this.nbPatch--;
						this.gamePanel.setNbPatch(this.nbPatch);
					} else {
						FormDrawable multipleAgent = new RectangleDrawable(MULTIPLE_AGENTS, (var).pos, new Dimension(10,10));
						this.gamePanel.addDrawable(multipleAgent);
					}
				}
			}
		}

	}

	public int getSize(){
		return this.size;
	}
	
	public void setNbAgents(int nbAgents) {
		this.nbAgent = nbAgents;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public void setFunctionUsed(boolean functionUsed) {
		this.alpha = alpha;
	}
	
	public void refresh(List mapContent){
		this.nbPatch = 0;
		this.nbTours = 0;
		this.gamePanel.removeAllDrawables();
		this.gamePanel.revalidate();
		int i = 0;
		for(i = 0; i < mapContent.size(); i++){
			if(i != 0){
				Position tmpPos = (Position) mapContent.get(i);
				Position newPos =  new Position(tmpPos.X*SCALE, tmpPos.Y*SCALE);
				FormDrawable fomrToAdd = new RectangleDrawable(PATCH, newPos, new Dimension(10, 10));
				this.gamePanel.displayGeneratedMap(fomrToAdd);
				this.nbPatch++;
			} else {
				this.size = (int) mapContent.get(i);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Nombre agents " +nbAgent);
		System.out.println("Valeur de l'alpha " +(float) alpha);
		
		Agent[] agents = new Agent[nbAgent];
		Random randomGenerator = new Random();
		for(int i = 0;i<nbAgent;i++){
			int xRandom = randomGenerator.nextInt(this.getSize()*GameEngine.SCALE);
			int yRandom = randomGenerator.nextInt(this.getSize()*GameEngine.SCALE);
			agents[i] = new Agent(new Position(xRandom, yRandom));
			this.putAgent(agents[i],null);
		}
		
		int nbTour = 0;
		while(this.nbPatch != 0){
			for(int i = 0;i<nbAgent;i++){
				if(functionUsed)
					agents[i].moveLevy(this,alpha,gamePanel);
				else
					agents[i].moveRandom(this,gamePanel);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nbTour++;
		}
//		this.menu.getRefreshThread().interrupt();
//		this.menu.getGameThread().interrupt();
		System.err.println("done");
		this.gamePanel.dispose();
	}
	
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;


public class GameEngine{

	private static final Color PATCH = Color.GREEN;
	private static final Color AGENT = Color.RED;
	private static final Color MULTIPLE_AGENTS = Color.BLUE;
	private static final Color PATCH_FOUND = Color.YELLOW;
	public static final int SCALE = 10;
	private GameWindow gameWindow;
	private int size;

	public int nbPatch;
	public int nbTours;
	
	public GameEngine(List mapContent, UIv2 ui, GameWindow gameWindow){
		this.gameWindow = gameWindow;
		for (int i = 0; i < mapContent.size(); i++) {
			if(i==0){
				this.size = (int) mapContent.get(i);
			} else {
				Position tmp = (Position) mapContent.get(i);
				IDrawable patch = new RectangleDrawable(PATCH, new Position(tmp.X*10, tmp.Y*10), new Dimension(10, 10));
				ui.addDrawable(patch);
				this.nbPatch++;
			}
		}
	}

	void putAgent(Agent agent, UIv2 ui, Position oldPos){
		
		if(oldPos != null){
			List ltmp = ui.findDrawables(oldPos);
			if(ltmp == null){
				System.out.println("NUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUULLLLLLLLL");
			}
			for(int i = 0; i < ltmp.size(); i++){
				FormDrawable form = (FormDrawable) ltmp.get(i);
				ui.removeDrawable(form);
			}
		}
		
		
		Position posAgent = agent.getPosition();
		//System.out.println(posAgent.X+"  "+posAgent.Y);

		FormDrawable agentRect = new RectangleDrawable(AGENT, posAgent, new Dimension(10, 10));
		
		if(ui.isFree(agentRect.getRectangle())){
			ui.addDrawable(agentRect);
		} else {
			List l = ui.findDrawables(posAgent);
			if(!l.isEmpty()){
				for(int i = 0;i<l.size();i++){
					FormDrawable var = (FormDrawable) l.get(i);
					if(var.color == PATCH){
						FormDrawable patchFound = new RectangleDrawable(PATCH_FOUND, (var).pos, new Dimension(10,10));
						ui.addDrawable(patchFound);
						ui.removeDrawable(var);
						this.nbPatch--;
						System.out.println("####Patchs : "+this.nbPatch+" ######");
					} else {
						FormDrawable multipleAgent = new RectangleDrawable(MULTIPLE_AGENTS, (var).pos, new Dimension(10,10));
						ui.addDrawable(multipleAgent);
					}
				}
			}
		}

	}

	public int getSize(){
		return this.size;
	}
	
	public void lauchgame(GameWindow window){
		UIv2 windowUI = (UIv2) window.getUI();
		CommandPanel commandPanel = window.getCommandePanel();
		int nbAgent = commandPanel.getAgents();
		float alpha = (commandPanel.getAlpha()) / 10;
		
		System.out.println("Nombre agents " +nbAgent);
		
		Agent[] agents = new Agent[nbAgent];
		Random randomGenerator = new Random();
		for(int i = 0;i<nbAgent;i++){
			int xRandom = randomGenerator.nextInt(this.getSize()*10);
			int yRandom = randomGenerator.nextInt(this.getSize()*10);
			agents[i] = new Agent(new Position(xRandom, yRandom));
			this.putAgent(agents[i], windowUI,null);
		}
		int nbTour = 0;

		commandPanel.setEnabled(false);
		while(this.nbPatch != 0){
			for(int i = 0;i<nbAgent;i++){
				agents[i].moveLevy(this,alpha,windowUI);
				//agents[i].moveRandom(gameIA,ui);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nbTour++;
		}
		commandPanel.setEnabled(true);
		System.out.println("Nombre de tours = "+nbTour);
	}

}

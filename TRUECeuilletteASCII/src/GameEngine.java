import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.text.Normalizer.Form;
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
				IDrawable patch = new RectangleDrawable(PATCH, new Position(tmp.X*SCALE, tmp.Y*SCALE), new Dimension(10, 10));
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
			List<FormDrawable> l = ui.findDrawables(posAgent);
			if(!l.isEmpty()){
				for(int i = 0;i<l.size();i++){
					FormDrawable var = l.get(i);
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
		
	public void refresh(List mapContent, UIv2 ui){
		this.nbPatch = 0;
		this.nbTours = 0;
		ui.removeAllDrawables();
		ui.revalidate();
		int i = 0;
		for(i = 0; i < mapContent.size(); i++){
			if(i != 0){
				Position tmpPos = (Position) mapContent.get(i);
				Position newPos =  new Position(tmpPos.X*SCALE, tmpPos.Y*SCALE);
				FormDrawable fomrToAdd = new RectangleDrawable(PATCH, newPos, new Dimension(10, 10));
				ui.addDrawable(fomrToAdd);
				this.nbPatch++;
			} else {
				this.size = (int) mapContent.get(i);
			}
		}
	}

}

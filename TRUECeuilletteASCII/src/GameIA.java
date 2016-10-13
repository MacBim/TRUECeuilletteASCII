import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class GameIA {

	private static final Color PATCH = Color.GREEN;
	private static final Color AGENT = Color.RED;
	private static final Color MULTIPLE_AGENTS = Color.BLUE;
	private static final Color PATCH_FOUND = Color.YELLOW;
	public static final int SCALE = 10;
	private int size;

	public int nbPatch;

	public GameIA(List mapContent, UIv2 ui){
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
		IDrawable agentRect = new RectangleDrawable(AGENT, posAgent, new Dimension(10, 10));
		FormDrawable form = (FormDrawable) agentRect;
		
		if(ui.isFree(form.getRectangle())){
			ui.addDrawable(agentRect);
		} else {
			List l = ui.findDrawables(posAgent);
			if(!l.isEmpty()){
				for(int i = 0;i<l.size();i++){
					FormDrawable var = (FormDrawable) l.get(i);
					if(var.color == PATCH){
						IDrawable patchFound = new RectangleDrawable(PATCH_FOUND, ((FormDrawable) l.get(i)).pos, new Dimension(10,10));
						ui.addDrawable(patchFound);
						ui.removeDrawable((IDrawable) l.get(i));
						this.nbPatch--;
						System.out.println("####Patchs : "+this.nbPatch+" ######");
					} else {
						IDrawable multipleAgent = new RectangleDrawable(MULTIPLE_AGENTS, ((FormDrawable) l.get(i)).pos, new Dimension(10,10));
						ui.addDrawable(multipleAgent);
					}
				}
			}
		}

	}

	public int getSize(){
		return this.size;
	}
}

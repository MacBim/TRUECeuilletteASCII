import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author Jean-Baptiste
 *
 */
/**
 * @author Jean-Baptiste
 *
 */
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
	
	private final List SaveMapContent;
	
	/**
	 * GameEngine Constructor
	 * @param mapContent Contains the patches
	 * @param gamePanel The actual window of the game
	 * @param menu The MenuiUI who create it
	 */
	public GameEngine(List mapContent, GamePanel gamePanel, MenuUI menu){
		this.SaveMapContent = mapContent;
		this.gamePanel = gamePanel;
		this.menu = menu;
		for (int i = 0; i < mapContent.size(); i++) {
			if(i==0){
				this.size = (int) mapContent.get(i);
			} else {
				Position tmp = (Position) mapContent.get(i);
				FormDrawable patch = new RectangleDrawable(PATCH, new Position(tmp.getX()*SCALE, tmp.getY()*SCALE), new Dimension(10, 10));
				gamePanel.addDrawable(patch);
				this.nbPatch++;
				
			}
		}
		gamePanel.setNbPatch(this.nbPatch);
	}

	/**
	 * Refresh the FormDrawables of the agent and the patches (if needed)
	 * @param agent Agent who was moved
	 * @param oldPos Last position of the agent
	 */
	void putAgent(Agent agent, Position oldPos){
		if(oldPos != null){
			List ltmp = this.gamePanel.findDrawables(oldPos);
			for(int i = 0; i < ltmp.size(); i++){
				FormDrawable form = (FormDrawable) ltmp.get(i);
				this.gamePanel.removeDrawable(form);
			}
		}
		
		Position posAgent = agent.getPosition();

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

	/**
	 * @return Size of the map
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Set the current number of agent to nbAgents's value
	 * @param nbAgents Number of agents
	 */
	public void setNbAgents(int nbAgents) {
		this.nbAgent = nbAgents;
	}

	/** Set the current value for alpha
	 * @param alpha
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	/**
	 * Set the current function being used
	 * @param functionUsed
	 */
	public void setFunctionUsed(boolean functionUsed) {
		this.functionUsed = functionUsed;
	}
	
	/**
	 * Refresh the GameEngine to it's default state ( a the start of a new game )
	 * @param mapContent Contains the patches
	 */
	public void refresh(List mapContent){
		this.nbPatch = 0;
		this.nbTours = 0;
		this.gamePanel.removeAllDrawables();
		this.gamePanel.revalidate();
		int i = 0;
		for ( i = 0; i < mapContent.size(); i++) {
			if(i==0){
				this.size = (int) mapContent.get(i);
			} else {
				Position tmp = (Position) mapContent.get(i);
				FormDrawable patch = new RectangleDrawable(PATCH, new Position(tmp.getX()*SCALE, tmp.getY()*SCALE), new Dimension(10, 10));
				gamePanel.addDrawable(patch);
				this.nbPatch++;
				
			}
		}

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Nombre agents " +nbAgent);
		System.out.println("Valeur de l'alpha " +(float) alpha);
		
		int start;
		if(!this.menu.isCalibrationModeEnabled()){
			// pour au moin le faire une fois 
			start = -1;
		} else {
			start = 0;
		}
		CSVResultsExporter csvExporter = new CSVResultsExporter();
		for(int currentIteration = start ; currentIteration < this.menu.getNbIterations(); currentIteration++ ){
			
			Agent[] agents = new Agent[nbAgent];
			Random randomGenerator = new Random();
			for(int i = 0;i<nbAgent;i++){
				int xRandom = randomGenerator.nextInt(this.getSize()*GameEngine.SCALE);
				int yRandom = randomGenerator.nextInt(this.getSize()*GameEngine.SCALE);
				agents[i] = new Agent(new Position(xRandom, yRandom));
				this.putAgent(agents[i],null);
			}
			
			int nbTour = 0;
			while(nbPatch != 0){
				for(int i = 0;i<nbAgent;i++){
					if(functionUsed)
						agents[i].moveLevy(this,alpha,gamePanel);
					else
						agents[i].moveRandom(this,gamePanel);
					if(!this.menu.isCalibrationModeEnabled()) {
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				nbTour++;
			}
			csvExporter.exportResults(functionUsed, nbTour, this.nbAgent, this.alpha, this.size);
			System.err.println("done iteration n : "+ currentIteration);
			refresh(this.SaveMapContent);
		}
		this.gamePanel.dispose();
	}
	
	/**
	 * @return The GamePanel attached to the current GameEngine
	 */
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
	

}

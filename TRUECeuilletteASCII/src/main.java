import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class main {
	
	private static boolean action = false;
	private static boolean genNewMap = false;
	
	public static void main(String[] args) throws IOException {
		
		GameWindow gameWindow = new GameWindow(500);
		GamePanel gamePanel = (GamePanel) gameWindow.getGamePanel();
		CommandPanel commandPanel = gameWindow.getCommandePanel();
		MapGenerator mapGen = new MapGenerator();
		Parser parser = new Parser();
		List<Position> list = (List) parser.parse("../map.txt");
		GameEngine gameEngine = new GameEngine(list,gamePanel);
		
		commandPanel.generateNewMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	if(!action) // pour éviter de généer une map en pleine éxécution
            		genNewMap = true;
                      
            }
        });
		
		commandPanel.startButton.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e) { 
	            //gameEngine.lauchgame(gameWindow);
	            action = true;
	            //commandPanel.setEnabled(false);
	        } 
	    });
		while(action == false){
			// and we wait there until the button is pressed
			if (genNewMap) {
	            String path = mapGen.generateMap(commandPanel.getNewMapSize(), commandPanel.getNewMapStarProba());
	            if(path!= null)
	            	list = (List) parser.parse(path);
	            gameEngine.refresh(list, gamePanel);
	            //gameEngine = new GameEngine(list,gameUI,gameWindow);
	            genNewMap = false;
	            
	        }
			System.out.println();
		}
		
		
		
		int nbAgent = commandPanel.getAgents();
		float alpha = (commandPanel.getAlpha()) / 10;
		
		System.out.println("Nombre agents " +nbAgent);
		System.out.println("Valeur de l'alpha " +(float) alpha);
		
		Agent[] agents = new Agent[nbAgent];
		Random randomGenerator = new Random();
		for(int i = 0;i<nbAgent;i++){
			int xRandom = randomGenerator.nextInt(gameEngine.getSize()*GameEngine.SCALE);
			int yRandom = randomGenerator.nextInt(gameEngine.getSize()*GameEngine.SCALE);
			agents[i] = new Agent(new Position(xRandom, yRandom));
			gameEngine.putAgent(agents[i], gamePanel,null);
		}
		
		
		int nbTour = 0;
		commandPanel.setEnabled(false);
		commandPanel.disableControls();
		while(gameEngine.nbPatch != 0){
			for(int i = 0;i<nbAgent;i++){
				if(commandPanel.getFonctionUsed())
					agents[i].moveLevy(gameEngine,alpha,gamePanel);
				else
					agents[i].moveRandom(gameEngine,gamePanel);
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nbTour++;
			commandPanel.changeTurnsLabel(nbTour);
		}
		commandPanel.setEnabled(true);
		commandPanel.enableControls();
		System.out.println("Nombre de tours = "+nbTour);
	}

}

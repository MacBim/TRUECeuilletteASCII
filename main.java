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
        private static String thePath = "/home/leo/Documents/projet/TRUECeuilletteASCII-master/TRUECeuilletteASCII/src/map.txt";

	public static void main(String[] args) throws IOException {
		
		GameWindow gameWindow = new GameWindow(500);
		UIv2 gameUI = (UIv2) gameWindow.getUI();
		CommandPanel commandPanel = gameWindow.getCommandePanel();
		Parser parser = new Parser();
                MapGenerator mapGen = new MapGenerator();
		List list = (List) parser.parse(thePath);
		GameEngine gameEngine = new GameEngine(list,gameUI,gameWindow);
                commandPanel.generateNewMapButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
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
                            mapGen.generateMap(gameWindow);
                            list = (List) parser.parse(thePath);
                            gameEngine = new GameEngine(list,gameUI,gameWindow);
                            
                        }
			System.out.println("im waiting");
		}
		
		
		int nbAgent = commandPanel.getAgents();
		float alpha = (commandPanel.getAlpha()) / 10;
		
		System.out.println("Nombre agents " +nbAgent);
		System.out.println("Valeur de l'alpha " +(float) alpha);
		
		Agent[] agents = new Agent[nbAgent];
		Random randomGenerator = new Random();
		for(int i = 0;i<nbAgent;i++){
			int xRandom = randomGenerator.nextInt(gameEngine.getSize()*10);
			int yRandom = randomGenerator.nextInt(gameEngine.getSize()*10);
			agents[i] = new Agent(new Position(xRandom, yRandom));
			gameEngine.putAgent(agents[i], gameUI,null);
		}
		
		
		int nbTour = 0;
		commandPanel.setEnabled(false);
		commandPanel.disableControls();
		while(gameEngine.nbPatch != 0){
			for(int i = 0;i<nbAgent;i++){
				if(commandPanel.getFonctionUsed())
					agents[i].moveLevy(gameEngine,alpha,gameUI);
				else
					agents[i].moveRandom(gameEngine,gameUI);
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
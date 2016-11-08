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
		
		
		
		
		GamePanel gamePanel = new GamePanel();
		CommandPanel commandPanel = new CommandPanel();
		MapGenerator mapGen = new MapGenerator();
		Parser parser = new Parser();
		List<Position> list = (List) parser.parse("../map.txt");
		GameEngine gameEngine = new GameEngine(list,gamePanel, gamePanel, commandPanel);
		GameWindow gameWindow = new GameWindow(500,gamePanel,commandPanel, gameEngine);
		
		Thread refreshThread = new Thread(gameWindow);
		Thread gameThread = new Thread(gameEngine);
		
		
		
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
	            refreshThread.start();
	    		gameThread.start();
	        } 
	    });
		while(action == false){
			// and we wait there until the button is pressed
			if (genNewMap) {
				String path;
				int size = 0;
				if((size = commandPanel.getNewMapSize()) > 0){
					path = mapGen.generateMap(commandPanel.getNewMapSize(), commandPanel.getNewMapStarProba());
				} else {
					path = null;
				}
	            if(path!= null)
	            	list = (List) parser.parse(path);
	            gameEngine.refresh(list, gamePanel);
	            //gameEngine = new GameEngine(list,gameUI,gameWindow);
	            genNewMap = false;
	            
	        }
			System.out.println();
		}
		
	}

	

}

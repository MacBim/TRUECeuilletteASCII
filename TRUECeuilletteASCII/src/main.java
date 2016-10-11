import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) throws IOException {
		JFrame tmpFrame = new JFrame();
		tmpFrame.setSize(500, 500);
		
		tmpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIv2 ui = new UIv2();

		tmpFrame.add(ui);
		tmpFrame.setVisible(true);
		
		Parser parser = new Parser();
		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");
		GameIA gameIA = new GameIA(list,ui);
		int nbAgent = Integer.parseInt((String) new JOptionPane().showInputDialog("Nombre d'agents :"));
		Agent[] agents = new Agent[nbAgent];
		Random randomGenerator = new Random();
		for(int i = 0;i<nbAgent;i++){
			int xRandom = randomGenerator.nextInt(gameIA.getSize()*10);
			int yRandom = randomGenerator.nextInt(gameIA.getSize()*10);
			agents[i] = new Agent(new Position(xRandom, yRandom));
			gameIA.putAgent(agents[i], ui,null);
		}
		int nbTour = 0;
//		// ***********************************//
		while(gameIA.nbPatch != 0){
			for(int i = 0;i<nbAgent;i++){
				//agents[i].moveLevy(gameIA,(float) 1,ui);
				agents[i].moveRandom(gameIA,ui);
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nbTour++;
		}
	}

}

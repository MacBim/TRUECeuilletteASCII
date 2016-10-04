import java.util.List;
import java.util.Random;
import java.io.IOException;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) throws IOException {
		UI ui = new UI(10);
		Parser parser = new Parser();
		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");
		GameIA gameIA = new GameIA(list);
		int nbAgent = Integer.parseInt((String) new JOptionPane().showInputDialog("Nombre d'agents :"));
		Agent[] agents = new Agent[nbAgent];
		Random randomGenerator = new Random();
		for(int i = 0;i<nbAgent;i++){
			int xRandom = randomGenerator.nextInt(gameIA.content.length);
			int yRandom = randomGenerator.nextInt(gameIA.content.length);
			agents[i] = new Agent(new Position(xRandom, yRandom));
			gameIA.putAgent(agents[i]);
		}
		int nbTour = 0;
		ui.texteAreaPrimary.setText(gameIA.drawMap());
		// ***********************************//
		String info = "";
		while(gameIA.nbPatch != 0){
			for(int i = 0;i<nbAgent;i++){
				agents[i].moveLevy(gameIA,(float) 1);
				//agents[1].moveRandom(gameIA);
//				try {
//					Thread.sleep(250);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				ui.texteAreaPrimary.setText(gameIA.drawMap());
				ui.texteAreaSecondary.setText(""+nbTour+" tours");
			}
			nbTour++;
		}
	}

}

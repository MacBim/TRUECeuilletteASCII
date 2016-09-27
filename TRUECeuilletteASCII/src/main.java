import java.util.List;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		UI ui = new UI(10);
		Parser parser = new Parser();

		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");

		// TODO Auto-generated method stub
		GameIA gameIA = new GameIA(list);
		Agent agent1 = new Agent(new Position(0, 3));
		Agent agent2 = new Agent(new Position(0, 3));
		gameIA.putAgent(agent1);
		gameIA.putAgent(agent2);
		ui.texteAreaPrimary.setText(gameIA.drawMap());
		// ***********************************//
		while(gameIA.nbPatch != 0){
			agent1.moveRandom(gameIA);
			agent2.moveRandom(gameIA);
			try {
			    Thread.sleep(250);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			ui.texteAreaPrimary.setText(gameIA.drawMap());
			
		}
		ui.texteAreaSecondary.setText("DONE");
	}

}

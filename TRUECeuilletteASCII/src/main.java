import java.util.List;
import java.io.IOException;



public class main {

	public static void main(String[] args) throws IOException {
		UI ui = new UI(10);
		Parser parser = new Parser();

		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");

		// TODO Auto-generated method stub
		GameIA gameIA = new GameIA(list);
		Agent agent = new Agent(new Position(0, 3));
		gameIA.putAgent(agent);
		ui.texteArea.setText(gameIA.drawMap());
		// ***********************************//
		agent.moveAgent(gameIA, new Position(0,9));
		for(int i=0;i<150;i++){
			agent.moveAgentRandom(gameIA);
			try {
			    Thread.sleep(250);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			ui.texteArea.setText(gameIA.drawMap());
		}

	}

}

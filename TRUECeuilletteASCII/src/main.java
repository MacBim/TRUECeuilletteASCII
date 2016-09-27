import java.util.List;
import java.io.IOException;


public class main {

	public static void main(String[] args) throws IOException {
		Parser parser = new Parser();

		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");

		// TODO Auto-generated method stub
		GameIA gameIA = new GameIA(list);
		Agent agent = new Agent(new Position(0, 3));
		gameIA.putAgent(agent);
		System.out.println(gameIA.drawMap());
		// ***********************************//
		agent.moveAgent(gameIA, new Position(0,9));
		System.out.println(gameIA.drawMap());
		for(int i=0;i<3;i++){
			agent.moveAgentRandom(gameIA);
			System.out.println(gameIA.drawMap());
		}

	}

}

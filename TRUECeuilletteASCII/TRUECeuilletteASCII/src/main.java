import java.util.List;
import java.io.IOException;


public class main {

	public static void main(String[] args) throws IOException {
		Parser parser = new Parser();

		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");

		// TODO Auto-generated method stub
		Map map = new Map(list);
		Agent agent = new Agent(new Position(0, 5));
		map.putAgent(agent);
		System.out.println(map.drawMap());
		// ***********************************//
		map.moveAgent(agent, new Position(0,9));
		System.out.println(map.drawMap());

	}

}

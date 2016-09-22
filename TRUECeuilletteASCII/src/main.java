import java.util.List;
import java.io.IOException;


public class main {

	public static void main(String[] args) throws IOException {
		Parser parser = new Parser();

		List list = (List) parser.parse("W:/git/TRUECeuilletteASCII/TRUECeuilletteASCII/src/map.txt");

		// TODO Auto-generated method stub
		Map map = new Map(list);
		System.out.println(map.drawMap());
	}

}

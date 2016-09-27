import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Parser {

	List parse(String filePath) throws IOException{

		List list = new LinkedList();
		String p = "";
		String currentLine= "";
		int nbLine = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			currentLine = br.readLine();
			list.add(Integer.parseInt(currentLine));
			while((currentLine = br.readLine()) != null){
				//System.out.println(nbLine);
				for(int x=0;x<currentLine.length();x++){
					//System.out.println(x);
					if(currentLine.charAt(x) != ' '){
//						System.out.println("("+x+","+nbLine+")");
						list.add(new Position(x, nbLine));
					}
				}
				
				//System.out.println(nbLine);
				nbLine++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

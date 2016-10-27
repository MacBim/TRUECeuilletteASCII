import java.io.File;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.PrintWriter;
import java.lang.Object;
import java.util.Random;

public class MapGenerator {

	String constructLine(int size, double starProbability)
	/*
	 * This function is used to create and return a string with a lenght of
	 * "size". Returned Strings contains " ", "*", and "/n" at the end. number
	 * of "*" and " " are chosen randomly. TODO: implement other way to do it?
	 * implement a way to choose the probability in the menu.
	 */
	{
		String newLine = "";
		Random randomGenerator = new Random();
		for (int i = 0; i < size; i++) {
			double d = randomGenerator.nextDouble();
			if (d <= starProbability)
				newLine += "*";
			else
				newLine += " ";
		}
		//newLine += '\n';
		return newLine;

	}

	public String generateMap(int size, double starProba) throws IOException {
		String newLine = "";
		BufferedWriter bfwrtr = null;
		File f = new File("./map12345.txt");

		try {
			bfwrtr = new BufferedWriter(new FileWriter(f,false));

			bfwrtr.write(""+size);
			bfwrtr.write(System.lineSeparator());

			for (int i = 0; i < size; i++) {
				newLine = constructLine(size, starProba);
				bfwrtr.write(newLine + System.lineSeparator());
				//System.out.println(newLine);
				
			}
			bfwrtr.close();
			System.out.println(f.getCanonicalPath());
			return f.getCanonicalPath();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// le cas ou ca foire
		return null;
	}
}
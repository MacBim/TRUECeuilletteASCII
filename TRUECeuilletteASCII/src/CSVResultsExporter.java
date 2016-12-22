import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVResultsExporter {

	public void exportResults(boolean functionUsed, int nbTours, int nbAgent, float alpha, int size){
		System.err.println("Exporting..");
		FileWriter fileWriter = null;
		try {		
			// test pour savoir si results.txt existe déja
			File f = new File("results.csv");
			if(f.exists() && !f.isDirectory()) {
				// si il existe on l'ouvre
				fileWriter = new FileWriter("results.csv",true);
			} else {
				// sinon on le créer
				fileWriter = new FileWriter(new File("results.csv"));
				fileWriter.append("nbTours,nbAgents,alpha,taille_map,fonctionUsed");
				fileWriter.append('\n');
			}
			
			fileWriter.append(Integer.toString(nbTours));
			fileWriter.append(",");
			fileWriter.append(Integer.toString(nbAgent));
			fileWriter.append(",");
			fileWriter.append(Float.toString(alpha));
			fileWriter.append(",");
			fileWriter.append(Integer.toString(size));
			fileWriter.append(",");
			if(functionUsed == true) { // lévy
				fileWriter.append("levy");
			} else {
				fileWriter.append("random");
			}
			fileWriter.append('\n');
			
			fileWriter.flush();
			fileWriter.close();
			System.err.println("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

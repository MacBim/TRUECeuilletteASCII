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
            This function is used to create and return a string with a lenght of "size".
            Returned Strings contains " ", "*", and "/n" at the end. 
            number of "*" and " " are chosen randomly. 
            TODO: 
                implement other way to do it? 
                implement a way to choose the probability in the menu. 
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
        newLine += "/n";
        return newLine;
        
        
        
    }

    public void generateMap(GameWindow window) throws IOException{
          
        UIv2 windowUI = (UIv2) window.getUI();
        CommandPanel commandPanel = window.getCommandePanel();
        String newLine = "";
        File f = new File ("map.txt");  
        int size;
        size = commandPanel.getNewMapSize();
        double starProba;
        starProba = commandPanel.getNewMapStarProba();
        
        try
        {
            PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f))); 
            
            pw.println(size);
            pw.println("/n");
            
            
            for (int i = 0; i < size; i++) {
                newLine = constructLine(size, starProba);
                pw.println(newLine);
                
            }
            
        }
        catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
    }
}

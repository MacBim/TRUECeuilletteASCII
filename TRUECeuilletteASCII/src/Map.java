import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Map {
	
	String[][] content;
	
	public Map(List mapContent){
		for (int i = 0; i < mapContent.size(); i++) {
			if(i==0){
				int size = (int) mapContent.get(i);
				this.content = new String[size][size];
			} else {
				Position tmp = (Position) mapContent.get(i);
				this.content[tmp.getX()][tmp.getY()] = " * ";
			}
		}
		/**
		 * pour chaque agent, le placer dans le tableau content
		 * pareil pour les patch
		 */
	}

	public Map(int size, Agent[] agents, InterestPatch[] patchs){
		this.content = new String[size][size];
		
		/**
		 * pour chaque agent, le placer dans le tableau content
		 * pareil pour les patch
		 */
	}
	
	String drawMap(){
		String asciiMap = "";
		for(int x=0; x<this.content.length;x++){
			for(int y=0; y<this.content.length;y++){
//				System.out.println(x+" | "+y+"\n");
				if(this.content[x][y]==null){
					asciiMap += " ";
				} else {
					asciiMap += this.content[x][y];
				}
				
			}
			
			asciiMap += '\n';
		}
		return asciiMap;
	}
}

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class GameIA {
	
	String[][] content;
	public int nbPatch;
	
	public GameIA(List mapContent){
		for (int i = 0; i < mapContent.size(); i++) {
			if(i==0){
				int size = (int) mapContent.get(i);
				this.content = new String[size][size];
			} else {
				Position tmp = (Position) mapContent.get(i);
				this.content[tmp.getX()][tmp.getY()] = "|*";
				this.nbPatch++;
			}
		}
	}

	public GameIA(int size, Agent[] agents, InterestPatch[] patchs){
		this.content = new String[size][size];
		
		/**
		 * pour chaque agent, le placer dans le tableau content
		 * pareil pour les patch
		 */
	}
	
	String drawMap(){
		String asciiMap = "";
		for(int y=0; y<this.content.length;y++){
			for(int x=0; x<this.content.length;x++){
				if(this.content[x][y]==null){
					asciiMap += "| ";
				} else {
					asciiMap += this.content[x][y];
				}
			}
			asciiMap += "|\n";
			
		}
		return asciiMap;
	}
	
	void putAgent(Agent agent){
		Position posAgent = agent.getPosition();
		int x = posAgent.getX();
		int y = posAgent.getY();
		if(this.content[x][y] != "|*"){
			this.content[x][y] = "|@";
		} else {
			this.nbPatch--;
			this.content[x][y] = "|X";
		}
	}
}


public class Map {
	
	String[][] content;
	
	public Map(int size){
		this.content = new String[size][size];
//		this.content[0][1] = "0";
//		this.content[1][1] = "1";
//		this.content[2][1] = "2";
//		this.content[3][1] = "3";
//		this.content[4][1] = "4";
		for(int x=0; x<size;x++){
			for(int y=0; y<size;y++){
				this.content[x][y] = " @ ";
			}
		}
//		System.out.println(this.content.length);
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
				System.out.println(x+" | "+y+"\n");
				asciiMap += this.content[x][y];
			}
			asciiMap += '\n';
		}
		return asciiMap;
	}
}

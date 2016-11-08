import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame implements Runnable {
	
	private int size;
	private CommandPanel commandPanel;
	private GamePanel ui;
	private GameEngine gameEngine;
	
	public GameWindow(int size, GamePanel gamePanel, CommandPanel commandPanel, GameEngine gameEngine){
		
		//this.gameFrame = new JFrame();
		this.setSize(size+100, size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// une ligne, deux colones
		this.setLayout(new GridLayout(1, 2));
		this.size = size;
		
		this.gameEngine = gameEngine;
		this.ui = gamePanel;
		this.ui.setBackground(Color.WHITE);
		this.commandPanel = commandPanel;
		
		this.getContentPane().add(this.ui);
		this.getContentPane().add(this.commandPanel);
		this.setVisible(true);
		
	}
	
	public CommandPanel getCommandePanel(){
		return this.commandPanel;
	}
	
	public GamePanel getGamePanel(){
		return this.ui;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.gameEngine.nbPatch != 0){
			//this.ui.revalidate();
			this.ui.repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.err.println("JE SUIS LA DEDANS");
		}
	}


	
}

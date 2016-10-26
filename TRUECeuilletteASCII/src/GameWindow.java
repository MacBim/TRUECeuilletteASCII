import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	
	private int size;
	private CommandPanel commandPanel;
	private UIv2 ui;
	
	public GameWindow(int size){
		
		//this.gameFrame = new JFrame();
		this.setSize(size+100, size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// une ligne, deux colones
		this.setLayout(new GridLayout(1, 2));
		this.size = size;
		
		this.ui = new UIv2();
		this.ui.setBackground(Color.WHITE);
		this.commandPanel = new CommandPanel();
		
		this.getContentPane().add(this.ui);
		this.getContentPane().add(this.commandPanel);
		this.setVisible(true);
		
		
		
		
	}
	
	public CommandPanel getCommandePanel(){
		return this.commandPanel;
	}
	
	public UIv2 getUI(){
		return this.ui;
	}
	
	public void update() {
		this.revalidate();
		this.repaint();
	}
}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UI extends JFrame {
	
	private JFrame frame;
	//public JTextArea texteAreaPrimary;
	
	public Canvas pane;
	public JTextArea texteAreaSecondary;
	
	public UI(int size) {
		
		this.frame = new JFrame("Lévy");
		this.frame.setLayout(new GridLayout(1, 2));
		
		texteAreaSecondary = new JTextArea(20,20);
		texteAreaSecondary.setText("TEXT");
		this.frame.add(texteAreaSecondary);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(500, 500);
		this.frame.setVisible(true);
		
		this.pane = new Canvas();
		this.pane.setSize(500, 500);
		this.pane.setVisible(true);
		this.frame.add(this.pane);	
	}
	
}

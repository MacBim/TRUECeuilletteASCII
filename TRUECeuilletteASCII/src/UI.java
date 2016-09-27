import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class UI {
	
	private JFrame frame;
	public JTextArea texteAreaPrimary;
	public JTextArea texteAreaSecondary;
	
	public UI(int size) {
		this.frame = new JFrame("Lévy");
		this.texteAreaPrimary = new JTextArea(size, size);
		this.frame.setLayout(new GridLayout(1, 2));
		this.frame.add(this.texteAreaPrimary);
		texteAreaSecondary = new JTextArea(20,20);
		texteAreaSecondary.setText("TEXT");
		this.frame.add(texteAreaSecondary);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(500, 500);
		this.frame.setVisible(true);
	}
	
}

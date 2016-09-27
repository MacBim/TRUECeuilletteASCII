import javax.swing.JFrame;
import javax.swing.JTextArea;

public class UI {
	
	private JFrame frame;
	public JTextArea texteArea;
	
	public UI(int size) {
		this.frame = new JFrame("TEST");
		this.texteArea = new JTextArea(size, size);
		this.frame.setContentPane(texteArea);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(500, 500);
		this.frame.setVisible(true);
	}
	
}

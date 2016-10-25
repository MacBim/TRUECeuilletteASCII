import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CommandPanel extends JPanel {

	private JSlider alphaSlider;
	private JSlider agentsSlider;
	private JLabel alphaLabel = new JLabel("Valeur de l'alpha : 1.0");
	private JLabel agentsLabel = new JLabel("Nombre d'agents : 1");
	public JButton startButton = new JButton("Lancer la recherche");
	
	public CommandPanel(){

		//Création du slider pour le choix de l'aplha
		this.alphaSlider = new JSlider();
		this.alphaSlider.setMaximum(20);
	    this.alphaSlider.setMinimum(0);
	    this.alphaSlider.setValue(10);
	    this.alphaSlider.setPaintTicks(true);
	    this.alphaSlider.setPaintLabels(true);
	    this.alphaSlider.setMajorTickSpacing(5);
	    this.alphaSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				alphaLabel.setText("Valeur de l'alpha : " + (float) ((JSlider)e.getSource()).getValue()/10);
			}
		});
 
	    
	    //Création du slider pour le nombre d'agents
	    this.agentsSlider = new JSlider();
	    this.agentsSlider.setMaximum(10);
	    this.agentsSlider.setMinimum(1);
	    this.agentsSlider.setValue(1);
	    this.agentsSlider.setPaintTicks(true);
	    this.agentsSlider.setPaintLabels(true);
	    this.agentsSlider.setMajorTickSpacing(4);
	    this.agentsSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				agentsLabel.setText("Nombre d'agents : " + ((JSlider)e.getSource()).getValue());
			}
		});
	    
	    
	    this.add(this.alphaLabel);
	    this.add(this.alphaSlider);
	    this.add(this.agentsLabel);
	    this.add(this.agentsSlider);
	    this.add(this.startButton);
	    this.setVisible(true);
	}
	
	public int getAlpha(){
		return this.alphaSlider.getValue();
	}
	
	public int getAgents(){
		return this.agentsSlider.getValue();
	}
}

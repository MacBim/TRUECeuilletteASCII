import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CommandPanel extends JPanel {

	private JSlider alphaSlider;
	private JSlider agentsSlider;
	private JLabel alphaLabel = new JLabel("Valeur de l'alpha : 1.0");
	private JLabel agentsLabel = new JLabel("Nombre d'agents : 1");
	private JLabel turnsLabel = new JLabel("Nombre de tours : 0");
	public JButton startButton = new JButton("Lancer la recherche");
	
	private JRadioButton jRLevy = new JRadioButton("Méthode de Lévy");
	private JRadioButton jRRandom = new JRadioButton("Méthode aléatoire");
	
	public CommandPanel(){

		this.jRLevy.setSelected(true);
		
		this.setLayout(new GridLayout(8, 1));
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
	    
	    
	    ButtonGroup radioButtonGroup = new ButtonGroup();
	    
	    radioButtonGroup.add(jRLevy);
	    radioButtonGroup.add(jRRandom);
	    
	    this.add(this.alphaLabel);
	    this.add(this.alphaSlider);
	    this.add(this.agentsLabel);
	    this.add(this.agentsSlider);
	    this.add(jRLevy);
	    this.add(jRRandom);
	    this.add(this.startButton);
	    this.add(this.turnsLabel);
	    this.setVisible(true);
	}
	
	public int getAlpha(){
		return this.alphaSlider.getValue();
	}
	
	
	
	public int getAgents(){
		return this.agentsSlider.getValue();
	}
	
	public void changeTurnsLabel(int nbTours){
		this.turnsLabel.setText("Nombre de tours : "+ nbTours);
	}
	
	public void disableControls(){
		this.startButton.setEnabled(false);
		this.alphaSlider.setEnabled(false);
		this.agentsSlider.setEnabled(false);
		this.jRLevy.setEnabled(false);
		this.jRRandom.setEnabled(false);
	}
	
	public void enableControls(){
		this.startButton.setEnabled(true);
		this.alphaSlider.setEnabled(true);
		this.agentsSlider.setEnabled(true);
		this.jRLevy.setEnabled(true);
		this.jRRandom.setEnabled(true);
	}
	
	/**
	 * @return true = lévy, false = random
	 */
	public boolean getFonctionUsed(){
		return this.jRLevy.isSelected();
	}
}

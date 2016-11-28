import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;

public class MenuUI extends JFrame {

	private GameEngine ge;
	private JPanel contentPane;
	private JButton generateMapBtn;
	private JButton startBtn;
	private JButton openMapBtn;
	private JSpinner agentSelector;
	private JSpinner alphaSelector;
	private JLabel starProbabilityLabel;
	private JComboBox functionUsedCB;
	private String selectedMap;
	private JLabel mapNameLabel;

	/**
	 * Create the frame.
	 */
	public MenuUI() {
		setTitle("Projet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		agentSelector = new JSpinner();
		agentSelector.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		agentSelector.setBounds(27, 167, 87, 20);
		contentPane.add(agentSelector);
		
		JLabel AgentSelectorLabel = new JLabel("Agents");
		AgentSelectorLabel.setBounds(27, 150, 87, 14);
		contentPane.add(AgentSelectorLabel);
		
		startBtn = new JButton("Start");
		startBtn.setBounds(172, 146, 118, 23);
		startBtn.addActionListener(e -> start());
		contentPane.add(startBtn);
		
		openMapBtn = new JButton("Open a map");
		openMapBtn.setBounds(172, 230, 118, 23);
		openMapBtn.addActionListener(e -> chooseMap());
		contentPane.add(openMapBtn);
		
		alphaSelector = new JSpinner();
		alphaSelector.setToolTipText("");
		alphaSelector.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(2), new Float(0.01)));
		alphaSelector.setBounds(27, 214, 87, 20);
		contentPane.add(alphaSelector);
		
		JLabel alphaSelectorLabel = new JLabel("Alpha");
		alphaSelectorLabel.setBounds(25, 193, 78, 14);
		contentPane.add(alphaSelectorLabel);
		
		JLabel logoLabel = new JLabel("Smart Picking");
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 41));
		logoLabel.setBounds(109, 30, 254, 84);
		contentPane.add(logoLabel);
		
		generateMapBtn = new JButton("Generate a map");
		generateMapBtn.setBounds(172, 184, 118, 23);
		contentPane.add(generateMapBtn);
		
		mapNameLabel = new JLabel("No map selected");
		mapNameLabel.setEnabled(false);
		mapNameLabel.setBounds(315, 234, 95, 14);
		contentPane.add(mapNameLabel);
		
		JSpinner startProbabilitySelector = new JSpinner();
		startProbabilitySelector.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.01)));
		startProbabilitySelector.setBounds(315, 187, 78, 20);
		contentPane.add(startProbabilitySelector);
		
		starProbabilityLabel = new JLabel("Patch probability");
		starProbabilityLabel.setBounds(315, 174, 95, 14);
		contentPane.add(starProbabilityLabel);
		
		functionUsedCB = new JComboBox();
		functionUsedCB.setModel(new DefaultComboBoxModel(new String[] {"L\u00E9vy", "Random"}));
		functionUsedCB.setSelectedIndex(0);
		functionUsedCB.setBounds(315, 147, 95, 20);
		contentPane.add(functionUsedCB);
		
		JLabel functionUsedLabel = new JLabel("Function to use :");
		functionUsedLabel.setBounds(315, 129, 95, 14);
		contentPane.add(functionUsedLabel);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void linkGameEngine(GameEngine ge){
		this.ge = ge;
	}
	
	public void chooseMap(){
		String path = null;
		File f;
		JFileChooser mapChooser = new JFileChooser();
		if(mapChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			f = mapChooser.getSelectedFile();
			path = f.getPath();
			this.mapNameLabel.setText(f.getName());
			this.selectedMap = path;
		} else {
			this.mapNameLabel.setText("Invalid File");
		}
		
	}
	
	public String getSelectedMap(){
		return this.selectedMap;
	}
	
	private void start(){
		if(selectedMap != null){
			
			List list = null;
			Parser parser = new Parser();
			try {
				list = (List) parser.parse(selectedMap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GamePanel gp = new GamePanel();
			GameEngine ge = new GameEngine(list, gp);
			ge.getGamePanel().setVisible(true);
			ge.setAlpha((float) alphaSelector.getValue());
			ge.setNbAgents((int) agentSelector.getValue());
			ge.setFunctionUsed(getFunctionUsed());
			
			System.out.println(agentSelector.getValue());
			
			
			Thread gameThread = new Thread(ge);
			gameThread.start();
			
			
			//ge.launchGame((int) agentSelector.getValue(), (float) alphaSelector.getValue(), getFunctionUsed());
			Thread refreshThread = new Thread(ge.getGamePanel());
			refreshThread.start();
		}
	}
	
	public boolean getFunctionUsed(){
		if(functionUsedCB.getSelectedIndex() == 0){ // AKA Lévy
			return true;
		}
		return false; // AKA Random
	}
}
